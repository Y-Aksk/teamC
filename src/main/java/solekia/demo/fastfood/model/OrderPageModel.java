package solekia.demo.fastfood.model;

/**
 * OrderPageModel
 */

import java.util.*;
import solekia.demo.fastfood.repository.OrderModel; 

public class OrderPageModel {
    public String web_title;
    public int hold_id;
    public int sumPrice = 0;
    public int sumNumber = 0;
    public List<OrderModel> list;

    //チェックボックスはStringの配列で受け取れるらしい
    // public String[] checked;
    public List<String> checked;
    public List<String> orderCnt;
    public List<String> proIds;
    public List<String> bgNames;
    public List<String> prices;
    // public List<String> orderCntTrue;

    //商品のIDと名前
    public int product_id;
    public String product_name;
    
    //価格と売り上げ
    public int price;
    public int sales;

    //商品のアレルギー名
    public boolean flour;
    public boolean egg;
    public boolean milk;
    public boolean shrimp;
    public boolean crab;
    public boolean peanut;

    //商品画像のリンク
    public String product_pic;

    public int customer_id;
    public int number;
    
    public int getProduct_id(){
        return product_id;
    }

    public void setProduct_id(int product_id){
        this.product_id = product_id;
    }

    public String getProduct_name(){
        return product_name;
    }

    public void setProduct_name(String product_name){
        this.product_name = product_name;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getSales(){
        return sales;
    }

    public void setSales(int sales){
        this.sales = sales;
    }

    public boolean getFlour(){
        return flour;
    }

    public void setFlour(boolean flour){
        this.flour = flour;
    }

    public boolean getEgg(){
        return egg;
    }

    public void setEgg(boolean egg){
        this.egg = egg;
    }

    public boolean getMilk(){
        return milk;
    }

    public void setMilk(boolean milk){
        this.milk = milk;
    }

    public boolean getShrimp(){
        return shrimp;
    }

    public void setShrimp(boolean shrimp){
        this.shrimp = shrimp;
    }

    public boolean getCrab(){
        return crab;
    }

    public void setCrab(boolean crab){
        this.crab = crab;
    }

    public boolean getPeanut(){
        return peanut;
    }

    public void setPeanut(boolean peanut){
        this.peanut = peanut;
    }

    public String getProduct_pic(){
        return product_pic;
    }

    public void setProduct_pic(String product_pic){
        this.product_pic = product_pic;
    }

    public int getCustomer_id(){
        return customer_id;
    }

    public void setCustomer_id(int customer_id){
        this.customer_id = customer_id;
    }

    public List<String> getChecked() {
        return checked;
    }

    public void setChecked(List<String> checked) {
        this.checked = checked;
    }

    public List<String> getOrderCnt(){
        return orderCnt;
    }

    public void setOrderCnt(List<String> orderCnt){
        this.orderCnt = orderCnt;
    }
    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public List<String> getProIds(){
        return proIds;
    }
    public void setProIds(List<String> proIds){
        this.proIds = proIds;
    }

    public List<String> getBgNames(){
        return bgNames;
    }

    public void setBgNames(List<String> bgNames){
        this.bgNames = bgNames;
    }
        
    public List<String> getPrices(){
        return prices;
    }
    public void setPrices(List<String> prices){
        this.prices = prices;
    }

    public int getSumPrice(){
        return sumPrice;
    }

    public void setSumPrice(int sumPrice){
        this.sumPrice = sumPrice;
    }

    public int getSumNumber(){
        return sumNumber;
    }

    public void setSumNumber(int sumNumber){
        this.sumNumber = sumNumber;
    }
}