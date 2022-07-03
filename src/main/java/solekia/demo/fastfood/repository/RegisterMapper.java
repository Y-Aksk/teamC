package solekia.demo.fastfood.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//RegisterMapper
public interface RegisterMapper {


/*----　ホーム画面用　----*/
    //productテーブルからデータを取得（人気メニュー用上位3件）
    @Select("SELECT product_name, product_pic, rank FROM (SELECT RANK() OVER (ORDER BY sales DESC) AS rank, product_pic, product_name FROM product) AS product WHERE rank <= 3 ORDER BY rank")
    public List<RegisterModel> findRank();


/*----  お問い合わせフォーム ----*/
    //qaテーブルにデータを追加 
    //(select max(customer_id) + 1 from qa)の追加
    @Insert("INSERT INTO qa values((select max(qa_id) + 1 from qa),#{qa_first_name}, #{qa_last_name}, #{qa_mail}, #{question})")
    public void insertQa(
        @Param("qa_first_name")String qa_first_name,
        @Param("qa_last_name")String qa_last_name,
        @Param("qa_mail")String qa_mail,
        @Param("question")String question
    );


/*----会員登録と変更の両方に使用するSQL----*/
    //shopテーブルからデータを取得（店舗選択のプルダウンメニュー用）
    @Select("SELECT shop_name FROM shop")
    public List<RegisterModel> findShop();


/*----新規会員登録----*/
    //registeredテーブルにデータを追加　loginは0　authorityは0
    @Insert("INSERT INTO registered values((select max(customer_id) + 1 from registered), #{password}, #{first_name}, #{last_name}, #{tell_no}, #{mail}, 0, 0, #{shop_name})")
    public void insertCust(
        @Param("password")String password,
        @Param("first_name")String first_name,
        @Param("last_name")String last_name,
        @Param("tell_no")String tell_no,
        @Param("mail")String mail,
        @Param("shop_name")String shop_name
    );


/*----新規従業員登録----*/
    //registeredテーブルにデータを追加　loginは0 authorityは1
    @Insert("INSERT INTO registered (customer_id, password, first_name, last_name, tell_no, mail, login, authority, shop_name) values((select max(customer_id) + 1 from registered), #{password}, #{first_name}, #{last_name}, #{tell_no}, #{mail}, 0, 1, #{shop_name})")
    public void insertEmp(
        @Param("password")String password,
        @Param("first_name")String first_name,
        @Param("last_name")String last_name,
        @Param("tell_no")String tell_no,
        @Param("mail")String mail,
        @Param("shop_name")String shop_name
    );


/*----　顧客/従業員　登録情報変更　----*/
    //registeredテーブルからデータを取得…登録後の情報を表示するため
    @Select("SELECT * FROM registered")
    public List<RegisterModel> findRe();
    
    //customer_idの検索　IDと一致しているデータベースの情報を持ってくる
    @Select("SELECT * from registered WHERE customer_id = #{id}")
    public RegisterModel findById(int id);

    //registeredテーブルのデータを更新
    @Update("UPDATE registered set password = #{password}, first_name = #{first_name}, last_name = #{last_name}, tell_no = #{tell_no}, mail = #{mail}, shop_name = #{shop_name}, login = #{login} WHERE customer_id = #{customer_id}")
    public void update(
        @Param("customer_id")int customer_id,
        @Param("password")String password,
        @Param("first_name")String first_name,
        @Param("last_name")String last_name,
        @Param("tell_no")String tell_no,
        @Param("mail")String mail,
        @Param("shop_name")String shop_name,
        @Param("login")int login
    );

    //registeredテーブルから顧客ごとのログイン状態を取得
  @Select("select login from registered where customer_id = #{id}")
  public int findById_login(int id);
}