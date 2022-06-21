package solekia.demo.fastfood.repository;
import java.util.List;


import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface AllergyMapper {
    @Select("select * from product order by product_id ")
    public List<AllergyModel> findAll();

    @Select("select * from product where product_name like #{name}")
    public List<AllergyModel> findName(String name);

    @Select("select * from cart ")
    public List<AllergyModel> findStatus();

    //追加：cartとorder by の間に　number != 0を追加
    @Select("select * from cart where number != 0 order by no")
    public List<AllergyModel> findAdo();

    //変更してます。丸々変えてください。：待ち組数数える
    @Select ("select customer_id, count(customer_id) from cart WHERE order_id = 0 Group by customer_id")
    public List<AllergyModel> findGroup();

     //マイページのボタンを押したとき　顧客IDごとに表示を変える
     //cartとproductを内部結合　写真を持ってくる
    @Select("select * from cart inner join product on cart.product_id = product.product_id  and  cart.customer_id = #{id} and number > 0 order by cart.product_id")
    public List<AllergyModel> findByCustId(int id);

    //オーダーIDを1に変更する
    @Update("update cart set order_id = 1 where no = #{no}")
    public void update(int no);

    //オーダーIDが0のものをリストに入れる
    @Select ("select order_id from cart where order_id = 0 and customer_id = #{id} and number != 0")
    public List<AllergyModel> findGroupOrder(int id);

    //追加 オーダーIDを2に変更する
    @Update("update cart set order_id = 2 where no = #{no} and order_id = 1")
    public void update_end(int no);
    
    //追加　オーダーIDが1のものをリストに入れる  and number != 0を一番後ろに追加
    @Select ("select order_id from cart where order_id = 1 and customer_id = #{id} and number != 0")
    public List<AllergyModel> findGroupOrder1(int id);

    //追加　オーダーIDが2のものをリストに入れる  and number != 0を一番後ろに追加
    @Select ("select order_id from cart where order_id = 2 and customer_id = #{id} and number != 0")
    public List<AllergyModel> findGroupOrder2(int id);
}