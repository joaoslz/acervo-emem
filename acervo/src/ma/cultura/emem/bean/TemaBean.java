package ma.cultura.emem.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
// vive dentro da sess�o Http
public class TemaBean implements Serializable {

	private String tema = "aristo"; // tema padr�o

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	// temas dispon�veis dentro do JAR
	public List<String> getTemas() {

		return Arrays.asList("aristo", "black-tie", "blitzer", "bluesky", "casablanca", "cupertino", "dark-hive",
				"dot-luv", "eggplant", "excite-bike", "flick", "glass-x", "hot-sneaks", "humanity", "le-frog",
				"midnight", "mint-choc", "overcast", "pepper-grinder", "redmond", "rocket", "sam", "smoothness",
				"south-street", "start", "sunny", "swanky-purse", "trontastic", "ui-darkness", "ui-lightness", "vader");
	}
}