package ba.academy.qoq.dto;

public class LevelDto {
    private Integer id;
    private WeightFacotr weightFactor;
    private MapDto mapDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WeightFacotr getWeightFactor() {
        return weightFactor;
    }
    public void setWeightFactor(WeightFacotr weightFactor) {
        this.weightFactor = weightFactor;
    }
    public MapDto getMapDto() {
        return mapDto;
    }

    public void setMapDto(MapDto mapDto) {
        this.mapDto = mapDto;
    }
}
