package solekia.demo.fastfood.model;

import java.util.List;

import solekia.demo.fastfood.repository.AllergyModel;

public class AllergyPageModel {

        public int product_id;
        public String product_name;
        public int price;
        public boolean flour;
        public boolean egg;
        public boolean milk;
        public boolean shrimp;
        public boolean crab;
        public boolean peanut;

        public String name;

        //タイトル
        public String title;
        //商品一覧
        public List<AllergyModel> list;




        
}