package org.esupportail.referentiel.pcscol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChcMapper {

	public ChcMapper Instance = Mappers.getMapper(ChcMapper.class);
}	
