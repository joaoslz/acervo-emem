package ma.cultura.emem.modelo;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "metodo")
public class Metodo extends Obra {

    private static final long serialVersionUID = -1L;

    //private String instrumento ?????
    //private String genero      ?????
}
