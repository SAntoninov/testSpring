package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {

    private String sector;
    private Float latestPrice;

    public Asset() {
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public float getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(float latestPrice) {
        this.latestPrice = latestPrice;
    }

    @Override
    public String toString() {
        return "Current{" +
                "sector='" + sector + '\'' + ", " +
                "latestPrice='" + latestPrice + '\'' +
                '}';
    }
}