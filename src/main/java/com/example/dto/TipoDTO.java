package com.example.dto;



import java.util.HashSet;
import java.util.Set;

import com.example.model.Pedido;
import com.example.model.Tipo;

public class TipoDTO {
	
	private long id;
	private String nombre; // Boceto, Logo, Imagen, Fotografia, etc
	private Integer alto;
	private Integer ancho;
	private String tipografia;
	private Set<Pedido> pedidos;
	
	public TipoDTO(Tipo tipo) {
		this.id = tipo.getId();
		this.nombre = tipo.getNombre();
		this.alto = tipo.getAlto();
		this.ancho = tipo.getAncho();
		this.tipografia = tipo.getTipografia();
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
	public Integer getAlto() {
		return alto;
	}
	public void setAlto(Integer alto) {
		this.alto = alto;
	}
	public Integer getAncho() {
		return ancho;
	}
	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}
	public String getTipografia() {
		return tipografia;
	}
	public void setTipografia(String tipografia) {
		this.tipografia = tipografia;
	}
	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
