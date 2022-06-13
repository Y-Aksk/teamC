package solekia.demo.fastfood.repository;
import java.util.List;
import org.apache.ibatis.annotations.Select;


public interface AllergyMapper {
    @Select("select * from product product_id ")
    public List<AllergyModel> findAll();

    @Select("select * from product where product_name like #{name}")
    public List<AllergyModel> findName(String name);

    //@Select("select * from product prouct_id ")
    //public Lis<AllergyModel> 

}
