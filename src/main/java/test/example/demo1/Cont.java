package test.example.demo1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pojos.Allocation;
import pojos.Asset;
import pojos.Output;
import pojos.Stocks;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

@RestController
public class Cont {

    private Float sum = 0f;

    @RequestMapping("/")
    public Output index(RestTemplate restTemplate) throws IOException, ParseException {

        List<Stocks> stocksArray = getStocks();
        List<Allocation> allocations = new ArrayList<>();

        // по каждому элементу запрашиваем актуальное значение через. Вычисляем необходимые значения.
        stocksArray.forEach(t -> {
            String uri = "https://api.iextrading.com/1.0/stock/"
                    + t.getSymbol()
                    + "/quote";
            Asset asset = restTemplate.getForObject(uri, Asset.class);

            Allocation allocation = new Allocation();
            allocation.setAssetValue(asset.getLatestPrice()*t.getVolume());
            sum += allocation.getAssetValue();
            allocation.setSector(asset.getSector());
            allocations.add(allocation);
        });

        allocations.forEach(t -> t.setProportion(t.getAssetValue()/sum));
        Output output = new Output();
        output.setValue(sum);
        output.setAllocations(allocations);
        return output;
    }

    /**
     * Parse json-data from resource and return array of input data
     * @return
     * @throws IOException
     * @throws ParseException
     * @throws URISyntaxException
     */
    List<Stocks> getStocks() throws IOException, ParseException{
        List<Stocks> stocksArrayList = new ArrayList<>();
        InputStream url = Cont.class.getClassLoader().getResourceAsStream("input");
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(new InputStreamReader(url));
        JSONArray stocks = (JSONArray) jsonObject.get("stocks");

        stocks.forEach(s -> {
            Stocks stock = new Stocks();
            stock.setSymbol((String) ((JSONObject)s).get("symbol"));
            stock.setVolume((Long) ((JSONObject)s).get("volume"));
            stocksArrayList.add(stock);
        });

        return stocksArrayList;
    }

}