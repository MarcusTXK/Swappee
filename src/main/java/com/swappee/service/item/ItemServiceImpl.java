package com.swappee.service.item;

import com.google.common.base.Preconditions;
import com.swappee.dao.item.ItemDao;
import com.swappee.dao.like.LikeDao;
import com.swappee.dao.picture.PictureDao;
import com.swappee.dao.user.UserDao;
import com.swappee.domain.item.Item;
import com.swappee.domain.item.ItemHistory;
import com.swappee.domain.item.PreferredItem;
import com.swappee.domain.like.Like;
import com.swappee.domain.picture.Picture;
import com.swappee.mapper.item.ItemDTOMapper;
import com.swappee.mapper.item.ItemHistoryDTOMapper;
import com.swappee.mapper.item.PreferredItemDTOMapper;
import com.swappee.model.item.*;
import com.swappee.model.picture.PictureViewDTO;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.BaseServiceException;
import com.swappee.utils.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    ItemDTOMapper itemDTOMapper;

    @Autowired
    PreferredItemDTOMapper preferredItemDTOMapper;

    @Autowired
    ItemHistoryDTOMapper itemHistoryDTOMapper;

    @Override
    public ItemDTO findItemById(Long id) throws BaseServiceException {
        try {
            logger.info("Start findItemById - id: {}", id);
            Preconditions.checkNotNull(id);
            ItemDTO itemDTO = itemDTOMapper.mapEntity(itemDao.findById(id));
            Preconditions.checkNotNull(itemDTO);
            return itemDTO;
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findItemById");
        }
    }

    @Override
    public ItemViewDTO findItemViewById(Long id) throws BaseServiceException {
        try {
            logger.info("Start findItemViewById - id: {}", id);
            Preconditions.checkNotNull(id);
            Item item = itemDao.findById(id);
            Preconditions.checkNotNull(item);
            List<Picture> pictureList = pictureDao.findByItemId(id);
            Like like = likeDao.findByUserIdAndItemId(item.getUserId(), id);
            logger.debug("like: {}", like);
            return itemViewDTOMapper(item, pictureList, like);
        } catch (BaseDaoException bde) {
            throw new BaseServiceException(ErrorMessage.USER_ERROR_GET_ONE_FAILED, bde);
        } catch (Exception ex) {
            throw new BaseServiceException(ErrorMessage.SVC_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findItemViewById");
        }
    }

    @Override
    public List<ItemCardDTO> getAll(List<Long> ids) {
        return null;
    }

    @Override
    public Page<ItemCardDTO> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ItemDTO> findByUserId(Long userId) {
        return null;
    }

    @Override
    public Page<ItemCardDTO> findByUserId(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public ItemDTO create(ItemDTO toCreate) {
        return null;
    }

    @Override
    public ItemDTO update(ItemDTO toUpdate) {
        return null;
    }

    //Delete item and likes
    @Override
    public ItemDTO delete(ItemDTO toDelete) {
        return null;
    }

    /**
     * Maps item, pictures and whether or not user has liked an item into one DTO to be sent to the front end
     * TODO cache in the future
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
        pictureViewDTO.setDescription(pictureViewDTO.getDescription());
        return pictureViewDTO;
    }
}
