package com.indigo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.indigo.exception.PedidoIncorrectoException;
import com.indigo.model.Estado.Estado;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer cantidad = 1;
	private String nombre;
	private String nombreExtendido;
	private String tipografia;
	private Integer alto;
	private Integer ancho;
	private String descripcion;
	private Date fechaEntrega;
	private Boolean hasPayment = false;
	private Boolean sendBudgetMail = false;
	private Boolean isEditing = false;
	@ManyToOne 
	@JoinColumn(name="estado_id", nullable=false)
	private Estado state;
	@ManyToOne 
	@JoinColumn(name="user_id", nullable=false)
	private User propietario; // El propietario que pidio el encargo del pedido
	@ManyToOne 
	@JoinColumn(name="encargado_id", nullable=true)
	private User encargado; // El editor encargado de resolver el pedido 
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<FileDB> files = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Presupuesto> presupuesto = new ArrayList<>();;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Solucion> solutions = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true) 
	private List<Interaccion> interacciones = new ArrayList<>();
	@ManyToOne //(fetch = FetchType.LAZY) //(fetch = FetchType.LAZY) // sacar Lazy
	@JoinColumn(name="tipo_id", nullable=false)
	private Tipo tipo;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	name = "colour_like", 
	joinColumns = @JoinColumn(name = "pedido_id"), // Que si sea Lazy
	inverseJoinColumns = @JoinColumn(name = "color_id"))
	private Set<Color> colores = new HashSet<>();
	
	public Pedido() {}
	
	public Pedido(String nombre, String nombreExtendido, String tipografia, Integer alto, Integer ancho, String descripcion, Integer cantidad, Estado state, Tipo tipo) {
		  this.setNombre(nombre);
		  this.setNombreExtendido(nombreExtendido);
		  this.setTipografia(tipografia);
		  this.setAlto(alto);
		  this.setAncho(ancho);
		  this.setDescripcion(descripcion);
		  this.setCantidad(cantidad);
		  this.setState(state);
		  this.setTipo(tipo);
		  this.setIsEditing(false);
		  //this.setAvalaible(true);
	};
	
	public List<FileDB> getFiles() {
		return this.files;
	}
	
	public void setFiles(List<FileDB> files) {
		this.files = files;
	}
	
	public List<Solucion> getSolutions() {
		return this.solutions;
	}
	
	public void setSolutions(List<Solucion> soluciones) {
		this.solutions = soluciones;
	}
	
	public List<Interaccion> getInteracciones() {
		return this.interacciones;
	}
	
	public void setInteracciones(List<Interaccion> interacciones) {
		this.interacciones = interacciones;
	}

	public Set<Color> getColores() {
		return this.colores;
	}
	
	public Long getId() {
	    return id;
	}
	public void setId(Long id) {
	    this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCantidad() {
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
	
	public Estado getState() {
		return state;
	}

	public void setState(Estado state) {
		this.state = state;
	}
	
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	

	public void setColores(Set<Color> colores) {
		this.colores = colores;
	}
	
	
	public User getPropietario() {
		return this.propietario;
	}

	public void setPropietario(User propietario) {
		this.propietario = propietario;
	}

	public User getEncargado() {
		return encargado;
	}

	public void setEncargado(User encargado) {
		this.encargado = encargado;
	}

	public void validar() throws PedidoIncorrectoException {
		if(!(getAlto() > 0) || !(getAncho() > 0)){
			throw new PedidoIncorrectoException(PedidoIncorrectoException.ALTO_O_ANCHO_INVALIDOS);
		}
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	// Revisar los nombres
	public boolean haveSolution() {
		return !this.getSolutions().isEmpty();
	}

	public boolean allFilesHaveSolution() {
		// ret almacena si AllFiles tienen Solution
		//ret = true;
		//this.getFiles().stream().map(file -) .haveSolution(this)
		boolean ret = this.getFiles().stream().allMatch(x -> x.haveSolution(this));
		return ret;
		/* this.getFiles().stream().map(x -> {
		ret = ret && x.haveSolution(this);	
		 return x;
		});
		return ret; */
	}

	public boolean haveFiles() {
		return !this.getFiles().isEmpty();
	}

	public Boolean getHasPayment() {
		return hasPayment;
	}

	public void setHasPayment(Boolean hasPayment) {
		this.hasPayment = hasPayment;
	}

	public boolean hasPayment() {
		return this.getHasPayment();
	}

	public boolean allSolutionsWasApproved() {
		boolean allApproved = this.getSolutions().stream().allMatch(x -> x.isApproved());
		return allApproved;
	}

	public boolean existsSolutionsWithoutAgreement() {
		boolean anyNotApproved = this.getSolutions().stream().anyMatch(x -> x.WithoutAgreement());
		return anyNotApproved;
	};

	/*
	public boolean isAvalaible() {
		return avalaible;
	}
	*/
	
	public List<Presupuesto> getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(List<Presupuesto> presupuesto) {
		this.presupuesto = presupuesto;
	}

	public boolean hasBudget() {
		// Presupuestos? no es vacio entonces tiena al menos un presupuesto!
		return !this.getPresupuesto().isEmpty();
	}

	public Boolean getIsEditing() {
		return isEditing;
	}

	public void setIsEditing(Boolean isEditing) {
		this.isEditing = isEditing;
	}

	public boolean allFilesHaveBeenReplacement() {
		boolean allHasReplacement = this.getSolutions().stream().allMatch(x -> x.isHasReplacement() || x.isApproved() );
		return allHasReplacement;
	}

	public Boolean getSendBudgetMail() {
		return sendBudgetMail;
	}

	public void setSendBudgetMail(Boolean sendBudgetMail) {
		this.sendBudgetMail = sendBudgetMail;
	}
}
