package com.example.utils;

import com.example.dto.FileDTO;
import com.example.dto.PedidoDTO;
import com.example.dto.RequerimientoDTO;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Requerimiento;

public class Helper {
	
	
	public FileDB fileFromDTO(FileDTO fileDTO) {
		
		FileDB fileDB = new FileDB(fileDTO.getName(), fileDTO.getType(), fileDTO.getData());
		fileDB.setId(fileDTO.getId());
		fileDB.setRequerimientos(fileDTO.getRequerimientos());
		return fileDB;
	}
	
	// (String nombre, String nombreExtendido, String tipografia, Integer alto, Integer ancho, String descripcion, Integer cantidad, String state)
	public Pedido pedidoFromDTO(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido(pedidoDTO.getNombre(), pedidoDTO.getNombreExtendido(), pedidoDTO.getTipografia(), pedidoDTO.getAlto(), pedidoDTO.getAncho(), pedidoDTO.getDescripcion(), pedidoDTO.getCantidad(), pedidoDTO.getState());
		pedido.setId(pedidoDTO.getId());
		pedido.setFiles(pedidoDTO.getFiles());
		return pedido;
	}
	/*
	 * 	this.id = req.getId();
		this.descripcion = req.getDescripcion();
		this.chequeado = req.getChequeado();
		this.file = null;
	 */

	public Requerimiento requerimientoFromDTO(RequerimientoDTO reqDTO) {
		Requerimiento req = new Requerimiento(reqDTO.getDescripcion(), reqDTO.getChequeado());
		req.setId(reqDTO.getId());
		req.setFile(reqDTO.getFile());
		return req;
	}

}
