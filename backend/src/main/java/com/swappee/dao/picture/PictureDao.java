package com.swappee.dao.picture;

import com.swappee.domain.picture.Picture;
import com.swappee.utils.exception.BaseDaoException;

import java.util.List;

/**
 * Data Access interface for managing pictures.
 */
public interface PictureDao {
    Picture findById(Long id) throws BaseDaoException;

    Picture findByItemIdAndOrderIs(Long itemId, Long order) throws BaseDaoException;

    List<Picture> findByItemId(Long itemId) throws BaseDaoException;

    List<Picture> create(List<Picture> toCreates) throws BaseDaoException;

    List<Picture> update(List<Picture> toUpdates) throws BaseDaoException;
}
