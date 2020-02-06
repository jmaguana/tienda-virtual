package modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

//@DiscriminatorValue(value="stock")
@Entity
public class ProductoStock extends Producto{
	
	@NotNull
	private int stock;
	
	@ManyToMany(mappedBy = "listaVotos")
	private List<Cliente> votos;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	@NotNull
	private Categoria categoria;
	
	private int vendido;
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	

	public List<Cliente> getVotos() {
		return votos;
	}

	public void setVotos(List<Cliente> votos) {
		this.votos = votos;
	}

	public int getVendido() {
		return vendido;
	}

	public void setVendido(int vendido) {
		this.vendido = vendido;
	}

	
}
