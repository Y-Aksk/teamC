package solekia.demo.fastfood.repository;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * ShopMapper
 */
public interface ShopMapper {
    @Select("select * from shop order by shop_id")
    public List<ShopModel> findAll();

    @Select("select * from shop where shop_id = #{id}")
    public List<ShopModel> findShopName(int id);

}