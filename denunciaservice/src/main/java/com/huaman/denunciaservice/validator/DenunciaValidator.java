package com.huaman.denunciaservice.validator;

import com.huaman.denunciaservice.entity.Denuncia;
import com.huaman.denunciaservice.exception.ValidateServiceException;


public class DenunciaValidator {
	public static void save(Denuncia denuncia) {
		if(denuncia.getDni()==null || denuncia.getDni().trim().isEmpty()) {
			throw new ValidateServiceException("El DNI es requerido");
		}
		if(denuncia.getDni().length()>8) {
			throw new ValidateServiceException("El DNI no debe contener mas de 8 caracteres");
		}
		if(denuncia.getTitulo().length()>3) {
			throw new ValidateServiceException("El Titulo no debe contener mas de 3 caracteres");
		}
		
		
		
	}
}