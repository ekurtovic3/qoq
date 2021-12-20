package ba.academy.qoq.services;

import ba.academy.qoq.dto.PlayerDto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PlayerSerivceImpl implements  PlayerSerivce {

    @Override
    public PlayerDto createPlayer(PlayerDto input) {
        return null;
    }
}
