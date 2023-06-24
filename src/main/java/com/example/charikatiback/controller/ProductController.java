package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Product;
import com.example.charikatiback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("allproducts")
    public List<Product> getAllProducts(){
        return productRepository.findAll(

        );
    }

    @GetMapping("products")
    public Page<Product> getProducts(
            @RequestParam Optional<Integer> page

            ){
        return productRepository.findByIsDeleted(false, PageRequest.of(
                page.orElse(0),
                15,
                Sort.Direction.ASC ,"name"

        ));
    }

    @PostMapping("addproduct")
    public ResponseEntity<Product> postProduct(@RequestBody Product product) throws URISyntaxException{
        Product newProduct=Product.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .sellPrice(product.getSellPrice())
                .buyPrice(product.getBuyPrice())
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

        Product product=productRepository.findByProductId(productId);
        product.setDeleted(true);
        productRepository.save(product);

    }

}
