package Service;

import Models.IndustryIndeedInfo;
import Models.IndustryInfo;
import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class IndeedParser implements IIndustriesWebWorkable{


    @Override
    public List<IndustryInfo> dataParsing(WebDriver webDriver, List<IndustryInfo> industries) throws InterruptedException {
        String json = null;
        JavascriptExecutor jse= (JavascriptExecutor) webDriver;
        Gson gson = new Gson();

        for (int i = 0; i < industries.size(); i++) {
            webDriver.get("https://www.indeed.com/companies/search");
            Thread.sleep(2000);
            WebElement webElement = new WebDriverWait(webDriver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id='main']/div/div[1]/form/div/div[2]/button")));

            jse.executeScript(
                    "document.querySelector('#ifl-InputFormField-3').value='"
                            + industries.get(i).getIndustryYahooInfo().getName() + "';\n" +
                            "document.querySelector('#main > div > div.css-19t9h4o.eu4oa1w0 > form > div " +
                            "> div.css-1fv2pee.eu4oa1w0 > button').click();\n"
            );
            Thread.sleep(2000);

            jse.executeScript(
                    "let item = document.querySelector('#main > div > div.css-evzha8.eu4oa1w0 > section > div " +
                            "> div > div > div >a');\n" +
                            "if (item) {\n" +
                            "    item.click();\n" +
                            "}");

            json = jse.executeScript(
                    "let result = {\n" +
                            "    'number': " + industries.get(i).getIndustryYahooInfo().getNumber() + ",\n" +
                            "    'ceo': '',\n" +
                            "    'founded': '',\n" +
                            "    'companySize': '',\n" +
                            "    'revenue': '',\n" +
                            "    'industry': '',\n" +
                            "    'headquarters': '',\n" +
                            "    'link': ''\n" +
                            "};\n" +

                            "let item = document.querySelector('#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 " +
                            "> main > div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 " +
                            "> div > ul > li:nth-child(1)');\n" +
                            "if (item && item.outerText.startsWith('CEO')) {\n" +
                            "    result.ceo = item.outerText.split('\\n')[1];\n" +
                            "}\n" +

                            "item = document.querySelector('#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main " +
                            "> div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div " +
                            "> ul > li:nth-child(2)');\n" +
                            "if (item && item.outerText.startsWith('Founded')) {\n" +
                            "    result.founded = item.outerText.split('\\n')[1];\n" +
                            "}\n" +

                            "item = document.querySelector('#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main " +
                            "> div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div " +
                            "> ul > li:nth-child(3)');\n" +
                            "if (item && item.outerText.startsWith('Company size')) {\n" +
                            "    result.companySize = item.outerText.split('\\n')[1] + ' ' + item.outerText.split('\\n')[2];\n" +
                            "}\n" +

                            "item = document.querySelector('#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main " +
                            "> div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div " +
                            "> ul > li:nth-child(4)');\n" +
                            "if (item && item.outerText.startsWith('Revenue')) {\n" +
                            "    result.revenue = item.outerText.split('\\n')[1] + ' ' + item.outerText.split('\\n')[2];\n" +
                            "}\n" +

                            "item = document.querySelector('#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main " +
                            "> div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div " +
                            "> ul > li:nth-child(5)');\n" +
                            "if (item && item.outerText.startsWith('Industry')) {\n" +
                            "    result.industry = item.outerText.split('\\n')[1];\n" +
                            "}\n" +

                            "item = document.querySelector('#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main " +
                            "> div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div " +
                            "> ul > li:nth-child(6)');\n" +
                            "if (item && item.outerText.startsWith('Headquarters')) {\n" +
                            "    result.headquarters = item.outerText.split('\\n')[1];\n" +
                            "}\n" +

                            "item = document.querySelector('#cmp-container > div > div.i-unmask.css-19u5gih.eu4oa1w0 > main " +
                            "> div:nth-child(2) > div:nth-child(2) > section > div.css-o5bvla.eu4oa1w0 > div " +
                            "> ul > li:nth-child(7)');\n" +
                            "if (item && item.outerText.startsWith('Link')) {\n" +
                            "    result.link = item.outerText.split('\\n')[1];\n" +
                            "}\n" +

                            "return JSON.stringify(result);").toString();

            industries.get(i).setIndustryIndeedInfo(gson.fromJson(json, IndustryIndeedInfo.class));
        }

        return industries;
    }
}
