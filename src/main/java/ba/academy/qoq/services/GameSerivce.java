package ba.academy.qoq.services;

import ba.academy.qoq.dto.GameDto;
import ba.academy.qoq.dto.WeightFacotr;

public interface GameSerivce {
    GameDto createGame(WeightFacotr weightFacotr);
}
