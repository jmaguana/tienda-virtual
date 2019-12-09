package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import datos.CategoriaDAO;
import modelo.Categoria;

@ManagedBean
public class CategoriaBean {
	private Categoria categoria;
	private List<Categoria> lista;
	private int codigo;
	
	@Inject
	private CategoriaDAO cDao;

	@PostConstruct
	public void init() {
		categoria = new Categoria();
		loadCategoria();
	}
	
	public void loadCategoria() {
		lista = cDao.listar();
	}
	
	public String guardar() {
		cDao.insertar(categoria);
		loadCategoria();
		return "CategoriaCRUD";
	}
	
	public String editar(int codigo) {
		this.codigo = codigo;
		categoria = cDao.leer(codigo);
		return "CrearCategoria";
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getLista() {
		return lista;
	}

	public void setLista(List<Categoria> lista) {
		this.lista = lista;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}