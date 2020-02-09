package modelo_servicio;

/**
 * En esta clase tenemos todos aquellos atributos y elementos que son <br>
 * necesarios, esta clase no tiene relacion con otras tablas o clases.
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

public class ProductoInfo {

	/**
	 * Atributos de la clase
	 */
	private int codigo;

	private String nombre;

	private String descripcion;

	private String imagenes;

	private double precio;

	private int votos;

	private int cantidad;

	private String categoria;

	private boolean isLike;
	
	private String nombreCompartido;
	
	private String correoCompartido;

	public String getNombreCompartido() {
		return nombreCompartido;
	}

	public void setNombreCompartido(String nombreCompartido) {
		this.nombreCompartido = nombreCompartido;
	}

	public String getCorreoCompartido() {
		return correoCompartido;
	}

	public void setCorreoCompartido(String correoCompartido) {
		this.correoCompartido = correoCompartido;
	}

	/**
	 * 
	 * @return isLike, para saber si un producto tiene un like o no
	 */
	public boolean isLike() {
		return isLike;
	}

	/**
	 * 
	 * @param isLike, permite saber si un producto tiene o no un me gusta
	 */
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	/**
	 * 
	 * @return codigo de la clase
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * 
	 * @param codigo, perteneciente a la clase
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * 
	 * @return nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * @param nombre, recibe el nombre del producto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return la descripcion del producto
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * @param descripcion, recibe la descripcion del producto
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @return la imagen del producto
	 */
	public String getImagenes() {
		return imagenes;
	}

	/**
	 * 
	 * @param imagen, recibe la imagen del producto
	 */
	public void setImagenes(String imagenes) {
		this.imagenes = imagenes;
	}

	/**
	 * 
	 * @return precio que tiene el producto a se vendido
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * 
	 * @param precio, recibe el precio del producto
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * 
	 * @return votos, permite saber cuantos votos tiene un producto
	 */
	public int getVotos() {
		return votos;
	}

	/**
	 * 
	 * @param votos de tipo int 
	 */
	public void setVotos(int votos) {
		this.votos = votos;
	}

	/**
	 * 
	 * @return cantidad de productos
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * 
	 * @param cantidad de tipo int 
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * 
	 * @return categoria a la que pertenece un producto
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * 
	 * @param categoria de tipo String
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Metodo que permite que al momento de imprimir el objeto por consola su
	 * contenido sea legible.
	 */
	@Override
	public String toString() {
		return "ProductoInfo [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagenes="
				+ imagenes + ", precio=" + precio + ", votos=" + votos + ", cantidad=" + cantidad + ", categoria="
				+ categoria + "]";
	}
}