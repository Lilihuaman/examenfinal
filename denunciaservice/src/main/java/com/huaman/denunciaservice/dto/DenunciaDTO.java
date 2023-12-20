package com.huaman.denunciaservice.dto;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaDTO {
	private int id;
	private String dni;
	private Date fecha;
	private String titulo;
	private String direccion;
	private String descripcion;

}
