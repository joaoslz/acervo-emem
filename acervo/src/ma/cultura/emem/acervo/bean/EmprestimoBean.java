package ma.cultura.emem.acervo.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ma.cultura.emem.acervo.dao.DAO;
import ma.cultura.emem.acervo.modelo.Exemplar;
import ma.cultura.emem.acervo.modelo.Usuario;
import ma.cultura.emem.acervo.util.jsf.FacesMessages;

import org.apache.log4j.Logger;

@ViewScoped
@Named
public class EmprestimoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	@Inject
	private FacesMessages facesMsg;
	
	@Inject
	private DAO<Exemplar> exemplarDAO;
	@Inject
	private DAO<Usuario> usuarioDAO;

	private Exemplar exemplar;
	private Usuario usuario;
	
	
	public List<Exemplar> findExemplar(String id){
		return exemplarDAO.findById(id);
	}
	
	public List<Usuario> findUsuario(String nome){
		return usuarioDAO.findByNome(nome);
	}
	
	public void verificarStatusEmprestimo(){
		//TODO
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}