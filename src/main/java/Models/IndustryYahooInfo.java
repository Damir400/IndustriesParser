package Models;

import org.bson.Document;

public class IndustryYahooInfo implements IMongoWorkable {

    private int number;
    private String name;
    private String description;
    private String corporateGovernance;
    private String urlYahoo;
//    private String description;
//    private String description;
//    private String description;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorporateGovernance() {
        return corporateGovernance;
    }

    public void setCorporateGovernance(String corporateGovernance) {
        this.corporateGovernance = corporateGovernance;
    }

    public String getUrlYahoo() {
        return urlYahoo;
    }

    public void setUrlYahoo(String urlYahoo) {
        this.urlYahoo = urlYahoo;
    }

    @Override
    public String toString() {
        return "IndustryYahooInfo{" +
                "name='" + name + '\'' +
                "description='" + description + '\'' +
                ", corporateGovernance='" + corporateGovernance + '\'' +
                ", urlYahoo='" + urlYahoo + '\'' +
                '}';
    }

    @Override
    public void pushing(Document document) {
        document.put("name", getName());
        document.put("urlYahoo", getUrlYahoo());
        document.put("corporateGovernance", getCorporateGovernance());
        document.put("description", getDescription());
        document.put("numberYahoo", getNumber());
    }

    @Override
    public void parsing(Document document) {
        setName((String) document.get("name"));
        setUrlYahoo((String) document.get("urlYahoo"));
        setCorporateGovernance((String) document.get("corporateGovernance"));
        setDescription((String) document.get("description"));
        setNumber((Integer) document.get("numberYahoo"));
    }
}
