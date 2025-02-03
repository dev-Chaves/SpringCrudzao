package academy.chaves.springCurso.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel implements Serializable {

    // Número de Controle de versão de cada uma dessas classes quando for inicialziada;
    // Evita erros de incompatibilidade na serialização de objetos
    private static final long serialVersionUID = 1L;

    //    @Id faz com que a variável idProduct sejá reconhecida como chave primária da entidade no banco de dados
    //    E o GeneratedValue, gerará um valor aleatório para esse ID

    //    UUID são identificadores distribuidos, evita conflito, são universais.
    //    Id/GeneraredValue, essas duas anotações permitem identificar esses identificadores

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID idProduct;
    private String name;
    private BigDecimal value;

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
