package ssafy.d102.pyunny.vo;

public class Store {
    private int id;
    private int franchise_id;
    private String franchise_name;
    private String store_name;
    private double latitude;
    private double longitude;
    private String address;
    private String city;
    private String tel;
    private int isatm;
    private int islottery;
    private int isdelivery;
    private int isfulltime;
    private String logo_url;
    private String delivery_begin;
    private String delivery_end;

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", franchise_id=" + franchise_id +
                ", franchise_name='" + franchise_name + '\'' +
                ", store_name='" + store_name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", tel='" + tel + '\'' +
                ", isatm=" + isatm +
                ", islottery=" + islottery +
                ", isdelivery=" + isdelivery +
                ", isfulltime=" + isfulltime +
                ", logo_url='" + logo_url + '\'' +
                ", delivery_begin='" + delivery_begin + '\'' +
                ", delivery_end='" + delivery_end + '\'' +
                '}';
    }

    public Store(int id, int franchise_id, String franchise_name, String store_name, double latitude, double longitude, String address, String city, String tel, int isatm, int islottery, int isdelivery, int isfulltime, String logo_url, String delivery_begin, String delivery_end) {
        this.id = id;
        this.franchise_id = franchise_id;
        this.franchise_name = franchise_name;
        this.store_name = store_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.tel = tel;
        this.isatm = isatm;
        this.islottery = islottery;
        this.isdelivery = isdelivery;
        this.isfulltime = isfulltime;
        this.logo_url = logo_url;
        this.delivery_begin = delivery_begin;
        this.delivery_end = delivery_end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFranchise_id() {
        return franchise_id;
    }

    public void setFranchise_id(int franchise_id) {
        this.franchise_id = franchise_id;
    }

    public String getFranchise_name() {
        return franchise_name;
    }

    public void setFranchise_name(String franchise_name) {
        this.franchise_name = franchise_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIsatm() {
        return isatm;
    }

    public void setIsatm(int isatm) {
        this.isatm = isatm;
    }

    public int getIslottery() {
        return islottery;
    }

    public void setIslottery(int islottery) {
        this.islottery = islottery;
    }

    public int getIsdelivery() {
        return isdelivery;
    }

    public void setIsdelivery(int isdelivery) {
        this.isdelivery = isdelivery;
    }

    public int getIsfulltime() {
        return isfulltime;
    }

    public void setIsfulltime(int isfulltime) {
        this.isfulltime = isfulltime;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getDelivery_begin() {
        return delivery_begin;
    }

    public void setDelivery_begin(String delivery_begin) {
        this.delivery_begin = delivery_begin;
    }

    public String getDelivery_end() {
        return delivery_end;
    }

    public void setDelivery_end(String delivery_end) {
        this.delivery_end = delivery_end;
    }
}
