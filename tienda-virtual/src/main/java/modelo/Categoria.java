package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Categoria {

	@Id
	@GeneratedValue
	private int codigo;
	
	@NotNull
	@NotEmpty
	private String nombre;
	@Size( max=150)
	private String descripcion;
	
	private String imagen;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria")
	private List<ProductoStock> productos;

	public List<ProductoStock> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoStock> productos) {
		this.productos = productos;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
