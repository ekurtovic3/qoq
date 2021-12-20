package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.ItemDto;
import ba.academy.qoq.dto.MonsterDto;
import ba.academy.qoq.repository.entities.ItemEntitiy;
import ba.academy.qoq.repository.entities.MonsterEntity;

import java.util.Collection;
import java.util.List;

public class ItemDtoTransformer  implements  DtoTransformer<ItemEntitiy, ItemDto>{

    @Override
    public ItemDto toDto(ItemEntitiy entity) {
        //TODO: Uraditi
        ItemDto itemDto=new ItemDto();
        return  itemDto;

    }

    @Override
    public ItemEntitiy toEntity(ItemDto dto, ItemEntitiy entityInstance) {
        //TODO: Uraditi
        return entityInstance;
    }

    @Override
    public List<ItemDto> toDtoList(Collection<ItemEntitiy> resultEntities) {
        return DtoTransformer.super.toDtoList(resultEntities);
    }
}



