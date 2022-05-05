package com.example.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	private String state;
	@OneToMany(mappedBy="pedido", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<FileDB> files = new HashSet<>();
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_id", nullable=false)
	private Tipo tipo;
	//@ElementCollection
	//@CollectionTable(name = "colores", joinColumns = @JoinColumn(name = "id")) 
    //@Column(name = "colores")
	//private List<String> colores;
	/*
	 * 	private Usuario usuario;
   		private editor?: Usuario
	 */
	// No agregar usuarios, o agregar un string usuarioId
	// Agregar colores
	
	public Pedido() {}
	
	public Pedido(String nombre, String nombreExtendido, String tipografia, Integer alto, Integer ancho, String descripcion, Integer cantidad, String state) {
	  this.setNombre(nombre);
	  this.setNombreExtendido(nombreExtendido);
	  this.setTipografia(tipografia);
	  this.setAlto(alto);
	  this.setAncho(ancho);
	  this.setDescripcion(descripcion);
	  this.setCantidad(cantidad);
	  this.setState(state);
	}
	
	public void addFile(FileDB file1) {
		files.add(file1);
		file1.setPedido(this);	
	}
	public void removeFile(FileDB file) {
		this.files.remove(file);
		file.setPedido(null);
	}
	
	public String getId() {
	    return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Number getCantidad() {
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
	public Number getAlto() {
		return alto;
	}
	public void setAlto(Integer alto) {
		this.alto = alto;
	}
	public Number getAncho() {
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


}
