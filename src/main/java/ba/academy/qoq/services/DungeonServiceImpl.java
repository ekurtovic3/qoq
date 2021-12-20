package ba.academy.qoq.services;

import ba.academy.qoq.dto.DungeonDto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class DungeonServiceImpl implements  DungeonService{
    @Override
    public DungeonDto createDungeon(DungeonDto input) {
        return null;
    }
}
