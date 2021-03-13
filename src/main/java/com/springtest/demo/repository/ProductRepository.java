package com.springtest.demo.repository;

import com.springtest.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements ICrudRepository<Product>  {

    //Alternativ til klassisk Mapper måde, kortere syntaks

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(Product product) {
        String sql = "INSERT INTO products (Name, Price) VALUES (?,?)";
        template.update(sql, product.getName(), product.getPrice());
    }

    @Override
    public List<Product> readAll() {
        String SqlString = "SELECT * FROM products";
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
        return template.query(SqlString, rowMapper);
    }

    @Override
    public Product read(long id) {
        String sql = "SELECT * FROM products WHERE id=?";
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void update(Product product, int id) {
        System.out.println("product:" + product);
        System.out.println("id: " + id);
        String SqlString = "UPDATE products SET Name=?, Price=? WHERE id=?";
        template.update(SqlString, product.getName(), product.getPrice(),product.getId());
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM products WHERE id=?";
        template.update(sql, id);
    }

    @Override  //VIRKER IKKE RETURNERE "0" og ikke sidste id i tabel
    public long getLastId() {
        String SqlString = "SELECT MAX(id) FROM products";
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
        List<Product> a = template.query(SqlString, rowMapper);
        return a.get(a.size()-1).getId();
    }
}
