package pojos;

public class Allocation {

    private String sector;
    private Float assetValue;
    private Float proportion;

    public Allocation(){
    }

    public void setProportion(Float proportion) {
        this.proportion = proportion;
    }

    public Float getProportion() {
        return proportion;
    }

    public void setAssetValue(Float assetValue) {
        this.assetValue = assetValue;
    }

    public Float getAssetValue() {
        return assetValue;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }
}
