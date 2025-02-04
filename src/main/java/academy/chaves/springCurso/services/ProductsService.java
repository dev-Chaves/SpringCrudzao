package academy.chaves.springCurso.services;

import academy.chaves.springCurso.models.ProductModel;
import academy.chaves.springCurso.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
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

}
