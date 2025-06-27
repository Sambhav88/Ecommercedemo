package com.Sambhav.Ecommerce.Service;

import com.Sambhav.Ecommerce.Repository.Productrepositry;
import com.Sambhav.Ecommerce.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService
{
    @Autowired
    Productrepositry repo;
//    ProductService()
//    {
//        System.out.println("Default Contructor");
//    }
//    ProductService(Productrepositry repo)
//    {
//        this.repo =repo;
//    }
    public List<Product> getproducts()
    {
        return repo.findAll();
    }

    public void addproduct(String productJson,MultipartFile imagefile) throws IOException {
        // Convert JSON string to Product object manually
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(productJson, Product.class);

        product.setImagename(imagefile.getName());
        product.setImagetype(imagefile.getContentType());
        product.setImagedata(imagefile.getBytes());

        repo.save(product);
    }

    public void deleteproduct(int productid)
    {
        repo.deleteById(productid);
    }

    public Product getproductbyid(int productid)
    {
        return repo.findById(productid).orElse(new Product());
    }

    public void updateproduct(String productJson, MultipartFile imagefile, int productid) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Product product =mapper.readValue(productJson,Product.class);

        product.setImagename(imagefile.getName());
        product.setImagetype(imagefile.getContentType());
        product.setImagedata(imagefile.getBytes());
        repo.save(product);

    }

    public List<Product> search(String keyword)
    {

        return repo.searchFeature(keyword);
    }
}
