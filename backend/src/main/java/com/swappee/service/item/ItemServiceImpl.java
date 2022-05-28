package com.swappee.service.item;

import com.google.common.base.Preconditions;
import com.swappee.dao.like.item.ItemDao;
import com.swappee.dao.like.LikeDao;
import com.swappee.dao.picture.PictureDao;
import com.swappee.dao.request.RequestDao;
import com.swappee.dao.user.UserDao;
import com.swappee.domain.item.Item;
import com.swappee.domain.item.ItemHistory;
import com.swappee.domain.item.PreferredItem;
import com.swappee.domain.like.Like;
import com.swappee.domain.picture.Picture;
import com.swappee.domain.request.Request;
import com.swappee.domain.user.User;
import com.swappee.mapper.item.ItemDTOMapper;
import com.swappee.mapper.item.ItemHistoryDTOMapper;
import com.swappee.mapper.item.PreferredItemDTOMapper;
import com.swappee.mapper.picture.PictureDTOMapper;
import com.swappee.model.item.*;
import com.swappee.model.picture.PictureViewDTO;
import com.swappee.model.wrapper.GridResult;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation class for managing items.
 */
@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    PictureDao pictureDao;

    @Autowired
    LikeDao likeDao;

    @Autowired
    RequestDao requestDao;

    @Autowired
    ItemDTOMapper itemDTOMapper;

    @Autowired
    PreferredItemDTOMapper preferredItemDTOMapper;

    @Autowired
    PictureDTOMapper pictureDTOMapper;

    @Autowired
    ItemHistoryDTOMapper itemHistoryDTOMapper;

    /**
     * Get an ItemDTO by id
     * Used for getting item's info when editing item
     *
     * @param id
     * @return
     * @throws BaseServiceException
     */

    @Override
    public ItemDTO findItemById(Long id) throws BaseServiceException {
        try {
            logger.info("Start findItemById - id: {}", id);
            Preconditions.checkNotNull(id);

            ItemDTO itemDTO = itemDTOMapper.mapEntity(itemDao.findById(id));
            Preconditions.checkNotNull(itemDTO);
            return itemDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findItemById");
        }
    }

    /**
     * Get an ItemViewDTO
     * for displaying a Single Item on front end
     * TODO cache in the future
     *
     * @param id
     * @return
     * @throws BaseServiceException
     */

    @Override
    public ItemViewDTO findItemViewById(Long id) throws BaseServiceException {
        try {
            logger.info("Start findItemViewById - id: {}", id);
            Preconditions.checkNotNull(id);

            Item item = itemDao.findById(id);
            Preconditions.checkNotNull(item);
            List<Picture> pictureList = pictureDao.findByItemId(id);
            Like like = likeDao.findByUserIdAndItemId(item.getUserId(), id);
            return itemViewDTOMapper(item, pictureList, like);
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findItemViewById");
        }
    }

    /**
     * Get a list ItemCardDTOs from a id list
     * Used when you already have a list of ids, and want to fetch an appropriate the list of ItemCardDTOs
     * Used by elasticsearch and likes id lists
     *
     * @return
     * @throws BaseServiceException
     */

    @Override
    public List<ItemCardDTO> getAll(List<Long> ids) throws BaseServiceException {
        try {
            logger.info("Start getAll - ids: {}", ids);

            List<Item> itemList = itemDao.getAll(ids);
            List<ItemCardDTO> itemCardDTOList = new ArrayList<>();
            for (Item item : itemList) {
                itemCardDTOList.add(
                        itemCardDTOMapper(
                                item,
                                pictureDao.findByItemIdAndOrderIs(item.getId(), 0L),
                                likeDao.findByUserIdAndItemId(item.getUserId(), item.getId()),
                                userDao.findById(item.getId())));
            }
            return itemCardDTOList;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_GET_LIST_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End getAll");
        }
    }

    /**
     * Get a page of ItemCardDTO
     *
     * @param pageable
     * @return
     * @throws BaseServiceException
     */

    @Override
    public GridResult findItems(Pageable pageable) throws BaseServiceException {
        try {
            logger.info("Start findItems - pageable: {}", pageable);
            Preconditions.checkNotNull(pageable);

            Page<Item> itemPage = itemDao.getAll(pageable);
            List<ItemCardDTO> itemCardDTOList = new ArrayList<>();
            for (Item item : itemPage) {
                itemCardDTOList.add(
                        itemCardDTOMapper(
                                item,
                                pictureDao.findByItemIdAndOrderIs(item.getId(), 0L),
                                likeDao.findByUserIdAndItemId(item.getUserId(), item.getId()),
                                userDao.findById(item.getUserId())));
            }
            GridResult gridResult = new GridResult();
            gridResult.setData(itemCardDTOList);
            gridResult.setTotalRecordCount((int) itemPage.getTotalElements());
            gridResult.setIsSuccess(true);
            return gridResult;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_GET_PAGE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findItems");
        }
    }

    /**
     * Get a page of ItemCardDTO by category
     * @param category
     * @param pageable
     * @return
     * @throws BaseServiceException
     */

    @Override
    public GridResult findByCategory(String category, Pageable pageable) throws BaseServiceException {
        try {
            logger.info("Start findByCategory - category: {}, pageable: {}", category, pageable);
            Preconditions.checkNotNull(category);
            Preconditions.checkNotNull(pageable);

            Page<Item> itemPage = itemDao.findByCategory(category, pageable);
            List<ItemCardDTO> itemCardDTOList = new ArrayList<>();
            for (Item item : itemPage) {
                itemCardDTOList.add(
                        itemCardDTOMapper(
                                item,
                                pictureDao.findByItemIdAndOrderIs(item.getId(), 0L),
                                likeDao.findByUserIdAndItemId(item.getUserId(), item.getId()),
                                userDao.findById(item.getUserId())));
            }
            GridResult gridResult = new GridResult();
            gridResult.setData(itemCardDTOList);
            gridResult.setTotalRecordCount((int) itemPage.getTotalElements());
            gridResult.setIsSuccess(true);
            return gridResult;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_GET_PAGE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByCategory");
        }
    }

    /**
     * Get a page of ItemCardDTO by user id
     * Used when looking at user's profile
     *
     * @param userId
     * @param pageable
     * @return
     * @throws BaseServiceException
     */

    @Override
    public GridResult findItemsByUserId(Long userId, Pageable pageable) throws BaseServiceException {
        try {
            logger.info("Start findItemsByUserId - userId: {}, pageable: {}", userId, pageable);
            Preconditions.checkNotNull(userId);
            Preconditions.checkNotNull(pageable);

            Page<Item> itemPage = itemDao.findByUserId(userId, pageable);
            List<ItemCardDTO> itemCardDTOList = new ArrayList<>();
            for (Item item : itemPage) {
                itemCardDTOList.add(
                        itemCardDTOMapper(
                                item,
                                pictureDao.findByItemIdAndOrderIs(item.getId(), 0L),
                                likeDao.findByUserIdAndItemId(item.getUserId(), item.getId()),
                                userDao.findById(item.getUserId())));
            }
            GridResult gridResult = new GridResult();
            gridResult.setData(itemCardDTOList);
            gridResult.setTotalRecordCount((int) itemPage.getTotalElements());
            gridResult.setIsSuccess(true);
            return gridResult;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_GET_PAGE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findItemsByUserId");
        }
    }

    /**
     * Get a page of ItemCardDTO by user id
     * Used when a user is looking for his liked items
     *
     * @param userId
     * @param pageable
     * @return
     */

    @Override
    public GridResult findItemsByUserLikes(Long userId, Pageable pageable) throws BaseServiceException {
        try {
            logger.info("Start findItemsByUserLikes - userId: {}, pageable: {}", userId, pageable);
            Preconditions.checkNotNull(userId);
            Preconditions.checkNotNull(pageable);
            //Find user's likes
            Page<Like> likePage = likeDao.findByUserId(userId, pageable);
            List<Long> idList = new ArrayList<>();
            for (Like like : likePage) {
                idList.add(like.getId());
            }

            List<Item> itemList = itemDao.getAll(idList);
            List<ItemCardDTO> itemCardDTOList = new ArrayList<>();
            for (Item item : itemList) {
                itemCardDTOList.add(
                        itemCardDTOMapper(
                                item,
                                pictureDao.findByItemIdAndOrderIs(item.getId(), 0L),
                                likeDao.findByUserIdAndItemId(item.getUserId(), item.getId()),
                                userDao.findById(item.getUserId())));
            }
            GridResult gridResult = new GridResult();
            gridResult.setData(itemCardDTOList);
            gridResult.setTotalRecordCount((int) likePage.getTotalElements());
            gridResult.setIsSuccess(true);
            return gridResult;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_GET_PAGE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findItemsByUserLikes");
        }
    }

    /**
     * Find all Items under a user
     * Used to delete items under a user when the user is deleted
     *
     * @param userId
     * @return
     * @throws BaseServiceException
     */

    @Override
    public List<ItemDTO> findByUserId(Long userId) throws BaseServiceException {
        try {
            logger.info("Start findByUserId - userId: {}", userId);
            Preconditions.checkNotNull(userId);
            List<Item> itemList = itemDao.findByUserId(userId);
            List<ItemDTO> itemDTOList = new ArrayList<>();
            for (Item item : itemList) {
                itemDTOList.add(itemDTOMapper.mapEntity(item));
            }
            return itemDTOList;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_GET_LIST_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByUserId");
        }
    }

    /**
     * When item is liked/unliked
     * Creates/Deletes Like and increments/decrements item's likes
     *
     * @param itemId
     * @param userId
     * @param like   true for liked, false for unliked
     * @return
     * @throws BaseServiceException
     */

    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public ItemDTO itemLiked(Long itemId, Long userId, Boolean like) throws BaseServiceException {
        try {
            logger.info("Start itemLiked - itemId: {}, userId: {}, like: {}", itemId, userId, like);
            Preconditions.checkNotNull(itemId);
            Preconditions.checkNotNull(userId);
            Preconditions.checkNotNull(like);

            Item item = (itemDao.findById(itemId));
            Like likeExist = likeDao.findByUserIdAndItemId(userId,itemId);
            Preconditions.checkNotNull(item);
            if (Boolean.TRUE.equals(like) && likeExist == null) {
                item.setLikes(item.getLikes() + 1L);
                Like newLike = new Like();
                newLike.setUserId(userId);
                newLike.setItemId(itemId);
                likeDao.create(newLike);
            } else if (Boolean.FALSE.equals(like) && likeExist != null){
                item.setLikes(item.getLikes() - 1L);
                likeDao.delete(likeDao.findByUserIdAndItemId(userId, itemId));
            } else {
                logger.error("Unable to like/unlike item");
                throw new BaseDaoException("Item is already liked/unliked");
            }
            ItemDTO updatedItemDTO = itemDTOMapper.mapEntity(itemDao.update(item));
            Preconditions.checkNotNull(updatedItemDTO);
            return updatedItemDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_LIKE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End itemLiked");
        }
    }

    /**
     * Creates ItemDTO
     *
     * @param toCreate
     * @return
     * @throws BaseServiceException
     */

    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public ItemDTO create(ItemDTO toCreate) throws BaseServiceException {
        try {
            logger.info("Start create - toCreate: {}", toCreate);
            Preconditions.checkNotNull(toCreate);
            ItemDTO itemDTO = itemDTOMapper.mapEntity(itemDao.create(itemDTOMapper.mapDto(toCreate)));
            Preconditions.checkNotNull(itemDTO);
            return itemDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_CREATE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End create");
        }
    }

    /**
     * Update ItemDTO and its pictureDTOs
     * @param toUpdate
     * @return
     * @throws BaseServiceException
     */

    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public ItemDTO update(ItemDTO toUpdate) throws BaseServiceException {
        try {
            logger.info("Start update - toUpdate: {}", toUpdate);
            Preconditions.checkNotNull(toUpdate);
            ItemDTO itemDTO = itemDTOMapper.mapEntity(itemDao.update(itemDTOMapper.mapDto(toUpdate)));
            Preconditions.checkNotNull(itemDTO);
            return itemDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_UPDATE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End update");
        }
    }

    /**
     * Deletes ItemDTO, set all likes under as deleted and set statuses of requests the item is in to REMOVED
     *
     * @param toDelete
     * @return
     * @throws BaseServiceException
     */
    @Override
    @Transactional(rollbackFor = {BaseServiceException.class})
    public ItemDTO delete(ItemDTO toDelete) throws BaseServiceException {
        try {
            logger.info("Start delete - toDelete: {}", toDelete);
            Preconditions.checkNotNull(toDelete);
            ItemDTO itemDTO = itemDTOMapper.mapEntity(itemDao.delete(itemDTOMapper.mapDto(toDelete)));
            Preconditions.checkNotNull(itemDTO);
            List<Like> likeList = likeDao.findByUserId(toDelete.getUserId());
            //set each like as itemDeleted = true
            for (Like like : likeList) {
                like.setItemDeleted(true);
                likeDao.update(like);
            }
            //set statuses of requests the item is in to REMOVED
            List<Request> ownerRequestList = requestDao.findByOwnerItemId(toDelete.getId());
            for (Request request : ownerRequestList) {
                if (!(request.getStatus() == Request.Status.REMOVED || request.getStatus() == Request.Status.TRADED)) {
                    request.setStatus(Request.Status.REMOVED);
                    requestDao.update(request);
                }
            }
            List<Request> traderRequestList = requestDao.findByTraderItemId(toDelete.getId());
            for (Request request : traderRequestList) {
                if (!(request.getStatus() == Request.Status.REMOVED || request.getStatus() == Request.Status.TRADED)) {
                    request.setStatus(Request.Status.REMOVED);
                    requestDao.update(request);
                }
            }
            return itemDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.ITEM_ERROR_DELETE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End delete");
        }
    }

    /**
     * Maps item, pictures and whether or not user has liked an item into one DTO to be sent to the front end
     *
     * @param item
     * @param pictureList
     * @param like
     * @return
     */
    private ItemViewDTO itemViewDTOMapper(Item item, List<Picture> pictureList, Like like) {
        Preconditions.checkNotNull(item);
        Preconditions.checkArgument(!pictureList.isEmpty());

        ItemViewDTO itemViewDTO = new ItemViewDTO();
        itemViewDTO.setId(item.getId());
        itemViewDTO.setUserId(item.getUserId());
        itemViewDTO.setStatus(item.getStatus().toString());
        itemViewDTO.setName(item.getName());
        itemViewDTO.setDescription(item.getDescription());
        itemViewDTO.setBrand(item.getBrand());
        itemViewDTO.setNew(item.isNew());
        itemViewDTO.setCategory(item.getCategory());
        itemViewDTO.setStrict(item.isStrict());
        itemViewDTO.setLikes(item.getLikes());
        itemViewDTO.setLiked(like != null);
        itemViewDTO.setPreferredCats(item.getPreferredCats());

        List<PictureViewDTO> pictureViewDTOList = new ArrayList<>();
        for (Picture picture : pictureList) {
            pictureViewDTOList.add(pictureViewDTOMapper(picture));
        }
        itemViewDTO.setPictureViews(pictureViewDTOList);

        List<PreferredItemDTO> preferredItemDTOList = new ArrayList<>();
        for (PreferredItem preferredItem : item.getPreferredItems()) {
            Preconditions.checkNotNull(preferredItem);
            preferredItemDTOList.add(preferredItemDTOMapper.mapEntity(preferredItem));
        }
        itemViewDTO.setPreferredItems(preferredItemDTOList);

        List<ItemHistoryDTO> itemHistoryDTOList = new ArrayList<>();
        for (ItemHistory itemHistory : item.getItemHistory()) {
            Preconditions.checkNotNull(itemHistory);
            itemHistoryDTOList.add(itemHistoryDTOMapper.mapEntity(itemHistory));
        }
        itemViewDTO.setItemHistory(itemHistoryDTOList);

        itemViewDTO.setCreatedBy(item.getCreatedBy());
        itemViewDTO.setCreatedDate(item.getCreatedDate());
        itemViewDTO.setLastModifiedBy(item.getLastModifiedBy());
        itemViewDTO.setLastModifiedDate(item.getLastModifiedDate());

        return itemViewDTO;
    }

    private PictureViewDTO pictureViewDTOMapper(Picture picture) {
        Preconditions.checkNotNull(picture);

        PictureViewDTO pictureViewDTO = new PictureViewDTO();
        pictureViewDTO.setId(picture.getId());
        pictureViewDTO.setOrder(picture.getOrder());
        pictureViewDTO.setImagePath(picture.getId().toString());
        pictureViewDTO.setDescription(picture.getDescription());

        return pictureViewDTO;
    }

    private ItemCardDTO itemCardDTOMapper(Item item, Picture picture, Like like, User user) {
        Preconditions.checkNotNull(item);
        Preconditions.checkNotNull(picture);
        Preconditions.checkNotNull(user);

        ItemCardDTO itemCardDTO = new ItemCardDTO();
        itemCardDTO.setId(item.getId());
        itemCardDTO.setImagePath(picture.getId().toString());
        itemCardDTO.setName(item.getName());
        itemCardDTO.setStatus(item.getStatus().toString());
        itemCardDTO.setBrand(item.getBrand());
        itemCardDTO.setDescription(item.getDescription());
        itemCardDTO.setNew(item.isNew());
        itemCardDTO.setLikes(item.getLikes());
        itemCardDTO.setLiked(like != null);
        itemCardDTO.setUserId(item.getUserId());
        itemCardDTO.setUserName(user.getUsername());
        itemCardDTO.setAvatarPath(user.getId().toString());
        itemCardDTO.setCreatedDate(item.getCreatedDate());
        itemCardDTO.setLastModifiedDate(item.getLastModifiedDate());

        return itemCardDTO;
    }
}
