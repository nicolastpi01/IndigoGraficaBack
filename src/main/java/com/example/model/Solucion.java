package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "soluciones")
public class Solucion {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@ManyToOne 
	@JoinColumn(name="file_id", nullable=false)
	private FileDB file;
	private String idFileToSolution;
	private Boolean approved;
	private boolean visible = true;
	
	public Solucion() {}
	
	public Solucion(FileDB file, String idFileToSolution) {
		this.setFile(file);
		this.setIdFileToSolution(idFileToSolution);
	}
	
	public String getId() {
	    return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public FileDB getFile() {
		return file;
	}

	public void setFile(FileDB file) {
		this.file = file;
	}

	public String getIdFileToSolution() {
		return idFileToSolution;
	}

	public void setIdFileToSolution(String idFileToSolution) {
		this.idFileToSolution = idFileToSolution;
	}

	public Boolean isApproved() {
		Boolean check = this.getApproved();
		if(check != null) return check.booleanValue() == true;
		else return false;
		
	}
	
	public Boolean getApproved() {
		return this.approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public void setVisible(boolean visible) {
		this.visible  = visible;	
	}
	
	public boolean getVisible() {
		return this.visible;	
	}

	public boolean WithoutAgreement() {
		return this.getApproved() == null;
	}

}
