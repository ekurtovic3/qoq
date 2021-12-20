package ba.academy.qoq.dto;

public class GameDto {
    private Integer id;
    private PlayerDto playerDto;
    private LevelDto levelDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlayerDto getPlayerDto() {
        return playerDto;
    }

    public void setPlayerDto(PlayerDto playerDto) {
        this.playerDto = playerDto;
    }

    public LevelDto getLevelDto() {
        return levelDto;
    }

    public void setLevelDto(LevelDto levelDto) {
        this.levelDto = levelDto;
    }
}
