package com.example.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.exception.PedidoIncorrectoException;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private Integer cantidad;
	private String nombre;
	private String nombreExtendido;
	private String tipografia;
	private Integer alto;
	private Integer ancho;
	private String descripcion;
	private String state; // Por ahora el estado es un string
	private String propietario; // El propietario que pidio el encargo del pedido 
	private String encargado; // El editor encargado de resolver el pedido
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true) 
	private Set<FileDB> files = new HashSet<>();
	@ManyToOne //(fetch = FetchType.LAZY) //(fetch = FetchType.LAZY) // sacar Lazy
	@JoinColumn(name="tipo_id", nullable=false)
	private Tipo tipo;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	name = "colour_like", 
	joinColumns = @JoinColumn(name = "pedido_id"), // Que si sea Lazy
	inverseJoinColumns = @JoinColumn(name = "color_id"))
	private Set<Color> colores = new HashSet<>();
	
	public Pedido() {}
	
	public Pedido(String nombre, String nombreExtendido, String tipografia, Integer alto, Integer ancho, String descripcion, Integer cantidad, String state, Tipo tipo) {
		  this.setNombre(nombre);
		  this.setNombreExtendido(nombreExtendido);
		  this.setTipografia(tipografia);
		  this.setAlto(alto);
		  this.setAncho(ancho);
		  this.setDescripcion(descripcion);
		  this.setCantidad(cantidad);
		  this.setState(state);
		  this.setTipo(tipo);
	}
	
	
	public Set<FileDB> getFiles() {
		return this.files;
	}
	
	public void setFiles(Set<FileDB> files) {
		this.files = files;
	}
	
	/*
	public void addColor(Color color) {
		colores.add(color);
		color.getPedidos().add(this);
	}
	
	public void removeColor(Color color) {
		this.colores.remove(color);
		color.getPedidos().remove(this);
	}
	
	public void addFile(FileDB file1) {
		files.add(file1);
		file1.setPedido(this);	
	}
	public void removeFile(FileDB file) {
		this.files.remove(file);
		file.setPedido(null);
	}
	
	*/
	public Set<Color> getColores() {
		return this.colores;
	}
	
	public String getId() {
	    return id;
	}
	public void setId(String id) {
	    this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombreExtendido() {
		return nombreExtendido;
	}
	public void setNombreExtendido(String nombreExtendido) {
		this.nombreExtendido = nombreExtendido;
	}
	public String getTipografia() {
		return tipografia;
	}
	public void setTipografia(String tipografia) {
		this.tipografia = tipografia;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	

	public void setColores(Set<Color> colores) {
		this.colores = colores;
	}
	
	
	public String getPropietario() {
		return this.propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getEncargado() {
		return encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}

	public void validar() throws PedidoIncorrectoException {
		if(!(getAlto() > 0) || !(getAncho() > 0)){
			throw new PedidoIncorrectoException(PedidoIncorrectoException.ALTO_O_ANCHO_INVALIDOS);
		}
	}
	/*
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	*/


}
