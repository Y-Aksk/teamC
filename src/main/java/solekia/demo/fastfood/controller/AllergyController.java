package solekia.demo.fastfood.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solekia.demo.fastfood.model.*;
import solekia.demo.fastfood.repository.*;

@Controller
@RequestMapping("fastfood")
public class AllergyController {


    @Autowired
    AllergyMapper allergyMapper;

    @Autowired
    HttpSession session;

    @Autowired
    //RagesterMapperをインスタンス化
    RegisterMapper registerMapper;

    @Autowired
    LoginMapper LoginMapper;

    //アレルギー検索
    @PostMapping("search")
    public String search(@RequestParam("product_name")String name,Model model)
    {
        //タイトルリセット　変数を使えるようにしている
        AllergyPageModel page = new AllergyPageModel();
        AllergyPageModel page2 = new AllergyPageModel();
        
        page.title = "アレルギー情報";

        page.product_name = name;

        int customer_id = 0;
        if(session.getAttribute("customer_id") != null){
            customer_id = (int)session.getAttribute("customer_id");
            page.login = registerMapper.findById_login(customer_id);
            //マイページに遷移できるなら必要
            page.authority = LoginMapper.ditectAuth(customer_id);
        }

        //リストの初期化
        page.list = allergyMapper.findName("%"+name+"%");
        page2.list = allergyMapper.findAll();

        //モデルにページインスタンスを作成　検索内容を詰め込む
        model.addAttribute("page",page);
        model.addAttribute("bglist",page2.list);

        //テンプレートファイルを設定
        return "fastfood/allergy";
    }

   //アレルギー情報一覧画面
    @GetMapping("allergy")
    public String list(Model model) {

        //タイトルリセット
        AllergyPageModel page = new AllergyPageModel();

        page.title = "アレルギー情報";

        int customer_id = 0;
        if(session.getAttribute("customer_id") != null){
            customer_id = (int)session.getAttribute("customer_id");
            page.login = registerMapper.findById_login(customer_id);
            //マイページに遷移できるなら必要
            page.authority = LoginMapper.ditectAuth(customer_id);
        }

        page.list = allergyMapper.findAll();

        model.addAttribute("page", page);
        model.addAttribute("bglist", page.list);

        return "fastfood/allergy"; //テンプレートファイルを指定
    }

    //注文状況確認画面
    @GetMapping("order_status")
    public String status (Model model) {

        //タイトルリセット
        AllergyPageModel status = new AllergyPageModel();
        //下４行追加
        AllergyPageModel wait = new AllergyPageModel();
        AllergyPageModel order_0 = new AllergyPageModel();
        AllergyPageModel order_1 = new AllergyPageModel();
        AllergyPageModel order_2 = new AllergyPageModel();

       status.title = "注文状況確認画面";

       int id = (int)session.getAttribute("customer_id");

       RegisterPageModel page = new RegisterPageModel();
        if(session.getAttribute("customer_id") != null){
            page.login = registerMapper.findById_login(id);
        }

        //IDをキーに表示するデータを検索
        status.list = allergyMapper.findByCustId(id);

        //待ち組数を数える用のcustomer_idを格納する
        wait.list = allergyMapper.findGroup(id);

        //変更　オーダーID＝0の数を数える
        order_0.list = allergyMapper.findGroupOrder(id);

        //追加　オーダーID＝1の数を数える
        order_1.list = allergyMapper.findGroupOrder1(id);

        //追加　オーダーID＝2の数を数える
        order_2.list = allergyMapper.findGroupOrder2(id);

        model.addAttribute("status", status);
        //下4行追加
        model.addAttribute("wait", wait);
        model.addAttribute("order_0", order_0);
        model.addAttribute("order_1", order_1);
        model.addAttribute("order_2", order_2);
        model.addAttribute("page", page);

        return "fastfood/order_status"; //テンプレートファイルを指定
    }

//管理者確認画面
@GetMapping("adoministrator_confirmation")
public String adoministrator(Model model) {

    //タイトルリセット
    AllergyPageModel page = new AllergyPageModel();

    page.title = "管理者確認画面";

    int customer_id = (int)session.getAttribute("customer_id");
    page.list = allergyMapper.findAdo(customer_id);

    model.addAttribute("page", page);

    return "fastfood/adoministrator_confirmation"; //テンプレートファイルを指定
}

@GetMapping("update/{no}")
public String update(@PathVariable("no") int no, Model model){
    
    //ページインスタンスを作って、タイトルを設定
    AllergyPageModel page = new AllergyPageModel();

    page.title = "管理者確認画面";

    //noをキーにデータを更新
    allergyMapper.update(no);

    int customer_id = (int)session.getAttribute("customer_id");
    //リストを初期化
    page.list = allergyMapper.findAdo(customer_id);

    //モデルにページインスタンスを設定
    model.addAttribute("page", page);

    //テンプレートファイルを指定
    return "fastfood/adoministrator_confirmation";
}


//以下のGetMapping追加
@GetMapping("update_end/{no}")
public String update_end(@PathVariable("no") int no, Model model){
    
    //ページインスタンスを作って、タイトルを設定
    AllergyPageModel page = new AllergyPageModel();

    page.title = "管理者確認画面";

    //noをキーにデータを更新
    allergyMapper.update_end(no);

    int customer_id = (int)session.getAttribute("customer_id");
    //リストを初期化
    page.list = allergyMapper.findAdo(customer_id);

    //モデルにページインスタンスを設定
    model.addAttribute("page", page);

    //テンプレートファイルを指定
    return "fastfood/adoministrator_confirmation";
}

}