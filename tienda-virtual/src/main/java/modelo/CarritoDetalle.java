package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class CarritoDetalle {
	
	@Id
	@GeneratedValue
	private int id; 
	
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name = "producto")
	private ProductoStock producto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ProductoStock getProducto() {
		return producto;
	}

	public void setProducto(ProductoStock producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "CarritoDetalle [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + "]";
	}
}
