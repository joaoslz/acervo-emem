//package ma.cultura.emem.modelo;
//
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//
//import ma.cultura.emem.modelo.auxiliar.Cantor;
//import ma.cultura.emem.modelo.auxiliar.Gravadora;
//import ma.cultura.emem.modelo.auxiliar.Midia;
//
//@Entity
//@NamedQueries({ 
//	@NamedQuery(name = "CD.findAll", query = "from CD cd order by cd.id desc")
//})
//public class CD extends ItemAcervo {
//
//	private static final long serialVersionUID = -1302773887232086520L;
//
//	private String comentario;
//
//	
//	@ManyToOne
//	private Gravadora gravadora;
//	
//	@ManyToOne
//	private Midia midia;
//	
//	@ManyToMany
//	private List<Cantor> cantores;
//	
//	@OneToMany(mappedBy = "cd")
//	private List<Musica> musicas;
//	
//	public String getComentario() {
//		return comentario;
//	}
//
//	public void setComentario(String comentario) {
//		this.comentario = comentario;
//	}
//
//	public List<Musica> getMusicas() {
//		return musicas;
//	}
//
//	public void setMusicas(List<Musica> musicas) {
//		this.musicas = musicas;
//	}
//
//	public Gravadora getGravadora() {
//		return gravadora;
//	}
//
//	public void setGravadora(Gravadora gravadora) {
//		this.gravadora = gravadora;
//	}
//
//	public Midia getMidia() {
//		return midia;
//	}
//
//	public void setMidia(Midia midia) {
//		this.midia = midia;
//	}
//
//	public List<Cantor> getCantores() {
//		return cantores;
//	}
//
//	public void setCantores(List<Cantor> cantores) {
//		this.cantores = cantores;
//	}	
//}