package com.example.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "comentarios")
public class Comentario {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	//@OneToOne 
	//@JoinColumn(name="posicion_id", nullable=false)
	//private Posicion pos;
	private Integer x;
	private Integer y;
	private Integer numero;
	private Boolean terminado;
	private Boolean isVisible;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true) 
	private List<Interaccion> interacciones = new ArrayList<>();
	
	public Comentario() {}
	
	
	public String getId() {
		return this.id;
	}
	
	public List<Interaccion> getInteracciones() {
		return this.interacciones;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getTerminado() {
		return terminado;
	}

	public void setTerminado(Boolean terminado) {
		this.terminado = terminado;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}


	public Integer getX() {
		return x;
	}


	public void setX(Integer x) {
		this.x = x;
	}


	public Integer getY() {
		return y;
	}


	public void setY(Integer y) {
		this.y = y;
	}
	

}


