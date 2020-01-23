package vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import modelo.ProductoStock;
import negocio.ControladorWeb;

@ManagedBean
@SessionScoped
public class InicioBean {
	
	private List<ProductoStock> lista;
	
	@Inject
	private ControladorWeb controlador;

	public List<ProductoStock> getLista() {
		return lista;
	}

	public void setLista(List<ProductoStock> lista) {
		this.lista = lista;
	}

	
	
}
