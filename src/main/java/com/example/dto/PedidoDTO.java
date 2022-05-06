package com.example.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.example.model.Color;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Tipo;
import com.example.utils.Helper;

public class PedidoDTO {
	
	
	private String id;
	private Integer cantidad;
	private String nombre;
	private String nombreExtendido;
	private String tipografia;
	private Integer alto;
	private Integer ancho;
	private String descripcion;
	private String state;
	private String propietario;
	private String encargado;
	private Set<FileDB> files = new HashSet<>();
	private Tipo tipo;
	private Set<Color> colores;
	private Helper helper = new Helper();
	
	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.nombre = pedido.getNombre();
		this.nombreExtendido = pedido.getNombreExtendido();
		this.tipografia = pedido.getTipografia();
		this.alto = (Integer) pedido.getAlto();
		this.ancho = (Integer) pedido.getAncho();
		this.descripcion = pedido.getDescripcion();
		this.state = pedido.getState();
		this.propietario = pedido.getPropietario();
		this.encargado = pedido.getEncargado();
		this.files = pedido.getFiles().stream().map(file -> helper.fileFromDTO(new FileDTO(file))).collect(Collectors.toSet());
		this.tipo = helper.tipoFromDTO(new TipoDTO(pedido.getTipo()));
		this.colores = pedido.getColores().stream().map(color -> helper.colorFromDTO(new ColorDTO(color))).collect(Collectors.toSet());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public Set<FileDB> getFiles() {
		return files;
	}
	public void setFiles(Set<FileDB> files) {
		this.files = files;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Set<Color> getColores() {
		return colores;
	}

	public void setColores(Set<Color> colores) {
		this.colores = colores;
	}

	public String getPropietario() {
		return propietario;
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

}
