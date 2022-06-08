package solekia.demo.fastfood.repository;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * ActorMapper
 */
public interface LoginMapper {
    @Select("select * from registered where (customer_id = #{customer_id}) and (password = #{password})")
    public List<LoginModel> findAccount(int customer_id, String password);

    @Select("select authority from registered where customer_id = #{customer_id}")
    public int ditectAuth(int customer_id);

    @Update("update registered set login = #{login} where customer_id = #{customer_id} ")
    public void login_out(
    @Param("customer_id")int customer_id,
    @Param("login")int login  
    );

    @Select("select * from registered where customer_id = #{customer_id}")
    public List<LoginModel> showMypage(int customer_id);
}
