package com.example.utils;

import com.example.dto.ColorDTO;
import com.example.dto.FileDTO;
import com.example.dto.PedidoDTO;
import com.example.dto.RequerimientoDTO;
import com.example.dto.TipoDTO;
import com.example.model.Color;
import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.model.Requerimiento;
import com.example.model.Tipo;

public class Helper {
	
	
	public FileDB fileFromDTO(FileDTO fileDTO) {
		
		FileDB fileDB = new FileDB(fileDTO.getName(), fileDTO.getType(), fileDTO.getData());
		fileDB.setId(fileDTO.getId());
		fileDB.setRequerimientos(fileDTO.getRequerimientos());
		return fileDB;
	}
	
	public Pedido pedidoFromDTO(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido(pedidoDTO.getNombre(), pedidoDTO.getNombreExtendido(), pedidoDTO.getTipografia(), pedidoDTO.getAlto(), pedidoDTO.getAncho(), pedidoDTO.getDescripcion(), pedidoDTO.getCantidad(), pedidoDTO.getState(), pedidoDTO.getPropietario());
		pedido.setId(pedidoDTO.getId());
		pedido.setFiles(pedidoDTO.getFiles());
		pedido.setTipo(pedidoDTO.getTipo());
		pedido.setColores(pedidoDTO.getColores());
		pedido.setEncargado(pedidoDTO.getEncargado());
		return pedido;
	}
	
	public Requerimiento requerimientoFromDTO(RequerimientoDTO reqDTO) {
		Requerimiento req = new Requerimiento(reqDTO.getDescripcion(), reqDTO.getChequeado());
		req.setId(reqDTO.getId());
		req.setFile(reqDTO.getFile());
		return req;
	}

	public Tipo tipoFromDTO(TipoDTO tipoDTO) {
		Tipo tipo = new Tipo(tipoDTO.getNombre(), tipoDTO.getAlto(), tipoDTO.getAncho(), tipoDTO.getTipografia());
		tipo.setId(tipoDTO.getId());
		return tipo;
	}

	public Color colorFromDTO(ColorDTO colorDTO) {
		Color color = new Color(colorDTO.getNombre(), colorDTO.getHexCode());
		color.setId(colorDTO.getId());
		return color;
	}

}
