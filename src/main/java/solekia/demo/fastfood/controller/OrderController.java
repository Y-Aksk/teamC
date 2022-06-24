package solekia.demo.fastfood.controller;

import java.util.ArrayList;
// 変更
import java.util.LinkedHashMap;
import java.util.List;
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
    //RagesterMapperをインスタンス化
    RegisterMapper registerMapper;


    @Autowired
    HttpSession session;
    public int customer_id;
    public String[] checked;

    @GetMapping("order")
    public String list(Model model) {
        //ページインスタンスを作って、タイトルを作成
        OrderPageModel page = new OrderPageModel();
        
        page.web_title = "商品選択画面";
        
        int id = (int)session.getAttribute("customer_id");

        RegisterPageModel login = new RegisterPageModel();
         if(session.getAttribute("customer_id") != null){
            login.login = registerMapper.findById_login(id);
         }

        //リストを初期化
        page.list = orderMapper.findAll();
        
        //モデルにページインスタンスを設定

        // MAPの下りは不要
        //model.addAttribute("page", getCheckBoxFood());
        model.addAttribute("page", page);
        model.addAttribute("login", login);
        
        return "fastfood/order"; //テンプレートファイルを指定
    }
    //変更
    // Map文を使って、チェックボックスを表示させる
    // private Map<String , String> getCheckBoxFood(){
    //     Map<String, String> checkBoxFood = new LinkedHashMap<String , String>();
    //     OrderPageModel page = new OrderPageModel();

    //     //リストを初期化
    //     page.list = orderMapper.findAll();

    //     // 拡張for文で商品画像のチェックボックスをputする
    //     for(OrderModel item : page.list){
    //         checkBoxFood.put(item.product_pic, item.product_pic);
    //     }
    //     return checkBoxFood; //テンプレートファイルを指定
    // }

    

    @PostMapping("order")
    public String list(
        // @RequestParam("product_pic") String product_pic, 
        // @RequestParam("product_name") String product_name,
        // @RequestParam("price") int price, 
        // @RequestParam("number") int number,
        OrderPageModel page,
    
        Model model){
        //ページインスタンスを作って、タイトルを設定
        //OrderPageModel page = new OrderPageModel();
       
        page.web_title = "商品選択画面";
        
        List<String> orderCntList = new ArrayList<String>();
        orderCntList = page.getOrderCnt();
        int maxCnt = orderCntList.size();
        
        // 表示用リストを定義
        List<String> dispKosuList= new ArrayList<String>();
        List<String> proIdList= new ArrayList<String>();
        List<String> bgList= new ArrayList<String>();
        List<String> priceList= new ArrayList<String>();
        
        // 個数の入力がないものを省く
        //
        // sumPrice += kakePrice;
        int sumNumber = 0;
        int kakePrice = 0;
        for (int i=0 ; i < maxCnt; i++ ){
            if (orderCntList.get(i).length() > 0){
                dispKosuList.add(orderCntList.get(i));
                proIdList.add(page.proIds.get(i));  
                bgList.add(page.bgNames.get(i));      
                priceList.add(page.prices.get(i)); 
                sumNumber += Integer.parseInt(page.orderCnt.get(i));
                kakePrice += Integer.parseInt(page.prices.get(i)) * Integer.parseInt(page.orderCnt.get(i));
            }
        }
        
        // チェック画面表示用の値を設定する
        List<OrderIF> orderif = new ArrayList<OrderIF>();
        for (int i=0 ; i < page.checked.size(); i++ ){
            orderif.add(new OrderIF(page.checked.get(i), dispKosuList.get(i), proIdList.get(i), bgList.get(i) , priceList.get(i)));
        }

        //変更
        //リスト初期化
        //orderMapper.addition(product_pic, product_name, price, number,customer_id);

        // 変更(コメントアウト)
        //商品選択後のデータを取得
        //page.list = orderMapper.list(customer_id);
        //checking.getChecked();

        int id = (int)session.getAttribute("customer_id");

        RegisterPageModel login = new RegisterPageModel();
         if(session.getAttribute("customer_id") != null){
            login.login = registerMapper.findById_login(id);
         }
        //モデルにページインスタンスを設定
        model.addAttribute("page", orderif);
        model.addAttribute("sumNumber", sumNumber);
        model.addAttribute("kakePrice", kakePrice);
        // model.addAttribute("orderCntList", orderCntList);
        model.addAttribute("login", login);
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "fastfood/check";
    }

    // @GetMapping("kakutei")
    // public String addition(@PathVariable("id")int customer_id, Model model){
    //     //ページインスタンスを作って、タイトルを設定
    //     OrderPageModel page = new OrderPageModel();
    //     page.web_title = "注文確認画面";
    //     hold_id = new OrderPageModel();
    //     hold_id.setCustomer_id(customer_id);
    //     // 変更(追加)
    //     page.list = orderMapper.orderList(customer_id);

    //     //モデルページにインスタンスを生成
    //     model.addAttribute("page", page);
    //     model.addAttribute("page1", getCheckBoxFood());


    //     //テンプレートファイルを指定
    //     return "fastfood/check";
    // }

    @PostMapping("kakutei")
    public String addition(
        // @RequestParam("product_pic") String product_pic, 
        // @RequestParam("product_name") String product_name,
        // @RequestParam("price") int price, 
        // @RequestParam("number") int number,
        OrderPageModel page,
        Model model ){

        //ページインスタンスを作って、タイトルを設定
        // OrderPageModel page = new OrderPageModel();
        page.web_title = "確認画面";
        int customer_id = (int)session.getAttribute("customer_id");

        int sumPrice = 0;
        int sumNumber = 0;
        int kakePrice = 0;
        for(int i = 0; i<page.orderCnt.size();i++){
             
            // List<String> orderCntList = new ArrayList<String>();
            // orderCntList = page.getOrderCnt();
            // int maxCnt = orderCntList.size();
            
            // kakePrice += Integer.parseInt(page.prices.get(i)) * Integer.parseInt(page.orderCnt.get(i));
            // sumPrice += kakePrice;
            // sumNumber += Integer.parseInt(page.orderCnt.get(i));
            
            
        // 選択された価格と個数を合計
        // page.sumPrice += price;
        // page.sumNumber += number;


        //変更　
        //リスト初期化
         orderMapper.addition(
            
          Integer.parseInt(page.proIds.get(i)),
          page.bgNames.get(i),
          Integer.parseInt(page.prices.get(i)),
        //   sumPrice += page.prices.get(i),
          Integer.parseInt(page.orderCnt.get(i)),
        //   sumNumber += page.orderCnt.get(i),

          customer_id);
    }
        //注文確定後のデータを取得
        //page.list = orderMapper.orderList(customer_id);


        //モデルにページインスタンスを設定
        //model.addAttribute("page", page.getChecked());
        //model.addAttribute("page", orderif);
        //変更(注文完了画面の名前confirmationは適当)
        //テンプレートファイルを指定
        return "fastfood/confirmation";
    }
}
 