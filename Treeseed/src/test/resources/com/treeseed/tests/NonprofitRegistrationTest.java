import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.treeseed.controllers.*;
import com.treeseed.pojo.*;
import com.mysql.fabric.Response;
import com.treeseed.contracts.*;

public class NonprofitUnitTest {

	@Autowired
    JdbcTemplate jdbcTemplate;
	KeyHolder keyHolder = new GeneratedKeyHolder();
	
	UsersController userController =new UserController();
	CatalogController catalogController=new CatalogController();
	
	int idCountry = 0;
	int idCause = 0;
	
	@BeforeClass
	@Transactional
	public void initialize(){
		jdbcTemplate.update("INSERT INTO catalog (english, spanish, name, description, is_active, type) values ('Costa Rica', 'Costa Rica', 'CRC', 'Pais', 'true', 'country') ;",keyHolder);
		idCountry =  keyHolader.get().longValue();
		keyHolder.destroy();
		keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update("INSERT INTO catalog (english, spanish, name, description, is_active, type) values ('Animals', 'Animales', 'Protección de Animales', 'Causa', 'true', 'cause') ;",keyHolder);
		idCause =  keyHolader.get().longValue();
		keyHolder.destroy();
	}
	
	
	@AfterClass
	@Transactional
	public void finalize(){
		jdbcTemplate().update("DELETE FROM catalog WHERE id = ?", new Object[] { idCountry });
		jdbcTemplate().update("DELETE FROM catalog WHERE id = ?", new Object[] { idCause });
	}
	
	

	@Test
	public void testInsertarDatosCorrectos() {
		String[] expected={"ONG Nombre", "email@ong.com", "pass", idCountry, idCause};
		String[] actual = "";
		
		userController.nonProfitCreate("ONG Nombre", "email@ong.com", "pass", idCountry, idCause, );
		
		actual =jdbcTemplate.queryForObject("SELECT * FROM nonprofit ORDER BY id DESC LIMIT 1;");
		
		assertArrayEquals(expected, actual);
		fail("Not yet implemented");
	}
	/*
	@Test
	public void testInsertarDatosCorrectos() {
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testInsertarDatosCorrectos() {
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testInsertarDatosCorrectos() {
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testInsertarDatosCorrectos() {
		
		fail("Not yet implemented");
	}*/

}
