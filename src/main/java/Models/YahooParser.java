package Models;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class YahooParser implements IIndustriesWebWorkable{

    public List<IndustryInfo> yahooIndustries(WebDriver webDriver, List<IndustryInfo> industries, Integer count) throws InterruptedException {

        String json = null;
        JavascriptExecutor jse= (JavascriptExecutor) webDriver;
        Gson gson = new Gson();

        webDriver.get("https://finance.yahoo.com/screener/predefined/ms_industrials/?offset=0&count=100");
        WebElement webElement = new WebDriverWait(webDriver, Duration
                .ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"screener-criteria\"]/div[2]/div[1]/div/button[1]")));

        jse = (JavascriptExecutor) webDriver;
        jse.executeScript("window.scrollBy(0,4000)");

        json = jse.executeScript(
                        "let result = [];\n" +
                                "for (let i = 1; i <= " + count + "; i++) {\n" +
                                "    result.push({\n" +
                                "        'name': document.querySelector('#scr-res-table " +
                                "> div.Ovx\\\\(a\\\\).Ovx\\\\(h\\\\)--print.Ovy\\\\(h\\\\).W\\\\(100\\\\%\\\\) " +
                                "> table > tbody > tr:nth-child('+ i +')').children[1].textContent,\n" +
                                "        'urlYahoo': 'https://finance.yahoo.com/'+document.querySelector('#scr-res-table " +
                                "> div.Ovx\\\\(a\\\\).Ovx\\\\(h\\\\)--print.Ovy\\\\(h\\\\).W\\\\(100\\\\%\\\\) " +
                                "> table > tbody > tr:nth-child('+ i +')> td> a').getAttribute('href')" +
                                ".split('?').join('/profile?'),\n" +
                                "    });\n" +
                                "}\n" +
                                "return JSON.stringify(result);")
                .toString();

        IndustryYahooInfo[] industriesYahoo = gson.fromJson(json, IndustryYahooInfo[].class);

        for (int i = 0; i < industriesYahoo.length; i++) {
            industries.add(new IndustryInfo());
            industries.get(i).setIndustryYahooInfo(industriesYahoo[i]);
        }


        return industries;
    }


    @Override
    public List<IndustryInfo> dataParsing(WebDriver webDriver, List<IndustryInfo> industries) throws InterruptedException {
        String json = null;
        JavascriptExecutor jse= (JavascriptExecutor) webDriver;
        Gson gson = new Gson();

        for (int i = 0; i < industries.size(); i++) {
            Thread.sleep(2000);
            webDriver.get(industries.get(i).getIndustryYahooInfo().getUrlYahoo());

            jse.executeScript("window.scrollBy(0,4000)");
            Thread.sleep(2000);

            json = jse.executeScript(
                    "let result = {\n" +
                            "    'number': " + i + "," +
                            "    'name': '" + industries.get(i).getIndustryYahooInfo().getName() + "',\n" +
                            "    'urlYahoo': '" + industries.get(i).getIndustryYahooInfo().getUrlYahoo() + "',\n" +
                            "    'description': document.querySelector('#Col1-0-Profile-Proxy > section " +
                            "> section.quote-sub-section.Mt\\\\(30px\\\\) > p').textContent,\n" +
                            "    'corporateGovernance': document.querySelector('#Col1-0-Profile-Proxy > section " +
                            "> section.Mt\\\\(30px\\\\).corporate-governance-container > div').textContent\n" +
                            "};\n" +
                            "return JSON.stringify(result);").toString();

            System.out.println(i + ": " + json);
            industries.get(i).setIndustryYahooInfo(gson.fromJson(json, IndustryYahooInfo.class));
        }

        return industries;
    }
}
