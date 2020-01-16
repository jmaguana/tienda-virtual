package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {
	 
	@Id
	@NotNull
	@Column(length = 10)
	@GeneratedValue
	private int id;
	
	private String nombre;
	
	private String apellidos;
	
	
	private Date fechaNacimiento;

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<CarritoDetalle> carrito;
	
	@NotNull
	private String correo;
	
	@NotNull
	private String contrasenia;
	
	@ManyToMany(mappedBy = "votos")
	private List<ProductoStock> listaVotos;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private List<Compra> listaCompras;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "envia")
	private List<Compartido> listaEnviado;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "recivbe")
	private List<Compartido> listaRecibido;
	
	
}


