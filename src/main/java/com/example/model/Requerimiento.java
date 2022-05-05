package com.example.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "requerimientos")
public class Requerimiento {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String descripcion;
	private Boolean chequeado;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id", nullable=false)
	private FileDB file;
	
	public Requerimiento() {}
	
	public Requerimiento(String descripcion, Boolean check) {
		this.descripcion = descripcion;
		this.chequeado = check;
	}
	
	 @Override
	 public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Requerimiento)) return false;
		return id != null && id.equals(((Requerimiento) o).getId());
	 }
	 
	 @Override
	 public int hashCode() {
		 return getClass().hashCode();
	 }
	
	public String getId() {
	    return id;
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


	public void setChequeado(Boolean check) {
		this.chequeado = check;
	}

	public FileDB getFile() {
		return file;
	}

	public void setFile(FileDB file) {
		this.file = file;
	};
	
	

}