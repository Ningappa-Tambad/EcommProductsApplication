package com.ecommerce.product.controller;


import com.ecommerce.product.dtos.ProductRequest;
import com.ecommerce.product.dtos.ProductResponse;
import com.ecommerce.product.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController
{

    private final ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest)
    {
        // Call the service to create the product
        // productService.createProduct(productRequest);

        // Return a response indicating success
         ProductResponse productResponse = productService.CreateProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    //uPDATE PRODUCT

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,
                                                         @PathVariable Long id)
    {
        // Call the service to create the product
        // productService.createProduct(productRequest);

        // Return a response indicating success
     return  productService.updateProduct(id,productRequest)
               .map(productResponse -> ResponseEntity.ok(productResponse))
                .orElse(ResponseEntity.notFound().build());

    }

    //Get product details by id

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id)
    {
        return productService.fetchSingleProduct(id)
                .map(productResponse -> ResponseEntity.ok(productResponse))
                .orElse(ResponseEntity.notFound().build());

    }

    //get all the products

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts()
    {
        List<ProductResponse> productResponses = productService.fetchAllProducts();
        return ResponseEntity.ok(productResponses);
    }


    //Delete the products by id

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id)
    {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }



    //Delete all the products


    @DeleteMapping
    public ResponseEntity<Void> deleteAllProducts()
    {
        productService.deleteAllProducts();
        return ResponseEntity.ok().build();
    }

    //Search products

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String name)
    {
        List<ProductResponse> productResponses = productService.searchProducts(name);
        return ResponseEntity.ok(productResponses);
    }
}
