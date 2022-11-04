package com.example.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "files")
public class FileDB {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String type;
	@Lob
	private byte[] data;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true) 
	private List<Comentario> comentarios = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true) 
	private List<Interaccion> interacciones = new ArrayList<>();
	
	public FileDB() {}
	
	public FileDB(String name, String type, byte[] data) {
	  this.name = name;
	  this.type = type;
	  this.data = data;
	}
	
	
	public List<Comentario> getComentarios() {
		return this.comentarios;
	}
	
	 @Override
	 public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof FileDB)) return false;
		return id != null && id.equals(((FileDB) o).getId());
	 }
	 
	 @Override
	 public int hashCode() {
		 return getClass().hashCode();
	 }
	
	  public Long getId() {
	    return id;
	  }
	  
	  public void setId(Long id) {
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
	  public byte[] getData() {
	    return data;
	  }
	  public void setData(byte[] data) {
	    this.data = data;
	  }
	  
	  public List<Interaccion> getInteracciones() {
		return this.interacciones;
	  }
		
	  public void setInteracciones(List<Interaccion> interacciones) {
		this.interacciones = interacciones;
	  }
	  /*
	  public byte[] getUrl() {
		  return url;
	  }
	  public void setUrl(byte[] url) {
		  this.url = url;
	  }
	  */

}
