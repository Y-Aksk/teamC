package solekia.dvdrental.repository;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * CustomerMapper
 */
public interface CustomerMapper {
    @Select("select * from customer order by customer_id")
    public List<CustomerModel> findAll();

    @Select("select * from customer where first_name || last_name || email like #{name}")
    public List<CustomerModel> findName(String name);
    
    @Delete("delete from customer where customer_id = #{id}")
    public void delete(int id);

    @Select("select * from customer where customer_id = #{id}")
    public CustomerModel findById(int id);

    @Update("update customer set first_name = #{first_name}, last_name = #{last_name}, email = #{email} where customer_id = #{customer_id} ")
    public void update(
    @Param("customer_id")int customer_id,
    @Param("first_name")String first_name,
    @Param("last_name")String last_name,
    @Param("email")String email
);

/*
元のinsert文
insert into customer (first_name, last_name, email) values(#{first_name}, #{last_name}, #{email}
*/ 
   @Insert("insert into customer values((select max(customer_id) + 1 from customer), '1', #{first_name}, #{last_name}, #{email}, '5', true, now(), now(), 1) ")
   public void addition(
    @Param("first_name")String first_name,
    @Param("last_name")String last_name,
    @Param("email")String email
);

    @Select(
    "select district, address, address2 from address left outer join customer on customer.address_id = address.address_id "+
    "where address.address_id = #{id} group by district, address, address2")
    public List<CustomerModel> findAddress(int id);

    @Select(
    "select title from inventory join film on inventory.film_id = film.film_id " +
    "left outer join rental on inventory.inventory_id = rental.inventory_id " + 
    "where customer_id = #{id} order by rental_date desc limit 10")
    public List<CustomerModel> rental_record(int id);
}
