//package solekia.demo.fastfood.controller;
package solekia.demo.fastfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import solekia.demo.fastfood.model.RegisterPageModel;
import solekia.demo.fastfood.repository.RegisterMapper;

//RegisterController
@Controller

//処理対象とするURL(今回はfastfood)を指定
@RequestMapping("fastfood")
public class RegisterController{

    RegisterMapper registerMapper;

    //お問い合わせ画面
    @GetMapping("qa")
    public String qa(Model model){

        //ページインスタンスを作って、タイトルを設定
        RegisterPageModel page = new RegisterPageModel();
        page.title = "お問い合わせ";
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "customer/qa";
    }

    //お問い合わせ処理
    @PostMapping("insertqa")
    public String qa(@ModelAttribute RegisterPageModel page, Model model){

        //タイトルを設定
        page.title = "お問い合わせ";
        //画面で入力した更新データをパラメータに設定　memo…パラメータとは？調べる
        registerMapper.insertqa(page.qa_id, page.qa_first_name, page.last_name, page.mail);
        //更新後のデータを取得
        page.list = registerMapper.findQa();
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "register/qa";
    }


    //新規会員登録画面
    @GetMapping("registercust")
    public String register(Model model){
        //ページインスタンスを作って、タイトルを設定
        RegisterPageModel page = new RegisterPageModel();
        page.title = "新規会員登録";
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/registercust";
    }
   

    //新規登録の編集処理
    @PostMapping("registercust")
    public String registercust(@ModelAttribute RegisterPageModel page, Model model){
        //タイトルを設定
        page.title = "顧客一覧画面（Java）";
        //画面で入力した更新データをパラメータに設定　memo…パラメータとは？調べる
        registerMapper.insert(page.first_name, page.last_name, page.tell_no, page.mail, page.login, page.authority, page.shop_name);
        //更新後のデータを取得
        page.list = registerMapper.findRe();
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/mypage";
    }

}










