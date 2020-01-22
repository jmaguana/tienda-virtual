package modelo;

import javax.persistence.Entity;

@Entity
public class ProductoVendido extends Producto{
	
	private int cantidad;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
