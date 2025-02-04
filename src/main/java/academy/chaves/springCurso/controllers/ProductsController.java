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
    public ResponseEntity<ProductModel> saveProduct (@RequestBody @Valid ProductRecordDto productRecordDto){

        return productsService.saveProduct(productRecordDto);

    }

    @GetMapping("products")
    public ResponseEntity<List<ProductModel>> listAllProducts(){
        return productsService.listProducts();
    }

    // Usamos object quando pode haver mais de um retorno
    //Para pegar o ID é necessário o path variable

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> findProductById(@PathVariable(value = "id") UUID id){

        return productsService.listById(id);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Object> updateUpdate(@PathVariable(value="id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto){

        return productsService.editProduct(id, productRecordDto);

    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){

        return productsService.deleteProduct(id);

    }

}
