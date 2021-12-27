package ba.academy.qoq.services;

import ba.academy.qoq.dto.*;

public interface GameSerivce {
    GameDto createGame(WeightFacotr weightFacotr);
    DungeonDto move(int id);
    int fight(int id);
    DungeonDto flee(int id);
    PlayerDto heal(int id);

}
