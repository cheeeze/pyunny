package ssafy.d102.pyunny.vo;

public class MapSearchResult {
    private int id;
    private int storeId;
    private int productId;
    private String name;
    private String stockAmount;
    private int price;
    private String image;
    private Store store;

    public MapSearchResult(int id, int storeId, int productId, String name, String stockAmount, int price, String image, Store store) {
        this.id = id;
        this.storeId = storeId;
        this.productId = productId;
        this.name = name;
        this.stockAmount = stockAmount;
        this.price = price;
        this.image = image;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(String stockAmount) {
        this.stockAmount = stockAmount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "MapSearchResult{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ", stockAmount='" + stockAmount + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", store=" + store +
                '}';
    }
}
