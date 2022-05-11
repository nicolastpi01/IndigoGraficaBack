package com.example.dto;


import com.example.model.Tipo;

public class TipoDTO {
	
	private long id;
	private String nombre; // Boceto, Logo, Imagen, Fotografia, etc
	private Integer alto;
	private Integer ancho;
	private String tipografia;
	
	public TipoDTO(Tipo tipo) {
		this.id = tipo.getId();
		this.nombre = tipo.getNombre();
		this.alto = tipo.getAlto();
		this.ancho = tipo.getAncho();
		this.tipografia = tipo.getTipografia();
	}
	
	public TipoDTO() {}
	
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

	public Tipo toTipo() {
		Tipo tipo = new Tipo(1, this.nombre, this.alto, this.ancho, this.tipografia);
		return tipo;
	}

}
