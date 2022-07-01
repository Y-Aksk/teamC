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
    AllergyMapper allergyMapper;

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
        page.title = "マイページ画面";

        //リスト初期化(IDとパスワード分けないといけないかも)
        page.list = LoginMapper.findAccount(customer_id, password);

        //addition参照、遷移するタイミングで
        if(!(page.list.size() == 0))
            page.authority = LoginMapper.ditectAuth(customer_id);
        
        //ログインボタンを押したときの画面遷移の条件分岐
        //顧客用
        if(!(page.list.size() == 0) && page.authority == 0){
            //idをセッションの値として格納
            session.setAttribute("customer_id", customer_id);

            //ログイン状態の記録
            page.login = 1;
            LoginMapper.login_out(customer_id, page.login);

            //マイページへ遷移
            return mypage(model);
        }
    
        //IDかパスワードが不一致だった場合
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

    @PostMapping("login_emp")
    public String login_emp(
        @RequestParam("customer_id")int customer_id, 
        @RequestParam("password") String password,
        Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.title = "マイページ画面";

        //リスト初期化(IDとパスワード分けないといけないかも)
        page.list = LoginMapper.findAccount(customer_id, password);

        //addition参照、遷移するタイミングで
        if(!(page.list.size() == 0))
            page.authority = LoginMapper.ditectAuth(customer_id);
        
        //ログインボタンを押したときの画面遷移の条件分岐
        //従業員用
        if(!(page.list.size() == 0) && page.authority == 1){

            //idをセッションの値として格納
            session.setAttribute("customer_id", customer_id);

            //ログイン状態の記録
            page.login = 1;
            LoginMapper.login_out(customer_id, page.login);

            //モデルにページインスタンスを設定
            model.addAttribute("page", page);

            //マイページへ遷移
            return "fastfood/mypage_emp";
        }
        //IDかパスワードが不一致だった場合
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
        AllergyPageModel status = new AllergyPageModel();
        //下４行追加
        AllergyPageModel wait = new AllergyPageModel();
        AllergyPageModel order_0 = new AllergyPageModel();
        AllergyPageModel order_1 = new AllergyPageModel();
        AllergyPageModel order_2 = new AllergyPageModel();

        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");

        //IDをキーに表示するデータを検索
        status.list = allergyMapper.findByCustId(customer_id);

        //待ち組数を数える用のcustomer_idを格納する
        wait.list = allergyMapper.findGroup(customer_id);

        //変更　オーダーID＝0の数を数える
        order_0.list = allergyMapper.findGroupOrder(customer_id);

        //追加　オーダーID＝1の数を数える
        order_1.list = allergyMapper.findGroupOrder1(customer_id);

        //追加　オーダーID＝2の数を数える
        order_2.list = allergyMapper.findGroupOrder2(customer_id);

        //リスト初期化
        page.list = LoginMapper.showMypage(customer_id);

        model.addAttribute("status", status);
        //下4行追加
        model.addAttribute("wait", wait);
        model.addAttribute("order_0", order_0);
        model.addAttribute("order_1", order_1);
        model.addAttribute("order_2", order_2);
        
        
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
        page.authority = LoginMapper.ditectAuth(customer_id);

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
        return "fastfood/check_logout_emp";
    }


    @PostMapping("check_logout")
    public String checkLogout(Model model){
        //RegisterPageModelクラスをpageとして扱う
        RegisterPageModel page = new RegisterPageModel();
        //pageのtitleメソッドに処理を追加
        page.title = "ホーム";
 
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
        page.title = "ホーム";

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

   @GetMapping("delete")
    public String delete(Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.message = "退会しますか？";

        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");
        page.authority = LoginMapper.ditectAuth(customer_id);

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
    

        //テンプレートファイルを指定
        return "fastfood/delete";
    }

    @GetMapping("deleted")
    public String deleted(Model model) {
        int customer_id = (int)session.getAttribute("customer_id");
        LoginMapper.delete(customer_id);
        //セッション終了
        session.invalidate();
        return "redirect:/fastfood/home";
    }

 
}
