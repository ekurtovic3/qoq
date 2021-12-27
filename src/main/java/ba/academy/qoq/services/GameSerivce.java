package ba.academy.qoq.services;

import ba.academy.qoq.dto.DungeonDto;
import ba.academy.qoq.dto.GameDto;
import ba.academy.qoq.dto.MapDto;
import ba.academy.qoq.dto.WeightFacotr;

public interface GameSerivce {
    GameDto createGame(WeightFacotr weightFacotr);
    DungeonDto move(int id);
    int fight(int id);
    DungeonDto flee(int id);
}
