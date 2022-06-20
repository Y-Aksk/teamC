package solekia.demo.fastfood.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solekia.demo.fastfood.model.*;
import solekia.demo.fastfood.repository.*;

/**
 * OrderController
 */
@Controller
@RequestMapping("fastfood")
public class OrderController {

OrderPageModel hold_id;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    HttpSession session;

    
    public String[] checked;

    @GetMapping("order")
    public String list(Model model) {
        //ページインスタンスを作って、タイトルを作成
        OrderPageModel page = new OrderPageModel();
        
        page.web_title = "商品選択画面";
        

        //リストを初期化
        page.list = orderMapper.findAll();
        
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        model.addAttribute("page1", getCheckBoxFood());
        

        return "fastfood/order"; //テンプレートファイルを指定
    }

//変更
    // Map文を使って、チェックボックスを表示させる
    private Map<String , String> getCheckBoxFood(){
        Map<String, String> checkBoxFood = new LinkedHashMap<String , String>();
        OrderPageModel page = new OrderPageModel();

        page.list = orderMapper.findAll();

        // 拡張for文で商品画像のチェックボックスをputする
        for(OrderModel item : page.list){
            checkBoxFood.put(item.product_name, item.product_pic);
        }
        return checkBoxFood; //テンプレートファイルを指定
    }

    @PostMapping("order")
    public String list(
        @RequestParam("product_id") int product_id, 
        @RequestParam("product_name") String product_name,
        @RequestParam("price") int price, 
        @RequestParam("number") int number,
        Model model){
        //ページインスタンスを作って、タイトルを設定
        OrderPageModel page = new OrderPageModel();
       
        page.web_title = "商品選択画面";
        
        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");
        
        //リスト初期化
        orderMapper.addition(product_id, product_name, price, number,customer_id);
        
        //商品選択後のデータを取得
        page.list = orderMapper.orderList(customer_id);

        //モデルにページインスタンスを設定
        //model.addAttribute("page", page);
        model.addAttribute("page", page.getChecked());

        //テンプレートファイルを指定
        return "fastfood/check";
    }

    @GetMapping("check")
    public String addition(Model model){
        //ページインスタンスを作って、タイトルを設定
        OrderPageModel page = new OrderPageModel();
        page.web_title = "確認画面";
        hold_id = new OrderPageModel();
        
        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");
        hold_id.setCustomer_id(customer_id);

        page.list = orderMapper.list(customer_id);

        //モデルページにインスタンスを生成
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "fastfood/check";
    }

    @PostMapping("check")
    public String addition(
        @RequestParam("product_id") int product_id, 
        @RequestParam("product_name") String product_name,
        @RequestParam("price") int price, 
        @RequestParam("number") int number,
    
        Model model){
        //ページインスタンスを作って、タイトルを設定
        OrderPageModel page = new OrderPageModel();
        page.web_title = "確認画面";
        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");
        page.sumPrice += price;
        page.sumNumber += number;


        //リスト初期化
        orderMapper.addition(product_id, product_name, price, number,customer_id);
        
        //注文確定後のデータを取得
        page.list = orderMapper.list(customer_id);

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "fastfood/kakunin";
    }
}
 