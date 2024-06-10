package org.esupportail.referentiel.pcscol.jdbc.repository;

import java.util.List;
import java.util.Optional;

public interface OdfRepository<T, ID> {

	
	Optional<T> findById(ID id);
	
	boolean existsById(ID id);
	
	List<T> findAll();
	
	List<T> findAllById(List<ID> ids);
}
