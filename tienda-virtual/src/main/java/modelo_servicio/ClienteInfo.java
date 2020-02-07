package modelo_servicio;

//import java.util.Date;

/**
 * En esta clase tenemos todos aquellos atributos y elementos que son <br>
 * necesarios para un cliente, que no tiene relacion con otras tablas o clases.
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

public class ClienteInfo {

	/**
	 * Atributos de la clase
	 */
	private int codigo;
	
	private String nombre;

	//private String imagen;

	private String apellidos;

	//private String fechaNacimiento;

	private String telefono;
	
	private String correo;
	
	/**
	 * 
	 * @return codigo perteneciente al cliente
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * 
	 * @param codigo, recibe como parametro el codigo del cliente
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	/*public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}*/

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

	/*public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}*/

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
	
	/**
	 * Metodo que permite que al momento de imprimir <br>
	 * el objeto por consola su contenido sea legible.
	 */
	@Override
	public String toString() {
		return "ClienteInfo [codigo=" + codigo + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", telefono=" + telefono + ", correo=" + correo + "]";
	}
}