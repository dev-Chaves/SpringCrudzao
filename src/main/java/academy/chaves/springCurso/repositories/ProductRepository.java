package academy.chaves.springCurso.repositories;

import academy.chaves.springCurso.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// Alterar para ser uma INTERFACE E NÂO CLASSE

// Extends, errar a classe do JpaRepository, que irá habilitar métodos, como parametro você irá passar seu model(DB) e o ID(UUID) que seja;


//Identificar pro Spring que essa Interface será um bean do tipo repositório, nesse caso o JPA Repository já reconhece, mas deixa essa porra ai, pra não dar erro!

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
