package academy.chaves.springCurso.services;

import academy.chaves.springCurso.controllers.ProductsController;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

        List<ProductModel> productList = productRepository.findAll();

        if(!productList.isEmpty()){
            for (ProductModel product: productList){
                UUID id = product.getIdProduct();

                product.add(linkTo(methodOn(ProductsController.class).findProductById(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(productList);
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

    public ResponseEntity<Object> deleteProduct (@PathVariable(value = "id") UUID id){

        Optional<ProductModel> product0 = productRepository.findById(id);

        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BAD REQUEST");
        }

        productRepository.delete(product0.get());

        return ResponseEntity.status(HttpStatus.OK).body("Product Deleted");

    }

    public ResponseEntity<Object> editProduct(@PathVariable(value="id")UUID id, @RequestBody @Valid ProductRecordDto productRecordDto){

        Optional<ProductModel> product0 = productRepository.findById(id);

        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BAD REQUEST");
        }

        var productModel = new ProductModel();

        BeanUtils.copyProperties(productRecordDto, productModel);

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));

    }


}
