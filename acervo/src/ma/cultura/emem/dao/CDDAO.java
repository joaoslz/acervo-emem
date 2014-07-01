package ma.cultura.emem.dao;


import java.util.ArrayList;
import java.util.List;

import ma.cultura.emem.modelo.CD;
import ma.cultura.emem.modelo.Musica;

public class CDDAO extends DAO<CD> {

	private static final long serialVersionUID = -7808363609000737042L;

	public CDDAO() {
		super(CD.class);
	}

//	@Override
//	public CD atualizar(CD cd) {
//		em.getTransaction().begin();
//		List<Musica> musicas = cd.getMusicas();
//		cd.setMusicas(new ArrayList<Musica>());
//		cd = em.merge(cd);
//		logger.debug("merge cd");
//		for(Musica m: musicas){
//			logger.debug("merge musica " + m);
//			m.setCd(cd);
//			em.merge(m);
//		}
//		em.getTransaction().commit();
//		return cd;
//	}
}