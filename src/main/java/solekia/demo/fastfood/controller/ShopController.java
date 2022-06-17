package solekia.demo.fastfood.controller;

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

    @GetMapping("shop")
    public String list(Model model) {
        //ページインスタンスを作って、タイトルを作成
        ShopPageModel page = new ShopPageModel();
        page.web_title = "店舗一覧";

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

        //モデルにページインスタンスを設定
        model.addAttribute("shop_name", shop_name);

        return "fastfood/shopdetail"; //テンプレートファイルを指定
    }
}