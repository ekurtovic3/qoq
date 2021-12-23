package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.*;
import ba.academy.qoq.repository.entities.*;

import java.util.Collection;
import java.util.List;

public class ItemDtoTransformer implements DtoTransformer<ItemEntitiy, ItemDto> {

    @Override
    public ItemDto toDto(ItemEntitiy entity) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(entity.getId());
        return null;

    }

    @Override
    public ItemEntitiy toEntity(ItemDto dto, ItemEntitiy entityInstance) {
        entityInstance.setId(dto.getId());
        return null;
    }

}



