package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Clase Usuario, que hace referencia al Usuario Administrador
 * el cual administrara la parte Web del proyecto
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Entity
public class Usuario {

	/**
	 * Atributos de la clase
	 */
	@Id
	@NotEmpty
	@Column(length = 10)
	private String cedula;
	
	@NotNull
	@Size(min=4, max=20)
	private String nombre;
	
	@NotNull
	@Size(min=4, max=20)
	private String apellidos;
	
	@Email
	private String email;
		
	@NotNull
	private String pass;

	/**
	 * 
	 * @return cedula del Usuario del sistema
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * 
	 * @param cedula perteneciente al Usuario
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * 
	 * @return nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre, perteneciente al usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return apellidos del usuario
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * 
	 * @param apellidos, perteneciente al Usuario
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * 
	 * @return email del usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email, perteneciente al usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return contrasenia del Usuario
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * 
	 * @param pass, que hace referencia a la contrasenia del usario
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Metodo que permite que al momento de imprimir el objeto por consola su <br>
	 * contenido sea legible.
	 */
	@Override
	public String toString() {
		return "Usuario [cedula=" + cedula + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", pass=" + pass + "]";
	}
}