package test.example.demo1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pojos.Allocation;
import pojos.Output;
import pojos.Qute;
import pojos.Stocks;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@RestController
public class Cont {

    @RequestMapping("/")
    public Output index(RestTemplate restTemplate) throws IOException, ParseException {

        ArrayList<Stocks> stocksArray = getStocks();
        ArrayList<Allocation> allocations = new ArrayList<>();
        Float sum = 0f;

        for (int i = 0; i < stocksArray.size(); i++) {
            String res = "https://api.iextrading.com/1.0/stock/"
                    + stocksArray.get(i).getSymbol()
                    + "/quote";
            Qute quote = restTemplate.getForObject(res, Qute.class);

            Allocation allocation = new Allocation();
            allocation.setAssetValue(quote.getLatestPrice()*stocksArray.get(i).getVolume());
            sum += allocation.getAssetValue();
            allocation.setSector(quote.getSector());
            allocations.add(allocation);
        }

        for (int i = 0; i < allocations.size(); i++) {
            allocations.get(i).setProportion(allocations.get(i).getAssetValue()/sum);
        }

        Output output = new Output();
        output.setValue(sum);
        output.setAllocations(allocations);

        return output;
    }

    ArrayList<Stocks> getStocks() throws IOException, ParseException {
        // мда, кажись не тяну я на нормального программиста =[
        ArrayList<Stocks> stocksArrayList = new ArrayList<>();

        // тут надо с хардкодом пути разобраться, по-нормальному он вродь через класслоадер достается их архива,
        // щас не помню точно. Надо гуглить. Да и вообще, наверное, можно и данные не из захардкоженного jsona цеплять,
        // а методами спринга из rest сервиса(как по умолчанию в условии задачи), какого-нибудь другого или этого. В общем тоже надо в мануалы лезть.
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("E:\\IdeaProjects\\demo1\\src\\main\\resources\\input"));
        JSONArray stocks = (JSONArray) jsonObject.get("stocks");
        Iterator iterator = stocks.iterator();
        Iterator<Map.Entry> inner;

        // здесь тоже та еще грязь
        // TODO посмотреть как покрасивее переписать
        while (iterator.hasNext()){
            inner = ((Map) iterator.next()).entrySet().iterator();
            Stocks s = new Stocks();
            s.setVolume((Long) inner.next().getValue());
            s.setSymbol((String) inner.next().getValue());
            stocksArrayList.add(s);
        }
        return stocksArrayList;
    }

}