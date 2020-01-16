package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class CarritoDetalle {
	
	@Id
	@GeneratedValue
	private int id; 
	
	private int cantidad;
	
	@ManyToOne
	private ProductoStock producto;
	
}
