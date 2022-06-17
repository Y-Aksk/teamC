package solekia.demo.fastfood.repository;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * OrderMapper
 */
public interface OrderMapper {
    @Select("select * from product order by product_id")
    public List<OrderModel> findAll();

    public static void addition(String product_pic, String product_name, int price, int number, int customer_id) {
    }

    @Insert("insert into cart values((select max(no) + 1 from cart), #{product_id}, #{product_name}, #{price}, #{number}, #{customer_id} ")
    public String addition( 
    @Param("product_id")int product_id,
    @Param("product_name")String product_name,
    @Param("price")int price,
    @Param("number")int number,
    @Param("customer_id")int customer_id
    );
    
    @Select("select * from cart where customer_id = #{customer_id} ")
    public List<OrderModel> orderList(int customer_id);

    public static void list(String product_pic, String product_name, int price, int number, int customer_id) {
    }
    public List<OrderModel> list(int customer_id);
}