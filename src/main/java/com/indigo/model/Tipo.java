package com.indigo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tipos")
public class Tipo {
	
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String nombre; // Boceto, Logo, Imagen, Fotografia, etc
	private Integer alto;
	private Integer ancho;
	private String tipografia;
	//@OneToMany(mappedBy="tipo", cascade = CascadeType.ALL, orphanRemoval=true)
	//private Set<Pedido> pedidos = new HashSet<>(); // Revisar la relaciones by direccionales. Volar esta relación.
	
	public Tipo() {}
	
	public Tipo(long id, String nombre, Integer alto, Integer ancho, String tipografia) {
		this.id = id;
		this.nombre = nombre;
		this.alto = alto;
		this.ancho = ancho;
		this.tipografia = tipografia;
	}
	
	
	/*
	public Set<Pedido> getPedidos() {
		return this.pedidos;
	}
	
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	public void addPedido(Pedido pedido) {
		this.pedidos.add(pedido);
		pedido.setTipo(this);
	}
	
	public void removePedido(Pedido pedido) {
		this.pedidos.remove(pedido);
		pedido.setTipo(null);
	}
	*/
	
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
/*
	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	*/

	 
}
