package com.example.dto;


import com.example.model.Color;

public class ColorDTO {
	
	private long id;
	private String nombre;
	private String hexCode;
	
	public ColorDTO(Color color) {
		this.id = color.getId();
		this.nombre = color.getNombre();
		this.hexCode = color.getHexCode();
	}
	
	public ColorDTO() {}
	

	public Color toColor() {
		Color color = new Color(this.nombre, this.hexCode);
		return color;
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

	

}
