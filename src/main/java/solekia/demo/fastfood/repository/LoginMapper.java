package solekia.demo.fastfood.repository;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * ActorMapper
 */
public interface LoginMapper {
    //ok
    @Select("select * from registered where (customer_id = #{customer_id}) and (password = #{password});")
    public List<LoginModel> findAccount(
        @Param("customer_id")int customer_id,
        @Param("password")String password
    );

    //ok
    @Select("select authority from registered where customer_id = #{customer_id};")
    public int ditectAuth(int customer_id);

    //sql文はok パラメータのやり取りは調査中
    @Update("update registered set login = #{login} where customer_id = #{customer_id};")
    public int login_out(
    @Param("customer_id")int customer_id,
    @Param("login")int login  
    );

    //ok
    @Select("select * from registered where customer_id = #{customer_id};")
    public List<LoginModel> showMypage(int customer_id);

    //ok
    @Select("select count(*) from cart where order_id is not null;")
    public int countOrder();

    @Delete("delete from registered where customer_id = #{id}")
    public void delete(int id);
}
