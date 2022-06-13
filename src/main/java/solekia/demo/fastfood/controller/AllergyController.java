package solekia.demo.fastfood.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import solekia.demo.fastfood.model.AllergyPageModel;
import solekia.demo.fastfood.repository.AllergyMapper;

@Controller
@RequestMapping("fastfood")
public class AllergyController {

    //Orderテーブルのアクセス用クラスを追加
    @Autowired
    AllergyMapper allergyMapper;

    @PostMapping("search")
    public String search(@RequestParam("product_name")String name,Model model)
    {
        //タイトルリセット　変数を使えるようにしている
        AllergyPageModel page = new AllergyPageModel();
        //AllergyPageModel page2 = new AllergyPageModel();
        page.title = "アレルギー情報";

        page.product_name = name;

        //リストの初期化
        page.list = allergyMapper.findName("%"+name+"%");
        //page2.list = allergyMapper.findAll();

        //モデルにページインスタンスを作成　検索内容を詰め込む
        model.addAttribute("page",page);
        //model.addAttribute("list",page2);

        //テンプレートファイルを設定
        return "fastfood/allergy";
    }

   

    @GetMapping("allergy")
    public String list(Model model) {

        //タイトルリセット
        AllergyPageModel page = new AllergyPageModel();

        page.title = "アレルギー情報";

        page.list = allergyMapper.findAll();

        model.addAttribute("page", page);

        return "fastfood/allergy"; //テンプレートファイルを指定
    }

}