package modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Compartido {

	@Id
	@GeneratedValue
	private int id;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductoStock getProducto() {
		return producto;
	}

	public void setProducto(ProductoStock producto) {
		this.producto = producto;
	}

	public Cliente getClienteEnvia() {
		return clienteEnvia;
	}

	public void setClienteEnvia(Cliente clienteEnvia) {
		this.clienteEnvia = clienteEnvia;
	}

	public Cliente getClienteRecibe() {
		return clienteRecibe;
	}

	public void setClienteRecibe(Cliente clienteRecibe) {
		this.clienteRecibe = clienteRecibe;
	}
	
	
}
