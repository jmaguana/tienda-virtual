package vista;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import datos.CategoriaDAO;
import modelo.Categoria;

@ManagedBean
@SessionScoped
public class CategoriaBean {
	private Categoria categoria;
	private List<Categoria> lista;
	private int codigo;
	private static Map<String,Object> listCategorias;
	
	@Inject
	private CategoriaDAO cDao;

	@PostConstruct
	public void init() {
		loadCategoria();
	}
	
	public void loadCategoria() {
		categoria = new Categoria();
		this.codigo = 0;
		lista = cDao.listar();
	}
	
	public Map<String,Object> listaCategorias(){
		listCategorias = new LinkedHashMap<String,Object>();
		loadCategoria();
		for(Categoria c: this.lista) {
			listCategorias.put(c.getNombre(), c.getCodigo());
		}
		return listCategorias;
	}
	
	public String guardar() {
		if(codigo == 0) {
			cDao.insertar(categoria);
		}else {
			cDao.actualizar(categoria);
		}
		loadCategoria();
		return "CategoriaCRUD";
	}
	
	public String editar(int codigo) {
		System.out.println("El codigo a Editar es: "+codigo);
		this.codigo = codigo;
		categoria = cDao.leer(codigo);
		if(categoria == null) {
			loadCategoria();
			return "CategoriaCRUD";
		}
		return "CrearCategoria";
	}
	
	public String eliminar(int codigo) {
		try{
			this.cDao.borrar(codigo);
		}catch(Exception e) {
			
		}
		loadCategoria();
		return "CategoriaCRUD";
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