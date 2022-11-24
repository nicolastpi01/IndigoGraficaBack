package com.example.seed;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.example.model.*;
import com.example.model.Estado.Estado;
import com.example.model.Estado.PendienteAtencion;
import com.example.model.Estado.Reservado;
import com.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.repository.ColorDBRepository;
import com.example.repository.EstadoDBRepository;
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
	
	@Autowired
	private EstadoDBRepository estadoRepo;

	@Autowired
	private RoleRepository roleRepository;
	
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
		
		Estado estado1 = new Estado(1, "pendAtencion", "Pendiente de atención", "#2db7f5");
		Estado estado2 = new Estado(2, "reservado", "Reservado", "#87d068");
		Estado estado3 = new Estado(3, "pendRevision", "Pendiente de revisión", "rgba(167, 37, 165, 0.755)");
		Estado estado4 = new Estado(4, "rechazado", "Rechazado", "#f6180d");
		Estado estado5 = new Estado(5, "finalizados", "Finalizado", "darkorange");
		estadoRepo.save(estado1);
		estadoRepo.save(estado2);
		estadoRepo.save(estado3);
		estadoRepo.save(estado4);
		estadoRepo.save(estado5);
		
		Color color1 = new Color(10, "Rojo", "#FF0000");
		Color color2 = new Color(9, "Azul", "#0000FF");
		Color color3 = new Color(8, "Amarillo", "#FFFF00");
		Color color4 = new Color(7, "Verde", "#008000");
		Color color5 = new Color(6, "Negro", "#000000");
		Color color6 = new Color(5, "Blanco", "#FFFFFF");
		Color color7 = new Color(4, "Celeste", "#87CEEB");
		Color color8 = new Color(3, "Naranja", "#FF8000");
		Color color9 = new Color(2, "Violeta", "#EE82EE");
		Color color10 = new Color(1, "Rosa", "#FFB6C1");
		
		
		colorRepo.save(color1);
		colorRepo.save(color2);
		colorRepo.save(color3);
		colorRepo.save(color4);
		colorRepo.save(color5);
		colorRepo.save(color6);
		colorRepo.save(color7);
		colorRepo.save(color8);
		colorRepo.save(color9);
		colorRepo.save(color10);

		Role cliente = new Role(ERole.ROLE_USER);
		Role encargado = new Role(ERole.ROLE_ENCARGADO);
		cliente.setId(1);
		encargado.setId(2);
		
		roleRepository.save(cliente);
		roleRepository.save(encargado);

	}
	
	@PreDestroy
	public void removeData() {
		//usuarioRepo.deleteAll();
	}
	
}
