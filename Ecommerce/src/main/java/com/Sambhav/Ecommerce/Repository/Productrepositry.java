package com.Sambhav.Ecommerce.Repository;

import com.Sambhav.Ecommerce.model.Product;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Productrepositry extends JpaRepository<Product, Integer>
{
    //@Query(value = "select * from product where name like concat('%',keyword,'%')",nativeQuery = true)
    @Query(value = "SELECT * FROM product WHERE name LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    public List<Product> searchFeature(String keyword);
}
