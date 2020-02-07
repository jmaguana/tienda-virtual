package modelo;

//import java.util.Date;
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

/**
 * En esta clase tenemos todos aquellos atributos <br>
 * y elementos que son necesarios para un cliente
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Entity
public class Cliente {
	 
	/**
	 * Atributos de la clase
	 */
	@Id
	@NotNull
	@Column(length = 10)
	@GeneratedValue
	private int id;
	
	@NotNull
	private String nombre;
	
	//private String imagen;
	
	@NotNull
	private String apellidos;
	
	//private Date fechaNacimiento;

	@NotNull
	private String telefono;
	
	@NotNull
	private String correo;
	
	@NotNull
	private String contrasenia;
	
	/**
	 * Relaciones de uno a muchos que son necesarias para que el cliente <br>
	 * tenga una relacion con las diferentes tablas como son carrito detalle 
	 * o factura detalle, compra y compartido
	 */
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private List<CarritoDetalle> carrito;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private List<Compra> listaCompras;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "envia")
	private List<Compartido> listaEnviado;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "recibe")
	private List<Compartido> listaRecibido;
	
	
	/**
	 * Relacion de muchos a muchos con la tabla de productos para obtener <br>
	 * la cantidad de votos que se le han dado
	 */
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name = "votos")
	private List<ProductoStock> listaVotos;

	/**
	 * 
	 * @return codigo perteneciente al cliente
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param codigo, recibe como parametro el codigo del cliente
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre, recibe el nombre del cliente
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return apellidos del cliente
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * 
	 * @param apellidos, recibe los apellidos del cliente
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/*public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}*/

	/**
	 * 
	 * @return una lista de aquellos productos que se tiene en el carrito
	 */
	public List<CarritoDetalle> getCarrito() {
		return carrito;
	}

	/**
	 * 
	 * @param carrito, recibe una lista del carrito de compras
	 */
	public void setCarrito(List<CarritoDetalle> carrito) {
		this.carrito = carrito;
	}

	/**
	 * 
	 * @return correo, el correo perteneciente al cliente
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * 
	 * @param correo, recibe el correo del cliente
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * 
	 * @return contrasenia perteneciente al cliente
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * 
	 * @param contrasenia, recibe la contrasenia del cliente
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * 
	 * @return una lista de tipo ProductoStock
	 */
	public List<ProductoStock> getListaVotos() {
		return listaVotos;
	}

	/**
	 * 
	 * @param listaVotos, recibe una lista de tipo ProductoStock
	 */
	public void setListaVotos(List<ProductoStock> listaVotos) {
		this.listaVotos = listaVotos;
	}

	/**
	 * 
	 * @return lista de compras, perteneciente o de tipo Compra
	 */
	public List<Compra> getListaCompras() {
		return listaCompras;
	}

	/**
	 * 
	 * @param listaCompras, recibe una lista de tipo Compra
	 */
	public void setListaCompras(List<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}

	/**
	 * 
	 * @return listaEnviado, una lista de tipo Compartido
	 */
	public List<Compartido> getListaEnviado() {
		return listaEnviado;
	}

	/**
	 * 
	 * @param listaEnviado, recibe una lista de tipo Compartido <br>
	 * que hace referencia a los producto enviados a un usuario
	 */
	public void setListaEnviado(List<Compartido> listaEnviado) {
		this.listaEnviado = listaEnviado;
	}

	/**
	 * 
	 * @return listaRecibido, una lista de tipo Compartido
	 */
	public List<Compartido> getListaRecibido() {
		return listaRecibido;
	}

	/**
	 * 
	 * @param listaRecibido, recibe una lista de tipo Compartido <br>
	 * que hace referencia a los producto recibidos por un usuario
	 */
	public void setListaRecibido(List<Compartido> listaRecibido) {
		this.listaRecibido = listaRecibido;
	}

	/**
	 * 
	 * @return telefono, perteneciente al cliente
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * 
	 * @param telefono, recibe un telefono perteneciente al cliente
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/*public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}*/

	/**
	 * Metodo que permite que al momento de imprimir <br>
	 * el objeto por consola su contenido sea legible.
	 */
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", correo=" + correo + ", contrasenia=" + contrasenia + "]";
	}
}