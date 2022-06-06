package solekia.demo.fastfood.repository;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * ActorMapper
 */
public interface LoginMapper {
    @Select("select * from registered where (customer_id = #{customer_id}) and (password = #{password})")
    public List<LoginModel> findAccount(int customer_id, String password);

    @Update("update registered set login = #{login} where customer_id = #{customer_id} ")
public void login_out(
    @Param("customer_id")int customer_id,
    @Param("login")int login  
);
    /* 
    @Select("select * from actor where first_name || last_name like #{name}")
    public List<ActorModel> findName(String name);

    @Delete("delete from actor where actor_id = #{id}")
    public void delete(int id);

    @Select("select * from actor where actor_id = #{id}")
    public ActorModel findById(int id);

    @Update("update actor set first_name = #{first_name}, last_name = #{last_name} where actor_id = #{actor_id} ")
public void update(
    @Param("actor_id")int actor_id,
    @Param("first_name")String first_name,
    @Param("last_name")String last_name
  
);
  */
}
