package com.example.dto;

public class UsuarioDTO {
	private String nombre;
	private String direccion;
	private String contacto;
	
	
	public UsuarioDTO() {}

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getContacto() {
		return contacto;
	}



	public void setContacto(String contacto) {
		this.contacto = contacto;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
