package com.example.jsonObjects;


import java.util.Set;
import com.example.model.Color;
import com.example.model.FileDB;
import com.example.model.Tipo;

public class PedidoJson {
	
	private String nombre;
	private String nombreExtendido;
	private Integer cantidad;
	private String tipografia;
	private Integer alto;
	private Integer ancho;
	private String descripcion;
	//private String state; // No viene estado por ser el primer alta
	private String propietario;  
	//private String encargado; // En principio no viene 
	private Set<FileDB> files;
	private Tipo tipo;
	private Set<Color> colores;

}
