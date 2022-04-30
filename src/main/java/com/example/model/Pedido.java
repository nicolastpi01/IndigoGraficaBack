package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@ElementCollection
	@CollectionTable(name = "colores", joinColumns = @JoinColumn(name = "id")) 
    @Column(name = "colores")
	private List<String> colores;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
	private Usuario usuario;
	//tipo: Tipo; 
	
	//@Lob
	//private byte[] data;
	//boceto?: Blob;
	/*¨
	 * 
     //
    estado: Estado;
    editor?: Usuario
	 */
	
	//@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "usuarios_id", referencedColumnName = "id")
	public Pedido() {}
	
	public Pedido(String nombre, String nombreExtendido, String tipografia, Integer alto, Integer ancho, String descripcion, Integer cantidad, Usuario usuario, List<String> colores) {
	  this.setNombre(nombre);
	  this.setNombreExtendido(nombreExtendido);
	  this.setTipografia(tipografia);
	  this.setAlto(alto);
	  this.setAncho(ancho);
	  this.setDescripcion(descripcion);
	  this.setCantidad(cantidad);
	  this.setUsuario(usuario);
	  this.setColores(colores);
	}
	
	public String getId() {
	    return id;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuario() {
		return this.usuario;
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

	public List<String> getColores() {
		return colores;
	}

	public void setColores(List<String> colores) {
		this.colores = colores;
	}
	

}
