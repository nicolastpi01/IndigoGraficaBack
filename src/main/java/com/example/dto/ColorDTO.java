package com.example.dto;


import java.util.Set;
import com.example.model.Color;
import com.example.model.Pedido;

public class ColorDTO {
	
	private long id;
	private String nombre;
	private String hexCode;
	private Set<Pedido> pedidos;
	
	public ColorDTO(Color color) {
		this.id = color.getId();
		this.nombre = color.getNombre();
		this.hexCode = color.getHexCode();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHexCode() {
		return hexCode;
	}
	public void setHexCode(String hexCode) {
		this.hexCode = hexCode;
	}
	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
