package modelo_servicio;

public class ProductoInfo {

	private int codigo;
	
	private String nombre;

	private String descripcion;

	private String imagenes;

	private double precio;
	
	private int votos;
	
	private int cantidad;
	
	private String categoria;

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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "ProductoInfo [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagenes="
				+ imagenes + ", precio=" + precio + ", votos=" + votos + ", cantidad=" + cantidad + ", categoria="
				+ categoria + "]";
	}
}