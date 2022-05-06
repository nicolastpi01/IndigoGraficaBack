package com.example.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String nombre;
	private String hexCode;
	
	//@ManyToMany(mappedBy = "colores")
	//private Set<Pedido> pedidos = new HashSet<>();
	
	
	public Color() {}
	
	public Color(String nombre, String hexCode) {
		this.nombre = nombre;
		this.hexCode = hexCode;
	}
	/*
	public Set<Pedido> getPedidos() {
		return this.pedidos;
	}
	*/
	
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

	/*
	public void addPedido(Pedido pedido) {
		this.pedidos.add(pedido);
	}
	*/

}