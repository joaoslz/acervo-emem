package ma.cultura.emem.modelo;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "metodo")
public class Metodo extends Livro {


    //private String instrumento ?????
    //private String genero      ?????
}
