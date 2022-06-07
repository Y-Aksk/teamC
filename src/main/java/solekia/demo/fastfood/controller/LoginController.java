package solekia.demo.fastfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solekia.demo.fastfood.model.*;
import solekia.demo.fastfood.repository.*;

@Controller
@RequestMapping("registered")
public class LoginController {

    @Autowired
    LoginMapper LoginMapper;

    //顧客ログイン
    @PostMapping("login")
    public String login(@RequestParam("name")int customer_id, String password, Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.title = "ログイン画面(Java)";

        //リスト初期化(IDとパスワード分けないといけないかも)
        page.list = LoginMapper.findAccount(customer_id, password);

        //addition参照、遷移するタイミングで
        page.authority = LoginMapper.ditectAuth(customer_id);
        
        //page.hold_id = customer_id;


        if(!(page.list == null) && page.authority == 0){
                int login = 1;
                LoginMapper.login_out(customer_id, login);
                //モデルにページインスタンスを設定
                model.addAttribute("page", page);
                return "fastfood/mypage";
            }
        

        else{
            page.message = "IDかパスワードが違います";
            model.addAttribute("page", page);
            return "fastfood/login";
    }
}

//従業員ログイン
@PostMapping("login_emp")
public String login_emp(@RequestParam("name")int customer_id, String password, Model model){
    //ページインスタンスを作って、タイトルを設定
    LoginPageModel page = new LoginPageModel();
    page.title = "ログイン画面(Java)";

    //リスト初期化(IDとパスワード分けないといけないかも)
    page.list = LoginMapper.findAccount(customer_id, password);

    //addition参照、遷移するタイミングで
    page.authority = LoginMapper.ditectAuth(customer_id);
    
    //page.hold_id = customer_id;


    if(!(page.list == null) && page.authority == 1){
            int login = 1;
            LoginMapper.login_out(customer_id, login);
            //モデルにページインスタンスを設定
            model.addAttribute("page", page);
            return "fastfood/mypage_emp";
        }
    

    else{
        page.message = "IDかパスワードが違います";
        model.addAttribute("page", page);
        return "fastfood/login";
}
}

@Transactional
    @GetMapping("mypage/{id}")
    public String mypage(@PathVariable("id") int customer_id, Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();

        //リスト初期化
        page.list = LoginMapper.showMypage(customer_id);
        
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        

        //テンプレートファイルを指定
        return "fastfood/mypage";

    }

    @GetMapping("mypage_emp/{id}")
    public String mypage_emp(@PathVariable("id") int customer_id, Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();

        //リスト初期化
        page.list = LoginMapper.showMypage(customer_id);
        
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        

        //テンプレートファイルを指定
        return "fastfood/mypage_emp";

    }
}
