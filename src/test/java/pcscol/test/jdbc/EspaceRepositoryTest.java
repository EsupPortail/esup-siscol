package pcscol.test.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

import org.esupportail.referentiel.Siscol;
import org.esupportail.referentiel.pcscol.jdbc.model.schema_odf.Espace;
import org.esupportail.referentiel.pcscol.jdbc.repository.EspaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@ContextConfiguration(classes = { Siscol.class })
@SpringBootTest
@SpringJUnitConfig
@ActiveProfiles("test")
public class EspaceRepositoryTest {
	
	@Autowired
	EspaceRepository espaceRepository;
	@Autowired
	NamedParameterJdbcTemplate template;
	
	
	@Test
	public void testFindALL() {
		final List<UUID> l=new ArrayList<>();
		List<Espace> espaces = espaceRepository.findAll();
		espaces.forEach(e->{
			//l.add(e.getId());
			System.out.println(e.getCode());
			
		});
		l.add(espaces.get(0).getId());
		
		try{
			List<String> espaces2 = findAllById(l);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//assertNotNull(espaces2);
		//assertEquals(espaces.size(), espaces2.size());
	}

	
	@Test
	public void testFindALL2() {
		List<Espace> espaces = espaceRepository.findAll(false);
		espaces.forEach(e->{
			System.out.println(e.getCode());
		});
	}
	
	//@Test
	public List<String> findAllById(List<UUID> ids) {
		SqlParameterSource parameters = new MapSqlParameterSource("ids", ids);
		List<String> espaces = template.queryForList("SELECT code_structure from schema_odf.espace WHERE id IN (:ids)",
				parameters, String.class);
		return espaces;
	}
}
