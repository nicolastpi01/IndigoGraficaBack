package com.example.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String type;
	@Lob
	private byte[] data;
	
	
	//@Lob
	// Persisto la url por comodidad
	//private byte[] url;
	//@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="pedido_id", nullable=false)
    //private Pedido pedido;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true) //(mappedBy="file",
	private List<Requerimiento> requerimientos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true) 
	private List<Comentario> comentarios = new ArrayList<>();
	
	public FileDB() {}
	
	public FileDB(String name, String type, byte[] data) {
	  this.name = name;
	  this.type = type;
	  this.data = data;
	}
	
	public List<Requerimiento> getRequerimientos() {
		return this.requerimientos;
	}
	
	public List<Comentario> getComentarios() {
		return this.comentarios;
	}
	/*
	public void addRequerimiento(Requerimiento requerimiento) {
		requerimientos.add(requerimiento);
		requerimiento.setFile(this);	
	}
	public void removeRequerimiento(Requerimiento requerimiento) {
		this.requerimientos.remove(requerimiento);
		requerimiento.setFile(null);
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
	/*
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Pedido getPedido() {
		return this.pedido;
	}
	*/
	
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
	  public byte[] getData() {
	    return data;
	  }
	  public void setData(byte[] data) {
	    this.data = data;
	  }
	  /*
	  public byte[] getUrl() {
		  return url;
	  }
	  public void setUrl(byte[] url) {
		  this.url = url;
	  }
	  */
	
	  public void setRequerimientos(List<Requerimiento> requerimientos) {
		this.requerimientos = requerimientos;
	  }

}
