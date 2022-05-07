package com.example.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Requerimiento;

public class FileDTO {
	
	private String id;
	private String name;
	private String type;
	private byte[] data;
	private Set<RequerimientoDTO> requerimientos; //  = new HashSet<>()
	//private Helper helper = new Helper();
		
	public FileDTO(FileDB file) {
		this.id = file.getId();
		this.name = file.getName();
		this.type = file.getType();
		this.data = file.getData();
		this.requerimientos = file.getRequerimientos().stream().map(requerimiento -> (new RequerimientoDTO(requerimiento))).collect(Collectors.toSet());
	}
	
	public FileDTO() {}
	
	
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
	
	public Set<RequerimientoDTO> getRequerimientos() {
		return requerimientos;
	}
	public void setRequerimientos(Set<RequerimientoDTO> requerimientos) {
		this.requerimientos = requerimientos;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

}
