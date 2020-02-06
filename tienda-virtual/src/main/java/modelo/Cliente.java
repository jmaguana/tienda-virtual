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
	
	private String imagen;
	
	private String apellidos;
	
	private Date fechaNacimiento;

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private List<CarritoDetalle> carrito;
	
	@NotNull
	private String correo;
	
	@NotNull
	private String contrasenia;
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name = "votos")
	private List<ProductoStock> listaVotos;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private List<Compra> listaCompras;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "envia")
	private List<Compartido> listaEnviado;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "recibe")
	private List<Compartido> listaRecibido;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<CarritoDetalle> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<CarritoDetalle> carrito) {
		this.carrito = carrito;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public List<ProductoStock> getListaVotos() {
		return listaVotos;
	}

	public void setListaVotos(List<ProductoStock> listaVotos) {
		this.listaVotos = listaVotos;
	}

	public List<Compra> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(List<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}

	public List<Compartido> getListaEnviado() {
		return listaEnviado;
	}

	public void setListaEnviado(List<Compartido> listaEnviado) {
		this.listaEnviado = listaEnviado;
	}

	public List<Compartido> getListaRecibido() {
		return listaRecibido;
	}

	public void setListaRecibido(List<Compartido> listaRecibido) {
		this.listaRecibido = listaRecibido;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + ", apellidos=" + apellidos
				+ ", fechaNacimiento=" + fechaNacimiento + ", carrito=" + carrito + ", correo=" + correo
				+ ", contrasenia=" + contrasenia + ", listaVotos=" + listaVotos + ", listaCompras=" + listaCompras
				+ ", listaEnviado=" + listaEnviado + ", listaRecibido=" + listaRecibido + "]";
	}
}