package academy.chaves.springCurso.controllers;

import academy.chaves.springCurso.dtos.ProductRecordDto;
import academy.chaves.springCurso.models.ProductModel;
import academy.chaves.springCurso.repositories.ProductRepository;
import academy.chaves.springCurso.services.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@RestController
public class ProductsController {

    private final ProductRepository productRepository;

    private ProductsService productsService;

    public ProductsController(ProductRepository productRepository, ProductsService productsService ) {
        this.productRepository = productRepository;
        this.productsService = productsService;
    }


    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){

        var productModel = new ProductModel();

        BeanUtils.copyProperties(productRecordDto, productModel);


        return ResponseEntity.status(HttpStatus.CREATED
        ).body(productRepository.save(productModel));
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductModel>> listAllProducts(){
        return productsService.listProducts();
    }

//    @GetMapping("/products")
//    public ResponseEntity<List<ProductModel>> getAllProducts(){
//        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
//    }

    // Usamos object quando pode haver mais de um retorno
    //Para pegar o ID é necessário o path variable

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> findProductById(@PathVariable(value = "id") UUID id){

        return productsService.listById(id);
    }


//    @GetMapping("/products/{id}")
//    public ResponseEntity<Object> getIdProducts(@PathVariable(value="id") UUID id){
//
//        Optional<ProductModel> product0 = productRepository.findById(id);
//
//        if(product0.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
//
//    }

    @PutMapping("products/{id}")
    public ResponseEntity<Object> updateUpdate(@PathVariable(value="id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto){

        Optional<ProductModel> product0 = productRepository.findById(id);

        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        var productModel = product0.get();
        BeanUtils.copyProperties(productRecordDto, productModel);

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));

    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){

        Optional<ProductModel> product0 = productRepository.findById(id);

        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BAD REQUEST");
        }

        productRepository.delete(product0.get());

        return ResponseEntity.status(HttpStatus.OK).body("Product Deleted succesfully.");

    }


}
