package solekia.demo.fastfood.model;

/**
 * ShopPageModel
 */

import java.util.*;
import solekia.demo.fastfood.repository.ShopModel; 

public class ShopPageModel {
    public String web_title;
    public String hold_name = "";
    public List<ShopModel> list;

    //店舗のIDと名前
    public int shop_id;
    public String shop_name;
    
    //営業時間と住所
    public String business_hours;
    public String address;

    //グーグルマップのリンク
    public String google_link;

    //追加
    public int login;
    public int authority;

    public int getShop_id(){
        return shop_id;
    }

    public void setShop_id(int shop_id){
        this.shop_id = shop_id;
    }

    public String getShop_name(){
        return shop_name;
    }

    public void setShop_name(String shop_name){
        this.shop_name = shop_name;
    }

    public String getBusiness_hours(){
        return business_hours;
    }

    public void setBusiness_hours(String business_hours){
        this.business_hours = business_hours;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getGoogle_link(){
        return google_link;
    }

    public void setGoogle_link(String google_link){
        this.google_link = google_link;
    }
}