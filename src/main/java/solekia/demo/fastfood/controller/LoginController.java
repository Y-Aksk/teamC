package solekia.demo.fastfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

import solekia.demo.fastfood.model.*;
import solekia.demo.fastfood.repository.*;

@Controller
@RequestMapping("fastfood")//URLのパス名をここに書く(http://localhost:8080/○○)
public class LoginController {

    @Autowired
    LoginMapper LoginMapper;
    @Autowired
    HttpSession session;

    //顧客用ログインページへの遷移(ボタン)
    @GetMapping("login")
    public String Login(Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.message = "";

        //モデルページにインスタンスを生成
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "fastfood/login";
    }

    //顧客ログイン画面
    @PostMapping("login")
    public String login(
        @RequestParam("customer_id") int customer_id, 
        @RequestParam("password") String password,
        Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.title = "マイページ画面(Java)";

        //リスト初期化(IDとパスワード分けないといけないかも)
        page.list = LoginMapper.findAccount(customer_id, password);

        //addition参照、遷移するタイミングで
        //page.authority = LoginMapper.ditectAuth(customer_id);
        
        //page.hold_id = customer_id;

        model.addAttribute("page", page);
        //model.addAttribute("page.count", page.count);


        //ログインボタンを押したときの画面遷移の条件分岐
        if(!(page.list.size() == 0) && page.authority == 0){
            int login = 1;
            LoginMapper.login_out(customer_id, login);

            page.count = LoginMapper.countOrder();
            //モデルにページインスタンスを設定
            model.addAttribute("page", page);

            session.setAttribute("user", page.getCustomer_id());
            //model.addAttribute("page.count", page.count);
            return "fastfood/mypage";
        }
        

        else{
            page.message = "IDかパスワードが違います";
            model.addAttribute("page", page);
            return "fastfood/login";
        }
        
    }

    //従業員用ログインページへの遷移(ボタン)
    @GetMapping("login_emp")
    public String Login_emp(
        Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.title = "ログイン画面（確認）";

        //モデルページにインスタンスを生成
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "fastfood/login_emp";
    }

    //従業員ログイン画面
    @PostMapping("login_emp")
    public String login_emp(
        @RequestParam("customer_id") int customer_id, 
        @RequestParam("password") String password, 
        Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.title = "ログイン画面(Java)";

        //リスト初期化(IDとパスワード分けないといけないかも)
        page.list = LoginMapper.findAccount(customer_id, password);

        //addition参照、遷移するタイミングで
        //page.authority = LoginMapper.ditectAuth(customer_id);
    
        page.hold_id = customer_id;


        if(!(page.list.size() == 0) && page.authority == 1){
            int login = 1;
            LoginMapper.login_out(customer_id, login);
            //モデルにページインスタンスを設定
            model.addAttribute("page", page);
            return "fastfood/mypage_emp";
        }
    

        else{
            page.message = "IDかパスワードが違います";
            model.addAttribute("page", page);
            return "fastfood/login_emp";
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

        //遷移先のページでセッションから値を取得する
        String name = (String)session.getAttribute("user");
        

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
