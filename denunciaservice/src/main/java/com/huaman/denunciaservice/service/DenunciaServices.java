package com.huaman.denunciaservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.huaman.denunciaservice.entity.Denuncia;



public interface DenunciaServices {
	public List<Denuncia> findAll(Pageable page);
	public Denuncia findById(int id);
	public Denuncia findByDni(String dni);
	public List<Denuncia> findByDni(String dni, Pageable page);
    public Denuncia create(Denuncia obj);
    public Denuncia update(Denuncia obj);
    public int delete(int id);
}
