package ba.academy.qoq.services;

import ba.academy.qoq.dto.LevelDto;
import ba.academy.qoq.dto.MapDto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MapServiceImpl implements MapSerivce{
    @Override
    public MapDto createLevel(LevelDto input) {
        return null;
    }
}
