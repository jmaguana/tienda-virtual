package modelo_servicio;

public class ProductoInfo {

	private int codigo;
	
	private String nombre;

	private String descripcion;

	private String imagenes;

	private double precio;
	
	private int votos;

	public String getNombre() {
		return nombre;
	}
	
	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagenes() {
		return imagenes;
	}

	public void setImagenes(String imagenes) {
		this.imagenes = imagenes;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	@Override
	public String toString() {
		return "Producto_Info [nombre=" + nombre + ", descripcion=" + descripcion + ", imagenes=" + imagenes
				+ ", precio=" + precio + "]";
	}
}
