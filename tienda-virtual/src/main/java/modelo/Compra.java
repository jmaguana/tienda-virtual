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
import javax.validation.constraints.NotNull;

/**
 * En esta clase tenemos todos aquellos atributos <br>
 * y elementos que son necesarios para realizar la compra
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

@Entity
public class Compra {
 
	/**
	 * Atributo de la clase
	 */	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	private double total;
	
	@NotNull
	private Date Fecha;
	
	/**
	 * Relacion de uno a muchos con la tabla de productoVendido
	 */	
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "compra_id")
	private List<ProductoVendido> listaProductos;

	/**
	 * 
	 * @return codigo perteneciente a la tabla Compartido
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id, codigo de la clase Compartido
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return total a pagar por la compra realizada
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * 
	 * @param total, valor a pagar por la compra
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * 
	 * @return fecha que se ha realizado la compra
	 */
	public Date getFecha() {
		return Fecha;
	}

	/**
	 * 
	 * @param fecha que se ha realizado la compra
	 */
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	
	/**
	 * 
	 * @return listaProductos de tipo ProductoVendido
	 */
	public List<ProductoVendido> getListaProductos() {
		return listaProductos;
	}

	/**
	 * 
	 * @param listaProductos, de tipo ProductoVendido
	 */
	public void setListaProductos(List<ProductoVendido> listaProductos) {
		this.listaProductos = listaProductos;
	}	
}