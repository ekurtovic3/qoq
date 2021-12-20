package ba.academy.qoq.dto;

public class QoqDto extends ItemDto{

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public DungeonDto getDungeon() {
        return super.getDungeon();
    }

    @Override
    public void setDungeon(DungeonDto dungeon) {
        super.setDungeon(dungeon);
    }


}
