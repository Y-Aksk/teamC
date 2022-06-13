/*package solekia.demo.fastfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import solekia.demo.fastfood.model.OrderPageModel;
//import solekia.demo.fastfood.repository.OrderMapper;


@Controller
@RequestMapping("fastfood")
public class OrderController {

    //Orderテーブルのアクセス用クラスを追加
    @Autowired
    //OrderMapper orderMapper;

    @GetMapping("order")
    public String order(Model model) {

        //タイトルリセット
        OrderPageModel page = new OrderPageModel();

        page.title = "注文画面";

        //page.list = orderMapper.findAll();

        model.addAttribute("page", page);

        return "fastfood/check"; //テンプレートファイルを指定
    }
}
*/