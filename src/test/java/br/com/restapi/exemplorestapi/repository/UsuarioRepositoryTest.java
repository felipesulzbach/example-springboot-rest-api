package br.com.restapi.exemplorestapi.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.restapi.exemplorestapi.ExemplorestapiApplication;
import br.com.restapi.exemplorestapi.models.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = { ExemplorestapiApplication.class })
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // usar o banco original.
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@Test
	public void testarFindByEstabelecimento() throws Exception {
		Optional<User> entity = repository.findById(1L);
		assertEquals(entity.isPresent(), true);
		// assertNull(entity.get().getDataDesativado());
		// assertEquals(entity.get().getMeioCaptura(), MeioCaptura.TEF);
	}
}
