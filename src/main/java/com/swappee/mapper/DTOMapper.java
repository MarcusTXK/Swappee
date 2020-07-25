package com.swappee.mapper;

/**
 * Mapper for DTO
 *
 * @param <D>
 * @param <E>
 */
public interface DTOMapper<D, E> {
    /**
     * Map DTO to Entity
     *
     * @param dto
     * @return
     */
    E mapDto(D dto);

    /**
     * Map Entity to DTO
     *
     * @param entity
     * @return
     */
    D mapEntity(E entity);

}
