import Models.*;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static JavascriptExecutor jse = null;
    static Gson gson = new Gson();
    static EdgeOptions edgeOptions = null;
    //edgeOptions.addArguments("headless");
    static WebDriver webDriver = null;
    static int count = 2;
    static List<IndustryInfo> industryInfos = new ArrayList<>(count);


    public static void initWebDriver() {
        System.setProperty("webdriver.edge.driver", "driver\\msedgedriver.exe");
        edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("headless");
        webDriver = new EdgeDriver(edgeOptions);
    }

    public static void main(String[] args) throws InterruptedException {
        initWebDriver();
        YahooParser yahooParser = new YahooParser();
        IndeedParser indeedParser = new IndeedParser();
        industryInfos = yahooParser.yahooIndustries(webDriver,industryInfos,count);
        industryInfos = yahooParser.dataParsing(webDriver,industryInfos);
        industryInfos = indeedParser.dataParsing(webDriver,industryInfos);

        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("companyInfos");
            MongoCollection<Document> collection = database.getCollection("informations");

            List<IndustryInfoMongo> mongos = new ArrayList<>(industryInfos.size());

            for (int i = 0; i < industryInfos.size(); i++) {
                mongos.add(new IndustryInfoMongo());
                industryInfos.get(i).getIndustryYahooInfo().pushing(mongos.get(i));
                industryInfos.get(i).getIndustryIndeedInfo().pushing(mongos.get(i));
            }
            collection.insertMany(mongos);
            }
            catch (Exception e){
                System.out.println("Отсутствует подключение к бд");
            }
        }

}
