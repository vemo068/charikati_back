package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Product;
import com.example.charikatiback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping("addproduct")
    public ResponseEntity<Product> postProduct(@RequestBody Product product) throws URISyntaxException{
        Product newProduct=Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stock(0)
                .build();

        newProduct=productRepository.save(newProduct);
        if (newProduct == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newProduct.getProductId())
                    .toUri();
            return ResponseEntity.created(uri).body(newProduct);
        }

    }
    @RequestMapping(value="deleteproduct", method = {RequestMethod.GET, RequestMethod.DELETE})
    public @ResponseBody
    void deleteProduct(@RequestParam("id") Long productId){

        productRepository.deleteById(productId);
    }

}
