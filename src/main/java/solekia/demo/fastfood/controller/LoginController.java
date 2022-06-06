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

    @PostMapping("search")
    public String search(@RequestParam("name")int customer_id, String password, Model model){
        //ページインスタンスを作って、タイトルを設定
        LoginPageModel page = new LoginPageModel();
        page.title = "顧客一覧画面(Java)";

        //リスト初期化(IDとパスワード分けないといけないかも)
        page.list = LoginMapper.findAccount(customer_id, password);
        
        //page.hold_id = customer_id;


        if(!(page.list == null)){
            int login = 1;
            page.login = LoginMapper.login_out(customer_id, login);
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
}
