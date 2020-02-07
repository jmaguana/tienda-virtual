package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * En esta clase tenemos todos aquellos atributos <br>
 * y elementos que son necesarios en esta clase
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
//@DiscriminatorValue(value="stock")
@Entity
public class ProductoStock extends Producto {

	/**
	 * Atributos de la clase
	 */
	@NotNull
	private int stock;

	private int vendido;

	/**
	 * Atributo de la clase Cliente, el cual permite <br>
	 * la relacion de muchos a muchos con la clase Categoria
	 */
	@ManyToMany(mappedBy = "listaVotos")
	private List<Cliente> votos;

	/**
	 * Atributo de la clase Categoria, el cual permite <br>
	 * la relacion de muchos a uno con la clase Categoria
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	@NotNull
	private Categoria categoria;

	/**
	 * 
	 * @return stock que tiene el producto
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * 
	 * @param stock, valor de stock con el que cuenta este Producto
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * 
	 * @return categoria, de tipo Categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * 
	 * @param categoria, que viene de la clase Categoria
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * 
	 * @return votos de tipo cliente, ya que cada voto va <br>
	 * asociado a un cliente
	 */
	public List<Cliente> getVotos() {
		return votos;
	}

	/**
	 * 
	 * @param votos, viene de la clase cliente y es una lista
	 */
	public void setVotos(List<Cliente> votos) {
		this.votos = votos;
	}

	/**
	 * 
	 * @return vendido, cantidad de productos vendidos
	 */
	public int getVendido() {
		return vendido;
	}
	
	/**
	 * 
	 * @param vendido, de tipo int y hace referencia al numero <br>
	 * de productos que se han vendido
	 */
	public void setVendido(int vendido) {
		this.vendido = vendido;
	}
}