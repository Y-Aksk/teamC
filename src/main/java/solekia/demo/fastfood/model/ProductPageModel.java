package solekia.demo.fastfood.model;

/**
 * ProductPageModel
 */

import java.util.*;
import solekia.demo.fastfood.repository.ProductModel; 

public class ProductPageModel {
    public String web_title;
    public String hold_name = "";
    public List<ProductModel> list;

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

    //追加
    public int login;
    public int authority;
    

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
}