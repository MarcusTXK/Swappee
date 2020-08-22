package com.swappee.dao.item;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.swappee.domain.item.Item;
import com.swappee.repository.item.ItemRepository;
import com.swappee.utils.exception.BaseDaoException;
import com.swappee.utils.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * An Data Access Object implementation for managing items.
 */
@Component
public class ItemDaoImpl implements ItemDao {
    private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item findById(Long id) throws BaseDaoException {
        logger.info("Start findById - id: {}", id);
        try {
            Preconditions.checkNotNull(id);
            return itemRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_ONE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findById");
        }
    }

    @Override
    public List<Item> getAll(List<Long> ids) throws BaseDaoException {
        logger.info("Start getAll list - ids: {}", ids);
        try {
            if (ids.isEmpty()) {
                return Lists.newArrayList();
            }
            return this.itemRepository.findAllById(ids);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_LIST_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End getAll list by id");
        }
    }

    @Override
    public Page<Item> getAll(Pageable pageable) throws BaseDaoException {
        logger.info("Start getAll page - pageable: {}", pageable);
        try {
            Preconditions.checkNotNull(pageable);
            return itemRepository.findAll(pageable);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_PAGE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End getAll page");
        }
    }

    @Override
    public Page<Item> findByCategory(String category, Pageable pageable) throws BaseDaoException {
        logger.info("Start findByCategory page - category: {}, pageable: {}", category, pageable);
        try {
            Preconditions.checkNotNull(category);
            Preconditions.checkNotNull(pageable);
            return itemRepository.findByCategory(category, pageable);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_PAGE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByCategory page");
        }
    }

    @Override
    public List<Item> findByUserId(Long userId) throws BaseDaoException {
        logger.info("Start findByUserId - userId: {}", userId);
        try {
            Preconditions.checkNotNull(userId);
            return this.itemRepository.findByUserId(userId);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_LIST_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByUserId");
        }
    }

    @Override
    public Page<Item> findByUserId(Long userId, Pageable pageable) throws BaseDaoException {
        logger.info("Start findByUserId page - userId: {}, pageable: {}", userId, pageable);
        try {
            Preconditions.checkNotNull(userId);
            Preconditions.checkNotNull(pageable);
            return itemRepository.findByUserId(userId, pageable);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GET_PAGE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End findByUserId page");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public Item create(Item toCreate) throws BaseDaoException {
        logger.info("Start create - toCreate: {}", toCreate);
        try {
            Preconditions.checkNotNull(toCreate);
            return this.itemRepository.save(toCreate);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_CREATE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End create");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public Item update(Item toUpdate) throws BaseDaoException {
        logger.info("Start update - toUpdate: {}", toUpdate);
        try {
            Preconditions.checkNotNull(toUpdate);
            Preconditions.checkNotNull(toUpdate.getId());

            Item originalEntity = itemRepository.getOne(toUpdate.getId());
            originalEntity.setUserId(toUpdate.getUserId());
            originalEntity.setStatus(toUpdate.getStatus());
            originalEntity.setName(toUpdate.getName());
            originalEntity.setDescription(toUpdate.getDescription());
            originalEntity.setBrand(toUpdate.getBrand());
            originalEntity.setNew(toUpdate.isNew());
            originalEntity.setCategory(toUpdate.getCategory());
            originalEntity.setStrict(toUpdate.isStrict());
            originalEntity.setLikes(toUpdate.getLikes());
            originalEntity.setPreferredCats(toUpdate.getPreferredCats());
            originalEntity.setPreferredItems(toUpdate.getPreferredItems());
            originalEntity.setItemHistory(toUpdate.getItemHistory());

            return this.itemRepository.save(originalEntity);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_UPDATE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End update");
        }
    }

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public Item delete(Item toDelete) throws BaseDaoException {
        logger.info("Start delete - toDelete: {}", toDelete);
        try {
            Preconditions.checkNotNull(toDelete);
            Preconditions.checkNotNull(toDelete.getId());

            Item originalEntity = itemRepository.getOne(toDelete.getId());
            originalEntity.setDeleted(true);

            return this.itemRepository.save(originalEntity);
        } catch (DataAccessException dae) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_DELETE_FAILED, dae);
        } catch (Exception ex) {
            throw new BaseDaoException(ErrorCode.DB_ERROR_GENERIC, ex);
        } finally {
            logger.info("End delete");
        }
    }
}
