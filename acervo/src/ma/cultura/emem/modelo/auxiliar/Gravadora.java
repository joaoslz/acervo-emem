//package ma.cultura.emem.modelo.auxiliar;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//
//import ma.cultura.emem.modelo.BaseEntity;
//
//@Entity
//@NamedQueries({ 
//	@NamedQuery(name = "Gravadora.findAll", query = "from Gravadora g order by g.id desc"),
//	@NamedQuery(name = "Gravadora.findByNome", query = "from Gravadora g where g.nome like :nome order by g.nome asc")
//})
//public class Gravadora extends BaseEntity {
//
//	private static final long serialVersionUID = -545883450140785764L;
//
//	@Id
//	@GeneratedValue
//	private Integer id;
//	
//	@Column(length=100, nullable=false)
//	private String nome;
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	@Override
//	public String toString() {
//		return this.nome;
//	}
//}
