package com.huaman.denunciaservice.service.impl;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huaman.denunciaservice.entity.Denuncia;
import com.huaman.denunciaservice.exception.GeneralServiceException;
import com.huaman.denunciaservice.exception.NoDataServiceException;
import com.huaman.denunciaservice.exception.ValidateServiceException;
import com.huaman.denunciaservice.repository.DenunciaRepository;
import com.huaman.denunciaservice.service.DenunciaServices;
import com.huaman.denunciaservice.validator.DenunciaValidator;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Service
public class DenunciaServiceImpl implements DenunciaServices {
	
	@Autowired
	private DenunciaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Denuncia> findAll(Pageable page) {
		try {
			return repository.findAll();
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Denuncia findById(int id) {
		try {
			return repository.findById(id).orElseThrow(()->new NoDataServiceException("No existe un registro con el ID "+id));
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}		
	}
	@Override
	@Transactional(readOnly = true)
	public Denuncia findByDni(String dni) {
		try {
			return repository.findByDni(dni);
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}		
	}
	@Override
	@Transactional(readOnly = true)
	public List<Denuncia> findByDni(String Dni, Pageable page) {
		try {
			return repository.findByDniContaining(Dni,page);
			} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Denuncia create(Denuncia obj) {
		try {
			DenunciaValidator.save(obj);
			if(repository.findByDni(obj.getDni()) !=null) {
				throw new ValidateServiceException("Ya hay un registro con ese dni"+obj.getDni());
			}
		
			return repository.save(obj);			
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}		
	}

	@Override
	@Transactional
	public Denuncia update(Denuncia obj) {
		try {
			DenunciaValidator.save(obj);
			Denuncia denunciaDB=repository.findByDni(obj.getDni());
			if(denunciaDB!=null&& obj.getId()!=denunciaDB.getId()) {
				throw new ValidateServiceException("No hay un registro con ese DNI"+obj.getDni());
			}
			Denuncia denuncia=repository.findById(obj.getId()).orElseThrow(()->new NoDataServiceException("No existe un registro con el ID"+obj.getId()));
			denuncia.setDni(obj.getDni());
			denuncia.setFecha(obj.getFecha());
			denuncia.setTitulo(obj.getTitulo());
			denuncia.setDireccion(obj.getDireccion());
			denuncia.setDescripcion(obj.getDescripcion());

			return repository.save(denuncia);
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	@Override
	@Transactional
	public int delete(int id) {
		try {
			Denuncia denunciaDB= findById(id);
			if(denunciaDB==null) {
				return 0;
			}else {
				repository.save(denunciaDB);
				return 1;
			}
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}

