package com.example.seed;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.repository.PedidoDBRepository;
import com.example.repository.UsuarioDBRepository;

@Component
public class InitDataLoader {
	
	@Autowired
	private PedidoDBRepository pedidoRepo;
	@Autowired
	private UsuarioDBRepository fileRepo;
	
	@PostConstruct
	public void loadSeed() {
		System.out.println("Saved Pedido en Repo?: ");
		Pedido pedido1 = new Pedido("Indigo Gráfica", "Esto es un nombre extendido", "Sans serif", 60, 60, "Esto es una descripcion", 10, "algo");
		//Usuario usuario1 = new Usuario("Av, Millazo", "Matias Maranguello", "1556203506");
		
		FileDB file1 = new FileDB();
		FileDB file2 = new FileDB();
		
		pedido1.addFile(file1);
		pedido1.addFile(file2);
		
		//usuarioRepo.save(usuario1);
		//pedido1.setUsuario(usuario1);
		pedidoRepo.save(pedido1);
		
	}
	
	@PreDestroy
	public void removeData() {
		System.out.print("Me ejecute");
		pedidoRepo.deleteAll();
		//usuarioRepo.deleteAll();
	}
	
}
