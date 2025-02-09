package cn.ctkqiang.lianjiascraper.models;

public class House {
    private String title;
    private String price;
    private String location;
    private String imageUrl;
    private String url;

    public House(String title, String price, String location, String imageUrl, String url) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    // Add getter for url
    public String getUrl() {
        return url;
    }

    // Add setter for url
    public void setUrl(String url) {
        this.url = url;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
