package ma.cultura.emem.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import ma.cultura.emem.modelo.Autor;
import ma.cultura.emem.modelo.Livro;
import ma.cultura.emem.modelo.Obra;

public class PopulaBDEmem {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		for(Obra l: em.createQuery("from Obra a order by a.id desc", Obra.class).getResultList()){
			System.out.println(l.getClass().getSimpleName());
		}
		em.close();

	}

	private static Autor geraAutor(String nome) {
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
	}

	private static Livro geraLivro(String isbn, String titulo, String data, Autor autor) {
		Livro livro = new Livro();
		livro.setIsbn(isbn);
		livro.setTitulo(titulo);
		livro.adicionarAutor(autor);
		return livro;
	}

	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
