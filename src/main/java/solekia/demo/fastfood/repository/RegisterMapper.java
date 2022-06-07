package solekia.demo.fastfood.repository;

import java.util.List;

//RegisterMapper
public class RegisterMapper{

    //お問い合わせ
    //registeredテーブル（データベース）から全てのデータを取得
    @Select("select * from qa order by qa_id") 
    //上の処理で取得したデータをリストに格納
    public List<RegisterModel> findQa();

    @Insert("insert into registered (qa_id, first_name, last_name, question, mail) values(#{qa_id], #{qa_first_name}, #{qa_last_name}, #{question}, #{qa_mail}")
        public void insertqa(
        @Param("qa_id")int qa_id,
        @Param("first_name")String qa_first_name,
        @Param("last_name")String qa_last_name,
        @Param("email")String qa_mail
        );

    //新規会員登録
    //registeredテーブル（データベース）から全てのデータを取得
    @Select("select * from registered order by customer_id") 
    //上の処理で取得したデータをリストに格納
    public List<RegisterModel> findRe();
    
    @Insert("insert into registered (first_name, last_name, tell_no, mail, login, authority, shop_name) values(#{first_name}, #{last_name}, #{tell_no], #{mail}, #{store_id}, 1, 0, #{shop_name})")
        public void insert(
        @Param("first_name")String first_name,
        @Param("last_name")String last_name,
        @Param("tell_no")String tell_no,
        @Param("email")String mail,
        @Param("login") int login,
        @Param("authority")int authority,
        @Param("shop_name")String shop_name
        );
    }
