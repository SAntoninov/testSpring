package pojos;

public class Stocks {

    private String symbol;
    private Long volume;

    public Stocks(){
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getVolume() {
        return volume;
    }
}
