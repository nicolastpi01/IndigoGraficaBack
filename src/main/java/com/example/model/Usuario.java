package com.example.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;
	private String direccion;
	private String nombre;
	private String contacto;
	//@OneToOne(mappedBy = "usuario")
	//private Pedido pedido;
    
	public Usuario() {}
	
	public Usuario(String direccion, String nombre, String contacto) {
		this.setDireccion(direccion);
		this.setNombre(nombre);
		this.setContacto(contacto);
		//this.setPedido(pedido);
	}
	
	
	

	public String getId() {
		return this.id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

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

	

}
