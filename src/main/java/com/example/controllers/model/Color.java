package com.example.controllers.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "colores")
public class Color {
	
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String nombre;
	private String hexCode;
	//@ManyToMany(mappedBy = "colores") // no se necesita ver los pedidos desde los colores. Evitar relaciones bidireccionales al menos que se necesite si o si
	//private Set<Pedido> pedidos = new HashSet<>();
	
	
	public Color() {}
	
	public Color(long id, String nombre, String hexCode) {
		this.id = id;
		this.nombre = nombre;
		this.hexCode = hexCode;
	}
	
	public Color(String nombre, String hexCode) {
		this.nombre = nombre;
		this.hexCode = hexCode;
	}
	
	//public Set<Pedido> getPedidos() {
	//	return this.pedidos;
	//}
	
	public String getHexCode() {
		return hexCode;
	}
	public void setHexCode(String hexCode) {
		this.hexCode = hexCode;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//public void addPedido(Pedido pedido) {
	//	this.pedidos.add(pedido);
	//}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
