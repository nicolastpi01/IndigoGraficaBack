package com.example.dto;

import com.example.model.FileDB;
import com.example.model.Requerimiento;

public class RequerimientoDTO {
	
	private String id;
	private String descripcion;
	private Boolean chequeado;
	private FileDB file = null;
	
	public RequerimientoDTO(Requerimiento req) {
		this.id = req.getId();
		this.descripcion = req.getDescripcion();
		this.chequeado = req.getChequeado();
	}
	
	public FileDB getFile() {
		return file;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getChequeado() {
		return chequeado;
	}
	public void setChequeado(Boolean chequeado) {
		this.chequeado = chequeado;
	}
}
