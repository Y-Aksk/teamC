package solekia.dvdrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solekia.dvdrental.model.*;
import solekia.dvdrental.repository.*;


/**
 * CustomerController
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerMapper customerMapper;

    @GetMapping("list")
    public String list(Model model) {
        //ページインスタンスを作って、タイトルを作成
        CustomerPageModel page = new CustomerPageModel();
        page.title = "顧客一覧画面（Java）";

        //リストを初期化
        page.list = customerMapper.findAll();
        
        //ユーザ情報を設定
        /*
        CustomerModel user1 = new CustomerModel();
        user1.customer_id = 1;
        user1.first_name = "java";
        user1.last_name = "test1";
        user1.email = "test1@java.com";
        page.list.add(user1);

        CustomerModel user2 = new CustomerModel();
        user2.customer_id = 1;
        user2.first_name = "java";
        user2.last_name = "test2";
        user2.email = "test2@java.com";
        page.list.add(user2);
        */

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);

        return "customer/list"; //テンプレートファイルを指定
    }

    @PostMapping("search")
    public String search(@RequestParam("name") String name, Model model){
        //ページインスタンスを作って、タイトルを設定
        CustomerPageModel page = new CustomerPageModel();
        page.title = "顧客一覧画面(Java)";

        //リスト初期化
        page.list = customerMapper.findName("%" + name + "%");
        
        page.hold_name = name;

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //model.addAttribute("name", name);

        //テンプレートファイルを指定
        return "customer/list";
    }

    @Transactional
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        //ページインスタンスを作って、タイトルを設定
        CustomerPageModel page = new CustomerPageModel();
        page.title = "顧客一覧画面(Java)";

        //IDをキーにデータを削除
        customerMapper.delete(id);

        //リスト初期化
        page.list = customerMapper.findAll();
        
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //model.addAttribute("name", name);

        //テンプレートファイルを指定
        return "customer/list";

    }

    //編集画面
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        //ページインスタンスを作って、タイトルを設定
        CustomerPageModel page = new CustomerPageModel();

        //IDをキーにデータを検索
        var customer = customerMapper.findById(id);

        //取得データをページに設定
        page.customer_id = customer.customer_id;
        page.first_name = customer.first_name;
        page.last_name = customer.last_name;
        page.email = customer.email;

        //モデルページにインスタンスを生成
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "customer/edit";
    }

    @PostMapping("update")
    public String edit(@ModelAttribute CustomerPageModel page, Model model){
        //タイトルを設定
        page.title = "顧客一覧画面（Java）";

        //画面で入力した更新データをパラメータに設定
        customerMapper.update(page.customer_id, page.first_name, page.last_name, page.email);

        //更新後のデータを取得
        page.list = customerMapper.findAll();

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "customer/list";
    } 


    //追加の処理（addition.htmlでやること）
    @GetMapping("addition")
    public String addition(Model model){
        //ページインスタンスを作って、タイトルを設定
        CustomerPageModel page = new CustomerPageModel();
        page.title = "登録画面";

        //モデルページにインスタンスを生成
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "customer/addition";
    }

    @PostMapping("addition")
    public String addition(
        @RequestParam("first_name") String first_name, 
        @RequestParam("last_name") String last_name,
        @RequestParam("email") String email, 
        Model model){
        //ページインスタンスを作って、タイトルを設定
        CustomerPageModel page = new CustomerPageModel();
        page.title = "顧客一覧画面(Java)";

        //リスト初期化
        customerMapper.addition(first_name, last_name, email);
        
        //更新後のデータを取得
        page.list = customerMapper.findAll();

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);

        //テンプレートファイルを指定
        return "customer/list";
    }

    //詳細ページ(住所)
    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") int id, Model model){
        //アドレスインスタンスを作って、タイトルを設定
        CustomerPageModel customer_address = new CustomerPageModel();
        //IDをキーに住所を検索
        customer_address.list = customerMapper.findAddress(id);

        //履歴インスタンスを作って、タイトルを設定
        CustomerPageModel customer_record = new CustomerPageModel();
        //IDをキーに個人の履歴を検索
        customer_record.list = customerMapper.rental_record(id);

        //モデルページに住所のインスタンスを生成
        model.addAttribute("customer_address", customer_address);

        //モデルページに履歴のインスタンスを生成
        model.addAttribute("customer_record", customer_record);

        //テンプレートファイルを指定
        return "Customer/detail";
    }

}
 