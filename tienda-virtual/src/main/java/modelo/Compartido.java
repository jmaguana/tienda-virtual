package modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * En esta clase tenemos todos aquellos atributos <br>
 * y elementos que son necesarios en esta clase
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

@Entity
public class Compartido {

	/**
	 * Atributo de la clase
	 */
	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * Relacion de muchos a uno con la tabla de producto y cliente
	 */	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "producto")
	@NotNull
	private ProductoStock producto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "envia")
	@NotNull
	private Cliente clienteEnvia;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recibe")
	@NotNull
	private Cliente clienteRecibe;

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
	 * @return producto, de tipo ProductoStock
	 */
	public ProductoStock getProducto() {
		return producto;
	}

	/**
	 * 
	 * @param producto, que viene de la clase ProductoStock
	 */
	public void setProducto(ProductoStock producto) {
		this.producto = producto;
	}

	/**
	 * 
	 * @return clienteEnvia, de tipo Cliente lo cual permite <br>
	 * saber que cliente envia un producto a otro.
	 */
	public Cliente getClienteEnvia() {
		return clienteEnvia;
	}

	/**
	 * 
	 * @param clienteEnvia, de tipo Cliente
	 */
	public void setClienteEnvia(Cliente clienteEnvia) {
		this.clienteEnvia = clienteEnvia;
	}

	/**
	 * 
	 * @return clienteRecibe, de tipo Cliente lo cual permite <br>
	 * saber que cliente recibe un producto de otro cliente.
	 */
	public Cliente getClienteRecibe() {
		return clienteRecibe;
	}

	/**
	 * 
	 * @param clienteRecibe, de tipo Cliente
	 */
	public void setClienteRecibe(Cliente clienteRecibe) {
		this.clienteRecibe = clienteRecibe;
	}
}