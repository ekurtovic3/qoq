package ba.academy.qoq.services;

import ba.academy.qoq.dto.LevelDto;
import ba.academy.qoq.dto.MapDto;

public interface MapSerivce {
    MapDto createLevel(LevelDto input);
}
