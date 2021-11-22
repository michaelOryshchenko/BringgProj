public class Groups {
    private int capacity;
    private String country;
    private Object site;

    public Groups(int capacity, String country, Object site) {
        this.capacity = capacity;
        this.country = country;
        this.site = site;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCountry() {
        return country;
    }

    public Object getSite() {
        return site;
    }

    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    public void setCountry(String newCountry) {
        this.country = newCountry;
    }

    public void setSite(Object newSite) {
        this.site = newSite;
    }

}
