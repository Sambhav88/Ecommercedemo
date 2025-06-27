package com.Sambhav.Ecommerce.Controller;

import com.Sambhav.Ecommerce.Service.ProductService;
import com.Sambhav.Ecommerce.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest;

import javax.xml.xpath.XPathVariableResolver;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Productcontroller
{
    @Autowired
    ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>>getproducts()
    {
        return new ResponseEntity<>(service.getproducts(), HttpStatus.OK);
    }
    @PostMapping("/products/add")
    public ResponseEntity<String>addproducts(@RequestPart("product") String productJson,
                            @RequestPart("image") MultipartFile imagefile) throws IOException
    {

        service.addproduct(productJson,imagefile);
       // return ResponseEntity.status(HttpStatus.CREATED).body("S2uccessfully");
        return new ResponseEntity<>("Successfully",HttpStatus.CREATED);
    }
    @PutMapping("products/update/{productid}")
    public void update(@RequestPart("product") String productJson,
                       @RequestPart("image") MultipartFile imagefile,
                       @PathVariable int productid) throws IOException
    {
        service.updateproduct(productJson,imagefile,productid);
    }

    @DeleteMapping("/products/delete/{productid}")
    public void Deleteproduct(@PathVariable int productid)
    {
        service.deleteproduct(productid);
    }
    @GetMapping("/products/{productid}")
    public Product getproduct(@PathVariable int productid)
    {
        return service.getproductbyid(productid);
    }
    @GetMapping("/products/image/{id}")
    public ResponseEntity<?> getProductImage(@PathVariable int id)
    {
        Product product = service.getproductbyid(id);
        if (product!=null &&product.getImagedata()!=null)
        {
            byte[] imageData =product.getImagedata();
            String imageType= product.getImagetype();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(imageType))
                    .body(imageData);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("X - Error Message ","Enter Correct Id");
            return ResponseEntity.ok().headers(headers).body("Product Id Not Found");
        }
    }
    @GetMapping("/product/search")
    ResponseEntity<List<Product>> searching(@RequestParam String keyword)
    {

       return  ResponseEntity.ok().body(service.search(keyword));
    }
}
