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

    @Autowired
    //RagesterMapperをインスタンス化
    RegisterMapper registerMapper;

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
        @RequestParam("customer_id")int customer_id, 
        @RequestParam("password") String password,
        Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.title = "マイページ画面(Java)";

        //リスト初期化(IDとパスワード分けないといけないかも)
        page.list = LoginMapper.findAccount(customer_id, password);

        //addition参照、遷移するタイミングで
        page.authority = LoginMapper.ditectAuth(customer_id);
        
        //page.hold_id = customer_id;

        model.addAttribute("page", page);
        //model.addAttribute("page.count", page.count);

        if(customer_id == page.getCustomer_id() && page.login == 1){
            page.message = "すでに他の端末でログイン済みです";
            model.addAttribute("page", page);
            return "fastfood/login_emp";
        }

        //ログインボタンを押したときの画面遷移の条件分岐
        if(!(page.list.size() == 0) && page.authority == 0){
            //idをセッションの値として格納
            session.setAttribute("customer_id", customer_id);

            page.login = 1;
            LoginMapper.login_out(customer_id, page.login);

            page.count = LoginMapper.countOrder();
            //モデルにページインスタンスを設定
            model.addAttribute("page", page);

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
    public String Login_emp(Model model){
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
        page.authority = LoginMapper.ditectAuth(customer_id);
   
        //page.hold_id = customer_id;

        /*
        if(customer_id == page.getCustomer_id() && page.getLogin() == 1){
            page.message = "すでに他の端末でログイン済みです";
            model.addAttribute("page", page);
            return "fastfood/login_emp";
        }
        */

        if(!(page.list.size() == 0) && page.authority == 1){

            //idをセッションの値として格納
            session.setAttribute("user_id", page.getCustomer_id());

            page.login = 1;
            LoginMapper.login_out(customer_id, page.login);

            page.count = LoginMapper.countOrder();
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
    @GetMapping("mypage")
    public String mypage(Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();

        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");

        //リスト初期化
        page.list = LoginMapper.showMypage(customer_id);
        
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "fastfood/mypage";

    }

    @GetMapping("mypage_emp")
    public String mypage_emp(Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();

        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");

        //リスト初期化
        page.list = LoginMapper.showMypage(customer_id);
        
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        

        //テンプレートファイルを指定
        return "fastfood/mypage_emp";

    }

    @GetMapping("logout")
    public String logout(Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.message = "ログアウトしました";

        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");

        page.list = LoginMapper.showMypage(customer_id);

        page.login = 0;
        LoginMapper.login_out(customer_id, page.login);

        //セッション終了
        session.invalidate();

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
    

        //テンプレートファイルを指定
        // return "fastfood/login";
        return "fastfood/check_logout";

    }

    @GetMapping("logout_emp")
    public String logout_emp(Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.message = "ログアウトしました";

        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");
        page.list = LoginMapper.showMypage(customer_id);

        page.login = 0;
        LoginMapper.login_out(customer_id, page.login);

        //セッション終了
        session.invalidate();

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
    

        //テンプレートファイルを指定
        // return "fastfood/login_emp";
        return "fastfood/check_logout_emp";
    }

    //login/list, logout/listの分も作る
    @PostMapping("check_logout")
    public String checkLogout(Model model){
         //RegisterPageModelクラスをpageとして扱う
         RegisterPageModel page = new RegisterPageModel();
         //pageのtitleメソッドに処理を追加
         page.title = "ホーム(java)";
 
        //遷移先のページでセッションから値を取得する
        //ログインいる版のホームページが必要になるかも

        int customer_id = 0;
    
        if(session.getAttribute("customer_id") != null){
            customer_id = (int)session.getAttribute("customer_id");
            page.login = 1;
        }
         //pageのlistにRegisterMapperクラスのfindRank()メソッドの値を格納
         page.list = registerMapper.findRank();
         //モデルにページインスタンスを設定
         model.addAttribute("page",page);
         //テンプレートファイルを指定
         return "redirect:/fastfood/home";
    }

    @GetMapping("check_logout_emp")
    public String checkLogoutEmp(Model model){
        //RegisterPageModelクラスをpageとして扱う
        RegisterPageModel page = new RegisterPageModel();
        //pageのtitleメソッドに処理を追加
        page.title = "ホーム(java)";

       //遷移先のページでセッションから値を取得する
       //ログインいる版のホームページが必要になるかも

       int customer_id = 0;
   
       if(session.getAttribute("customer_id") != null){
           customer_id = (int)session.getAttribute("customer_id");
       }
        //pageのlistにRegisterMapperクラスのfindRank()メソッドの値を格納
        page.list = registerMapper.findRank();
        //モデルにページインスタンスを設定
        model.addAttribute("page",page);
        //テンプレートファイルを指定
        return "redirect:/fastfood/home";
   }

    /* 
    @GetMapping("list")
    public String List(Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.message = "";

        //モデルページにインスタンスを生成
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "fastfood/list";
    }

    @GetMapping("logout/list")
    public String List1(Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.message = "";

        //モデルページにインスタンスを生成
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "fastfood/list";
    }
    */
}
