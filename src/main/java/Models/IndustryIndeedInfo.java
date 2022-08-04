package Models;

import org.bson.Document;

public class IndustryIndeedInfo implements IMongoWorkable{
    private int number;
    private String ceo;
    private String founded;
    private String companySize;
    private String revenue;
    private String industry;
    private String headquarters;
    private String link;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "IndustryIndeedInfo {" +
                "ceo='" + ceo + '\'' +
                ", founded='" + founded + '\'' +
                ", companySize='" + companySize + '\'' +
                ", revenue='" + revenue + '\'' +
                ", industry='" + industry + '\'' +
                ", headquarters='" + headquarters + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public void pushing(Document document) {
        document.put("number", getNumber());
        document.put("ceo", getCeo());
        document.put("founded", getFounded());
        document.put("companySize", getCompanySize());
        document.put("revenue", getRevenue());
        document.put("industry", getIndustry());
        document.put("headquarters", getHeadquarters());
        document.put("link", getLink());
    }

    @Override
    public void parsing(Document document) {
        setNumber((Integer) document.get("name"));
        setCeo((String) document.get("ceo"));
        setFounded((String) document.get("founded"));
        setCompanySize((String) document.get("companySize"));
        setRevenue((String) document.get("revenue"));
        setIndustry((String) document.get("industry"));
        setHeadquarters((String) document.get("headquarters"));
        setLink((String) document.get("link"));
    }
}
