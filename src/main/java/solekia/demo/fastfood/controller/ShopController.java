package solekia.demo.fastfood.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solekia.demo.fastfood.model.*;
import solekia.demo.fastfood.repository.*;

@Controller
// 処理対象のフォルダーを指定(fastfood)
@RequestMapping("fastfood")
public class ShopController {

    @Autowired
    ShopMapper ShopMapper;

    @Autowired
    //RagesterMapperをインスタンス化
    RegisterMapper registerMapper;

    @Autowired
    LoginMapper LoginMapper;

    @Autowired
    HttpSession session;

    @GetMapping("shop")
    public String list(Model model) {
        //ページインスタンスを作って、タイトルを作成
        ShopPageModel page = new ShopPageModel();
        page.web_title = "店舗一覧";

        int customer_id = 0;
        if(session.getAttribute("customer_id") != null){
            customer_id = (int)session.getAttribute("customer_id");
            page.login = registerMapper.findById_login(customer_id);
            //マイページに遷移できるなら必要
            page.authority = LoginMapper.ditectAuth(customer_id);
        }

        //リストを初期化
        page.list = ShopMapper.findAll();

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);

        return "fastfood/shop"; //テンプレートファイルを指定
    }

    //詳細ページ
    @GetMapping("shopdetail/{id}")
    public String shopdetail(@PathVariable("id") int id,Model model) {
        //ページインスタンスを作って、タイトルを作成    
        ShopPageModel shop_name = new ShopPageModel();
        
        shop_name.list = ShopMapper.findShopName(id);

        int customer_id = 0;
        RegisterPageModel page = new RegisterPageModel() ;
        if(session.getAttribute("customer_id") != null){
            customer_id = (int)session.getAttribute("customer_id");
            page.login = registerMapper.findById_login(customer_id);
            //マイページに遷移できるなら必要
            page.authority = LoginMapper.ditectAuth(customer_id);
        }

        //モデルにページインスタンスを設定
        model.addAttribute("shop_name", shop_name);
        model.addAttribute("page", page);


        return "fastfood/shopdetail"; //テンプレートファイルを指定
    }
}