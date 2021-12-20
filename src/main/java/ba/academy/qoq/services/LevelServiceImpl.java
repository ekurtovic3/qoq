package ba.academy.qoq.services;

import ba.academy.qoq.dto.LevelDto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class LevelServiceImpl implements LevelService{
    @Override
    public LevelDto createLevel(LevelDto input) {
        return null;
    }
}
