package solekia.demo.fastfood.repository;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * ProductMapper
 */
public interface ProductMapper {
    @Select("select * from product order by product_id")
    public List<ProductModel> findAll();

}