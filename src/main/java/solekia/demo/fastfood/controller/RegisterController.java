package solekia.demo.fastfood.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solekia.demo.fastfood.model.*;
import solekia.demo.fastfood.repository.*;

//RegisterController
@Controller

//処理対象とするURL（今回はfastfood）を指定
@RequestMapping("fastfood")
public class RegisterController{

    //テーブルのアクセス用クラスを追加
    @Autowired
    //RagesterMapperをインスタンス化
    RegisterMapper registerMapper;

    @Autowired
    LoginMapper LoginMapper;

    @Autowired
    HttpSession session;

/*----　↓↓小林さんと合わせるときにちゃんと動くかチェック↓↓　----*/

/*----　ホーム画面　(http://localhost:8080/fastfood/home)　----*/
    //人気メニュートップ3の表示
    @GetMapping("home")
    public String home(Model model){
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
        return "fastfood/home";
    }


/*----　お問い合わせ　(http://localhost:8080/fastfood/qa)　完了----*/
    //お問い合わせ画面
    @GetMapping("qa")
    public String qa(Model model){
        //ページインスタンスを作って、タイトルを設定
        RegisterPageModel page = new RegisterPageModel();
        page.title = "お問い合わせ(java)";
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/qa";
    }

    //お問い合わせ処理
    @PostMapping("insertqa")
    public String insertQa(@ModelAttribute RegisterPageModel page, Model model){

        //タイトルを設定
        page.title = "お問い合わせ(java)";
        //画面で入力した更新データをパラメータに設定
        registerMapper.insertQa(page.qa_first_name, page.qa_last_name, page.question, page.qa_mail);
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/qa";
    }

/*----　新規会員登録　(http://localhost:8080/fastfood/register)　完了----*/
    //新規会員登録　画面
    /*
    @GetMapping("{id}/register")//先頭に{id}追加、ログアウト対策
    public String register(Model model){
        //ページインスタンスを作って、タイトルを設定
        RegisterPageModel page = new RegisterPageModel();
        page.title = "新規会員登録(java)";
        //pageのlistにfinShop()メソッドの値を格納
        page.list = registerMapper.findShop(); 
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/register";
    }

    
    //新規会員登録　処理　URL小林さんに聞く。
    @PostMapping("{id}/register")
    public String insertCust(
        //@ModelAttribute RegisterPageModel page, Model model){
        @RequestParam("password") String password, 
        @RequestParam("first_name") String first_name, 
        @RequestParam("last_name") String last_name,
        @RequestParam("tell_no") String tell_no,
        @RequestParam("mail") String mail, 
        @RequestParam("shop_name") String shop_name,
        Model model
    ){
        //LoginPageModel型オブジェクトのpageを生成
        LoginPageModel page = new LoginPageModel();
        page.message = "新規登録されました。";
        //画面で入力した更新データをパラメータに設定
        registerMapper.insertCust(password, first_name, last_name, tell_no, mail, shop_name);
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/login";
    }
    */

    @GetMapping("register")//loginから飛んだ場合、{id}は先頭に不要
    public String register2(Model model){
        //ページインスタンスを作って、タイトルを設定
        RegisterPageModel page = new RegisterPageModel();
        page.title = "新規会員登録(java)";
        //pageのlistにfinShop()メソッドの値を格納
        page.list = registerMapper.findShop(); 
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/register";
    }

    
    //新規会員登録　処理　URL小林さんに聞く。
    //loginから飛んだ場合、{id}は先頭に不要
    @PostMapping("register")
    public String insertCust2(
        //@ModelAttribute RegisterPageModel page, Model model){
        @RequestParam("password") String password, 
        @RequestParam("first_name") String first_name, 
        @RequestParam("last_name") String last_name,
        @RequestParam("tell_no") String tell_no,
        @RequestParam("mail") String mail, 
        @RequestParam("shop_name") String shop_name,
        Model model
    ){
        //追加
        LoginPageModel page = new LoginPageModel();
        //画面で入力した更新データをパラメータに設定
        registerMapper.insertCust(password, first_name, last_name, tell_no, mail, shop_name);
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/login";
    }


/*----　新規従業員登録　(http://localhost:8080/fastfood/register_emp)　完了----*/
    //新規従業員登録画面
    @GetMapping("register_emp")
    public String registerEmp(Model model){
        //ページインスタンスを作って、タイトルを設定
        RegisterPageModel page = new RegisterPageModel();
        page.title = "新規従業員登録(java)";
        //pageのlistにfinShop()メソッドの値を格納
        page.list = registerMapper.findShop(); 
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/register_emp";
    }

    
    //新規従業員登録処理　URL小林さんに聞く
    @PostMapping("register_emp")
    public String insertEmp( @RequestParam("password") String password, 
    @RequestParam("first_name") String first_name, 
    @RequestParam("last_name") String last_name,
    @RequestParam("tell_no") String tell_no,
    @RequestParam("mail") String mail, 
    @RequestParam("shop_name") String shop_name,
     Model model){

        LoginPageModel page = new LoginPageModel();
        //画面で入力した更新データをパラメータに設定
        registerMapper.insertEmp(password, first_name, last_name, tell_no, mail, shop_name);
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/login_emp";
    }
 

/*----　↓↓小林さんと合わせるときにちゃんと動くかチェック↓↓　----*/

/*----　顧客　登録情報変更　(http://localhost:8080/fastfood/edit)　----*/
    //顧客登録情報変更画面　URL変更する必要ありそう loginとidの値はセッション保持してたらいらない…？？
    @GetMapping("edit")
    public String edit(Model model){
        //ページインスタンスを作ってタイトルを設定
        RegisterPageModel page = new RegisterPageModel();
        page.title = "登録情報変更(java)";

        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");

        //IDをキーにデータを検索
        var register = registerMapper.findById(customer_id);
        //urlのloginにページのloginの値を入れる？違う気がする。
        //int login = page.login;
        //取得データをページに設定
        page.customer_id = register.customer_id;
        page.password = register.password;
        page.first_name = register.first_name;
        page.last_name = register.last_name;
        page.tell_no = register.tell_no;
        page.mail = register.mail;
        page.shop_name = register.shop_name;
        page.login = register.login;

        page.list = registerMapper.findShop();

        //遷移先のページでセッションから値を取得する
        //String user_id = (String)session.getAttribute("user_id");
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/edit";
    }

    
    //顧客登録情報変更処理　URL小林さんに聞く
    @PostMapping("edit")
    public String edit(@ModelAttribute RegisterPageModel page, Model model){
        //画面で入力した更新データをパラメータに設定
        registerMapper.update(page.customer_id, page.password, page.first_name, page.last_name, page.tell_no, page.mail, page.shop_name, page.login);
        
        LoginPageModel account = new LoginPageModel();
        //更新後のデータを取得
        account.list = LoginMapper.findAccount(page.customer_id, page.password);
        //モデルにページインスタンスを設定
        model.addAttribute("page", account);
        //テンプレートファイルを指定
        return "fastfood/mypage";
    }


/*----　従業員　登録情報変更　(http://localhost:8080/fastfood/edit_emp)　----*/
    //従業員　登録情報変更　画面　URL変更する必要ありそう　loginとidの値はセッション保持してたらいらない…？？
    @GetMapping("edit_emp")
    public String editEmp(Model model){
        //ページインスタンスを作ってタイトルを設定
        RegisterPageModel page = new RegisterPageModel();
        page.title = "登録情報変更(java)";

        //遷移先のページでセッションから値を取得する
        int customer_id = (int)session.getAttribute("customer_id");

        //IDをキーにデータを検索
        var register = registerMapper.findById(customer_id);
        //urlのloginにページのloginの値を入れる？違う気がする。
        //login = page.login;
        //取得データをページに設定
        page.customer_id = register.customer_id;
        page.password = register.password;
        page.first_name = register.first_name;
        page.last_name = register.last_name;
        page.tell_no = register.tell_no;
        page.mail = register.mail;
        page.shop_name = register.shop_name;
        page.login = register.login;

        page.list = registerMapper.findShop();

        //遷移先のページでセッションから値を取得する
        //String user_id = (String)session.getAttribute("user_id");
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "fastfood/edit_emp";
    }

     
    //従業員　登録情報変更　処理　URL小林さんに聞く
    @PostMapping("mypage_emp")
    public String editEmp(@ModelAttribute RegisterPageModel page, Model model){
        //画面で入力した更新データをパラメータに設定
        registerMapper.update(page.customer_id, page.password, page.first_name, page.last_name, page.tell_no, page.mail, page.shop_name, page.login);
        //更新後のデータを取得
        LoginPageModel account = new LoginPageModel();
        account.list = LoginMapper.findAccount(page.customer_id, page.password);
        //モデルにページインスタンスを設定
        model.addAttribute("page", account);
        //テンプレートファイルを指定
        return "fastfood/mypage_emp";
    }
    
}


