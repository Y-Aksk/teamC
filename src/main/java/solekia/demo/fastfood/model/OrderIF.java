 package solekia.demo.fastfood.model;

/**
 * OrderIF
 * チェック
 */

public class OrderIF {
    public String path;
    public String orderStr;
    public String product_id;
    public String product_name;
    public String price;

    public OrderIF(String path , String orderStr , String product_id , String product_name , String price){
        this.path = path;
        this.orderStr = orderStr;
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
    }

    public String getPath(){
        return path;
    }
    public String getOrderStr(){
        return orderStr;
    }
    public String getProduct_id(){
        return product_id;
    }
    public String getProduct_name(){
        return product_name;
    }
    public String getPrice(){
        return price;
    }
}
