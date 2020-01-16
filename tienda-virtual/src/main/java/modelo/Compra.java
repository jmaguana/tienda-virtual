package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Compra {
 
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "compra_id")
	private List<ProductoVendido> listaProductos;
	
	private double total;
	
	private Date Fecha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ProductoVendido> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<ProductoVendido> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	
	
	
}
