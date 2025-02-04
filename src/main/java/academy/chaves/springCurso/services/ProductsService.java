package academy.chaves.springCurso.services;

import academy.chaves.springCurso.dtos.ProductRecordDto;
import academy.chaves.springCurso.models.ProductModel;
import academy.chaves.springCurso.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {

   private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<ProductModel>> listProducts(){

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    public ResponseEntity<Object> listById(@PathVariable(value = "id") UUID id){

        // Optional By id

        Optional<ProductModel> product0 = productRepository.findById(id);

        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BAD REQUEST");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product0.get());

    }

    public ResponseEntity<ProductModel> saveProduct(@RequestBody@Valid ProductRecordDto productRecordDto){

        var productModel = new ProductModel();

        BeanUtils.copyProperties(productRecordDto, productModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));

    }

}
