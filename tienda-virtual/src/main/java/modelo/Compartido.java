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
	
	@ManyToOne
	@JoinColumn(name = "producto_compartido")
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
	
}
