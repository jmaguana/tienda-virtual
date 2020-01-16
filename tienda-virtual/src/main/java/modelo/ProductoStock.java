package modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

//@DiscriminatorValue(value="stock")
@Entity
public class ProductoStock extends Producto{
	
	@NotNull
	private int stock;
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name = "votos")
	private List<Cliente> votos;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	@NotNull
	private Categoria categoria;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_vendido")
	private List<ProductoVendido> ventas;
	
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

	public List<ProductoVendido> getVentas() {
		return ventas;
	}

	public void setVentas(List<ProductoVendido> ventas) {
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "ProductoStock [stock=" + stock + ", votos=" + votos + ", categoria=" + categoria + "]";
	}
	
	
}