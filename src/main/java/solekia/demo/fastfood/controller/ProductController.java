package solekia.demo.fastfood.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solekia.demo.fastfood.model.*;
import solekia.demo.fastfood.repository.*;

@Controller
// 処理対象のフォルダーを指定(fastfood)

@RequestMapping("fastfood")
public class ProductController {

    @Autowired
    ProductMapper ProductMapper;

    @Autowired
    HttpSession session;

    @Autowired
    //RagesterMapperをインスタンス化
    RegisterMapper registerMapper;

    @Autowired
    LoginMapper LoginMapper;

    @GetMapping("product")
    public String list(Model model) {
        //ページインスタンスを作って、タイトルを作成
        ProductPageModel page = new ProductPageModel();
        page.web_title = "メニュー";

        int customer_id = 0;
        if(session.getAttribute("customer_id") != null){
            customer_id = (int)session.getAttribute("customer_id");
            page.login = registerMapper.findById_login(customer_id);
            page.authority = LoginMapper.ditectAuth(customer_id);
        }

        //リストを初期化
        page.list = ProductMapper.findAll();

        //モデルにページインスタンスを設定
        model.addAttribute("page", page);

        return "fastfood/product"; //テンプレートファイルを指定
    }
}