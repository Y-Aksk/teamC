package solekia.demo.fastfood.controller;

import java.util.ArrayList;
import java.util.List;

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
        
        //セッションIDからログイン状態の把握
        int customer_id = 0;
        if(session.getAttribute("customer_id") != null){
            RegisterPageModel login = new RegisterPageModel();
            customer_id = (int)session.getAttribute("customer_id");
            login.login = registerMapper.findById_login(customer_id);
        }

        //リストを初期化
        page.list = orderMapper.findAll();
        
        model.addAttribute("page", page);
        
        return "fastfood/order"; //テンプレートファイルを指定
    }
    
    @PostMapping("order")
    public String list(OrderPageModel page, Model model){
        //ページインスタンスを作って、タイトルを設定
        page.web_title = "商品選択画面";
        
        List<String> orderCntList = new ArrayList<String>();
        orderCntList = page.getOrderCnt();
        int maxCnt = orderCntList.size();
        
        // 表示用リストを定義
        List<String> dispKosuList= new ArrayList<String>();
        List<String> proIdList= new ArrayList<String>();
        List<String> bgList= new ArrayList<String>();
        List<String> priceList= new ArrayList<String>();
        
        try {
        
        // 個数の入力がないものを省く
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

        //モデルにページインスタンスを設定
        model.addAttribute("page", orderif);
        model.addAttribute("sumNumber", sumNumber);
        model.addAttribute("kakePrice", kakePrice);
    } catch (Exception e) {
        return "fastfood/ngPage";
    }
        //テンプレートファイルを指定
        return "fastfood/check";
    }

    @PostMapping("kakutei")
    public String addition(OrderPageModel page, Model model ){

        //ページインスタンスを作って、タイトルを設定
        page.web_title = "確認画面";
        int customer_id = (int)session.getAttribute("customer_id");

        for(int i = 0; i<page.orderCnt.size();i++){
           
        //オーダー内容の追加
        orderMapper.addition(
          Integer.parseInt(page.proIds.get(i)),
          page.bgNames.get(i),
          Integer.parseInt(page.prices.get(i)),
          Integer.parseInt(page.orderCnt.get(i)),
          customer_id);

        orderMapper.addSales(
            Integer.parseInt(page.proIds.get(i)), 
            Integer.parseInt(page.orderCnt.get(i))
            );
    }

        
        //テンプレートファイルを指定
        return "fastfood/confirmation";
    }
}
 