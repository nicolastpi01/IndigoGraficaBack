package com.example.seed;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Requerimiento;
import com.example.model.Tipo;
import com.example.repository.PedidoDBRepository;
import com.example.repository.TipoDBRepository;

@Component
public class InitDataLoader {
	
	@Autowired
	private PedidoDBRepository pedidoRepo;
	@Autowired
	private TipoDBRepository tipoRepo;
	
	@PostConstruct
	public void loadSeed() {
		//System.out.println("Saved Pedido en Repo?: ");
		
		Tipo tipo = new Tipo("Logo", 60, 60, "sans serif");
		
		Pedido pedido1 = new Pedido("Indigo Gráfica", "Esto es un nombre extendido", "Sans serif", 60, 60, "Esto es una descripcion", 10, "algo");
		//Usuario usuario1 = new Usuario("Av, Millazo", "Matias Maranguello", "1556203506");
		
		FileDB file1 = new FileDB();
		FileDB file2 = new FileDB();
		
		Requerimiento requerimiento1 = new Requerimiento("Esto es un requerimiento terminado", true);
		Requerimiento requerimiento2 = new Requerimiento("Esto es un requerimiento inconcluso", false);
		
		file1.addRequerimiento(requerimiento1);
		file1.addRequerimiento(requerimiento2);
		pedido1.addFile(file1);
		pedido1.addFile(file2);
		
		//usuarioRepo.save(usuario1);
		//pedidoRepo.save(pedido1);
		tipo.addPedido(pedido1);
		
		tipoRepo.save(tipo);
	}
	
	@PreDestroy
	public void removeData() {
		System.out.print("Me ejecute");
		pedidoRepo.deleteAll();
		//usuarioRepo.deleteAll();
	}
	
}
