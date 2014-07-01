package ma.cultura.emem.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import ma.cultura.emem.modelo.Musica;

public class PopulaBDEmem {

	public static void main(String[] args) {

		try {
			SimpleDateFormat f = new SimpleDateFormat("mm:ss");
			Date d = f.parse("01:02");
			EntityManager em = new JPAUtil().getEntityManager();

			for (Musica m : em.createQuery("from Musica", Musica.class).getResultList()) {
				System.out.println(f.format(m.getDuracao()));
			}

			System.out.println(f.format(d));

			em.close();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
