package academy.chaves.springCurso.controllers;

import academy.chaves.springCurso.dtos.ProductRecordDto;
import academy.chaves.springCurso.models.ProductModel;
import academy.chaves.springCurso.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductsController {

    private final ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){

        var productModel = new ProductModel();

        BeanUtils.copyProperties(productRecordDto, productModel);


        return ResponseEntity.status(HttpStatus.CREATED
        ).body(productRepository.save(productModel));
    }

}
