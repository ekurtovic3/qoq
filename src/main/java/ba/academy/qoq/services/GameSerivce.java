package ba.academy.qoq.services;

import ba.academy.qoq.dto.*;

public interface GameSerivce {
    GameDto createGame(WeightFacotr weightFacotr);
    GameDto move(int id);
    GameDto fight(int id);
    GameDto flee(int id);
    PlayerDto heal(int id);

}
