package solekia.demo.fastfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solekia.demo.fastfood.model.*;
import solekia.demo.fastfood.repository.*;

//localhost:8080/fastfood/product
@Controller
// 処理対象のフォルダーを指定(fastfood)
@RequestMapping("fastfood")
public class ProductController {

    @Autowired
    ProductMapper ProductMapper;

    @GetMapping("product")
    public String list(Model model) {
        //ページインスタンスを作って、タイトルを作成
        ProductPageModel page = new ProductPageModel();
        page.web_title = "メニュー";

        //リストを初期化
        page.list = ProductMapper.findAll();

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);

        return "fastfood/product"; //テンプレートファイルを指定
    }
}