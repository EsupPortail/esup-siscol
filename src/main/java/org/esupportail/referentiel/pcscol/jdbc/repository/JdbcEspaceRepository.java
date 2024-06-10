package org.esupportail.referentiel.pcscol.jdbc.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.esupportail.referentiel.pcscol.jdbc.model.schema_odf.Espace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcEspaceRepository implements EspaceRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public Espace findOne(UUID id) {
		Espace espace = jdbcTemplate.queryForObject("SELECT * FROM schema_odf.espace WHERE id=?",
				BeanPropertyRowMapper.newInstance(Espace.class), id);
		return espace;
	}

	@Override
	public Optional<Espace> findById(UUID id) {
		Espace espace = jdbcTemplate.queryForObject("SELECT * FROM schema_odf.espace WHERE id=?",
				BeanPropertyRowMapper.newInstance(Espace.class), id);
		return Optional.of(espace);
	}

	@Override
	public List<Espace> findAllById(List<UUID> ids) {
		SqlParameterSource parameters = new MapSqlParameterSource("ids", ids);
		List<Espace> espaces = namedJdbcTemplate.queryForList("SELECT * from schema_odf.espace WHERE id IN (:ids)",
				parameters, Espace.class);
		return espaces;
	}

	@Override
	public boolean existsById(UUID id) {
		return (findById(id).isPresent());
	}

	@Override
	public List<Espace> findAll() {
		List<Espace> espaces = jdbcTemplate.query("SELECT * from schema_odf.espace",
				BeanPropertyRowMapper.newInstance(Espace.class));
		return espaces;
	}

	@Override
	public List<Espace> findAll(Boolean temoin_active) {
		List<Espace> espaces = jdbcTemplate.query("SELECT * from schema_odf.espace s WHERE s.temoin_active=?",
				BeanPropertyRowMapper.newInstance(Espace.class), temoin_active);
		return espaces;
	}

	@Override
	public List<Espace> findByCode(String code) {
		List<Espace> espaces = jdbcTemplate.query("SELECT * from schema_odf.espace s WHERE s.code=?",
				BeanPropertyRowMapper.newInstance(Espace.class), code);
		return espaces;
	}

	@Override
	public List<Espace> findByCodeStructure(String code_structure) {
		List<Espace> espaces = jdbcTemplate.query("SELECT * from schema_odf.espace s WHERE s.code_structure=?",
				BeanPropertyRowMapper.newInstance(Espace.class), code_structure);
		return espaces;
	}

	@Override
	public List<Espace> findByAnneeUniversitaire(Integer annee_universitaire) {
		List<Espace> espaces = jdbcTemplate.query("SELECT * from schema_odf.espace s WHERE s.annee_universitaire=?",
				BeanPropertyRowMapper.newInstance(Espace.class), annee_universitaire);
		return espaces;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
