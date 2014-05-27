package ma.cultura.emem.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "livro")
@NamedQueries({ 
	@NamedQuery(name = "Livro.findAll", query = "from Livro l WHERE TYPE(l) IN (Livro) order by l.id desc"),
	@NamedQuery(name = "Livro.pesquisarPorISBN", query = "from Livro l where l.isbn like :isbn and TYPE(l) IN (Livro) ")
})
public class Livro extends Obra {
	
	private static final long serialVersionUID = -5247199056256533770L;

}