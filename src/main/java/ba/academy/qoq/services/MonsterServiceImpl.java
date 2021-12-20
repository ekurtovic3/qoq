package ba.academy.qoq.services;

import ba.academy.qoq.dto.MonsterDto;
import ba.academy.qoq.repository.entities.MonsterEntity;
import ba.academy.qoq.repository.transformer.MonsterDtoTransformer;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MonsterServiceImpl implements MonsterService{
    private MonsterDtoTransformer monsterDtoTransformer = new MonsterDtoTransformer();

    @Override
    public MonsterDto createMonster(MonsterDto input) {
        final MonsterEntity monsterEntity = monsterDtoTransformer.toEntity(input, new MonsterEntity());
        monsterEntity.setDamage(input.getDamage());
       // monsterEntity.setDungeon();





       // return patientDtoTransformer.toDto(patientEntity);
    return null; //TODO: NULL!!
    }
}
