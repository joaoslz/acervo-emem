package ma.cultura.emem.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exemplar {

    @Id
    @GeneratedValue
    private int id;
    private boolean ehDoacao;

    @ManyToOne
    private Obra obra;

    @Column(columnDefinition = "text")
    private String observacao;

    public Obra getObra() {
	return obra;
    }

}
