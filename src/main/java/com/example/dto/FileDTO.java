package com.example.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Requerimiento;
import com.example.utils.Helper;

public class FileDTO {
	
	private String id;
	private String name;
	private String type;
	private byte[] data;
    private Pedido pedido;
	private Set<Requerimiento> requerimientos = new HashSet<>();
	private Helper helper = new Helper();
	
	
	public FileDTO(FileDB file) {
		this.id = file.getId();
		this.name = file.getName();
		this.type = file.getType();
		this.data = file.getData();
		this.pedido = null;
		// pedido.getFiles().stream().map(file -> helper.fileFromDTO(new FileDTO(file))).collect(Collectors.toSet());
		System.out.println("Requerimientos?: " + file.getRequerimientos());
		this.requerimientos = file.getRequerimientos().stream().map(requerimiento -> helper.requerimientoFromDTO(new RequerimientoDTO(requerimiento))).collect(Collectors.toSet());
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Set<Requerimiento> getRequerimientos() {
		return requerimientos;
	}
	public void setRequerimientos(Set<Requerimiento> requerimientos) {
		this.requerimientos = requerimientos;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

}
