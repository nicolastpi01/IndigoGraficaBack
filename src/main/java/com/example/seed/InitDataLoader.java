package com.example.seed;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.model.Color;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Requerimiento;
import com.example.model.Tipo;
import com.example.repository.ColorDBRepository;
import com.example.repository.PedidoDBRepository;
import com.example.repository.TipoDBRepository;

@Component
public class InitDataLoader {
	@Autowired
	private PedidoDBRepository pedidoRepo;
	@Autowired
	private TipoDBRepository tipoRepo;
	@Autowired
	private ColorDBRepository colorRepo;
	
	@PostConstruct
	public void loadSeed() {
		Tipo tipo1 = new Tipo(1,"Logo", 60, 60, "sans serif");
		Tipo tipo2 = new Tipo(2,"Folleto", 60, 60, "sans serif");
		Tipo tipo3 = new Tipo(3,"Carta", 60, 60, "sans serif");
		Tipo tipo4 = new Tipo(4,"Fotografia", 60, 60, "sans serif");
		
		tipoRepo.save(tipo1);
		tipoRepo.save(tipo2);
		tipoRepo.save(tipo3);
		tipoRepo.save(tipo4);
		
		Color color1 = new Color(10, "Rojo", "#FF0000");
		Color color2 = new Color(9, "Azul", "#0000FF");
		
		colorRepo.save(color1);
		colorRepo.save(color2);
	 /*
		Color Azul = new Color("Rojo", "#FF0000");
		Color Rojo = new Color("Azul", "#0000FF");
		
		
		
		Pedido pedido1 = new Pedido("Indigo Gráfica", "Esto es un nombre extendido", "Sans serif", 60, 60, "Esto es una descripcion", 10, "algo", "Nicolas owner");
		//Usuario usuario1 = new Usuario("Av, Millazo", "Matias Maranguello", "1556203506");
		
		FileDB file1 = new FileDB();
		FileDB file2 = new FileDB();
		
		Requerimiento requerimiento1 = new Requerimiento("Esto es un requerimiento terminado", true);
		Requerimiento requerimiento2 = new Requerimiento("Esto es un requerimiento inconcluso", false);
		
		file1.addRequerimiento(requerimiento1);
		file1.addRequerimiento(requerimiento2);
		pedido1.addFile(file1);
		pedido1.addFile(file2);
		
		colorRepo.save(Rojo);
		colorRepo.save(Azul);
		
		pedido1.addColor(Rojo);
		pedido1.addColor(Azul);
		
		//usuarioRepo.save(usuario1);
		
		tipo.addPedido(pedido1);
		
		//pedidoRepo.save(pedido1);
		tipoRepo.save(tipo);
	*/
		 
	}
	
	@PreDestroy
	public void removeData() {
		System.out.print("Me ejecute");
		pedidoRepo.deleteAll();
		//usuarioRepo.deleteAll();
	}
	
}
