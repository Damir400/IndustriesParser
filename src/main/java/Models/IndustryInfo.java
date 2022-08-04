package Models;

import org.bson.Document;

public class IndustryInfo {

    private IndustryYahooInfo industryYahooInfo;
    private IndustryIndeedInfo industryIndeedInfo;

//    public IndustryInfo(IndustryYahooInfo industryYahooInfo, IndustryIndeedInfo industryIndeedInfo) {
//        this.industryYahooInfo = industryYahooInfo;
//        this.industryIndeedInfo = industryIndeedInfo;
//    }
//
//    public IndustryInfo(IndustryYahooInfo industryYahooInfo) {
//        this.industryYahooInfo = industryYahooInfo;
//    }

    public IndustryYahooInfo getIndustryYahooInfo() {
        return industryYahooInfo;
    }

    public void setIndustryYahooInfo(IndustryYahooInfo industryYahooInfo) {
        this.industryYahooInfo = industryYahooInfo;
    }

    public IndustryIndeedInfo getIndustryIndeedInfo() {
        return industryIndeedInfo;
    }

    public void setIndustryIndeedInfo(IndustryIndeedInfo industryIndeedInfo) {
        this.industryIndeedInfo = industryIndeedInfo;
    }

    @Override
    public String toString() {
        return "********************************************************\n" +
                "IndustryInfo {" +
                "  industryYahooInfo=" + industryYahooInfo +
                ", industryIndeedInfo=" + industryIndeedInfo +
                '}';
    }
}
