package ma.cultura.emem.modelo.auxiliar;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ma.cultura.emem.modelo.BaseEntity;
import ma.cultura.emem.modelo.ItemAcervo;

@Entity
public class Editora extends BaseEntity {

	private static final long serialVersionUID = -7971911155752471160L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 100, nullable = false, unique = true)
	private String nome;

	@Column(length = 100)
	private String site;

	// bi-directional many-to-one association to ItemAcervo
	@OneToMany(mappedBy = "editora")
	private List<ItemAcervo> itemAcervos;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null) {
			this.nome = nome.toUpperCase();
		} else {
			this.nome = null;
		}
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public List<ItemAcervo> getItemAcervos() {
		return itemAcervos;
	}

	public void setItemAcervos(List<ItemAcervo> itemAcervos) {
		this.itemAcervos = itemAcervos;
	}
}
