package Models;

import org.openqa.selenium.WebDriver;

import java.util.List;

public interface IIndustriesWebWorkable {
    public List<IndustryInfo> dataParsing(WebDriver webDriver, List<IndustryInfo> industries)throws InterruptedException;
}
