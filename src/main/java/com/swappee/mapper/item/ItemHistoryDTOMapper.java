package com.swappee.mapper.item;

import com.swappee.domain.item.ItemHistory;
import com.swappee.mapper.DTOMapper;
import com.swappee.model.item.ItemHistoryDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemHistoryDTOMapper implements DTOMapper<ItemHistoryDTO, ItemHistory> {

    @Override
    public ItemHistoryDTO mapEntity(ItemHistory entity) {
        if (entity == null) {
            return null;
        }

        ItemHistoryDTO dto = new ItemHistoryDTO();
        dto.setPrevOwnerUsername(entity.getPrevOwnerUsername());
        dto.setTradedItemId(entity.getTradedItemId());
        dto.setTradedItemName(entity.getTradedItemName());

        return dto;
    }

    @Override
    public ItemHistory mapDto(ItemHistoryDTO dto) {
        if (dto == null) {
            return null;
        }

        ItemHistory entity = new ItemHistory();
        entity.setPrevOwnerUsername(dto.getPrevOwnerUsername());
        entity.setTradedItemId(dto.getTradedItemId());
        entity.setTradedItemName(dto.getTradedItemName());

        return entity;
    }
}
