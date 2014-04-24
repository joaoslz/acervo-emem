package ma.cultura.emem.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value = "metodo")
@NamedQueries({ 
	@NamedQuery(name = "Metodo.listarTodos", query = "from Metodo m order by m.id desc")
})
public class Metodo extends Livro {

	private static final long serialVersionUID = 4617237052504448138L;

}
