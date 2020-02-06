package modelo_servicio;

//import java.util.Date;

public class ClienteInfo {

	private int codigo;
	
	private String nombre;

	//private String imagen;

	private String apellidos;

	//private String fechaNacimiento;

	private String telefono;
	
	private String correo;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}*/

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/*public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}*/

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "ClienteInfo [codigo=" + codigo + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", telefono=" + telefono + ", correo=" + correo + "]";
	}
}