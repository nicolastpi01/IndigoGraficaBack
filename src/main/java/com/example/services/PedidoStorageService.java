package com.example.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.ColorDTO;
import com.example.dto.PedidoDTO;
import com.example.dto.UsuarioDTO;
import com.example.model.Color;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Tipo;
import com.example.repository.ColorDBRepository;
import com.example.repository.PedidoDBRepository;
import com.example.repository.TipoDBRepository;

@Service
public class PedidoStorageService {
	
	@Autowired
	private PedidoDBRepository pedidoDBRepository;
	@Autowired
	private TipoDBRepository tipoRepo;
	@Autowired
	private ColorDBRepository colorRepo;
	
	@Transactional
	public Pedido store(MultipartFile[] files,Pedido pedido) {
		
		Set<FileDB> filesDB = new HashSet<>();
		//List<String> fileNames = new ArrayList<>();
    	Arrays.asList(files).stream().forEach(file -> {
    		try {
				FileDB FileDB = new FileDB(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes());
				filesDB.add(FileDB);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			}
          //storageService.store(file);
          //fileNames.add(file.getOriginalFilename());
      });
    	
    	//tipoRepo.save(pedido.getTipo());
    	
    	
    	
    	pedido.setFiles(filesDB);
    	
    	return pedidoDBRepository.save(pedido);
		
		//Pedido pedido = pedidoDTO.toPedido();
		//Tipo tipo = pedidoDTO.getTipo().toTipo();
		
		// Llevarlo al Controller
		//Set<Color> colores = pedidoDTO.getColores().stream().map((colorDTO -> colorDTO.toColor())).collect(Collectors.toSet());
		//Set<FileDB> files = pedidoDTO.getFiles().stream().map((fileDTO -> fileDTO.toFile())).collect(Collectors.toSet());
		
		// No deberia estar // Cargar antes los colores
		//colores.forEach(color -> {
		//	colorRepo.save(color);
		//});
		
		// No deberia ir
		//colores.forEach(color -> {
		//	pedido.addColor(color);
		//});
		
		// Van en cascada
		//files.forEach((file -> {
		//	//file.setRequerimientos(new HashSet<>());
		//	pedido.addFile(file);
		//}));
		
		//pedido.setFiles(new HashSet<>());
		
		//tipo.addPedido(pedido);
		//return tipoRepo.save(tipo);
	}

	@Transactional(readOnly=true)
	public List<Pedido> getAllByState(String state) {
		return pedidoDBRepository.findByState(state) ;
	}

	@Transactional
	public void reservar(String id, UsuarioDTO usuarioDTO) throws IOException {
		Pedido pedido = pedidoDBRepository.findById(id).get();
		pedido.setState("reservado");
		pedido.setEncargado(usuarioDTO.getNombre());
		pedidoDBRepository.save(pedido);
	}

}
