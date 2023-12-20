package com.huaman.denunciaservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huaman.denunciaservice.entity.Denuncia;


@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {
	public Denuncia findByDni(String Dni);
	public List<Denuncia> findByDniContaining(String Dni,Pageable page);
}
