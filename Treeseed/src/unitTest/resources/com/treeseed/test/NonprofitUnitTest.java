package com.treeseed.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.security.interfaces.RSAKey;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.treeseed.config.*;
import com.treeseed.repositories.*;
import com.treeseed.controllers.CatalogController;
import com.treeseed.controllers.UsersController;
import com.treeseed.pojo.NonprofitPOJO;

import scala.annotation.meta.param;

public class NonprofitUnitTest {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	UsersController userController =new UsersController();
	CatalogController catalogController=new CatalogController();
	
	String idCountry = "0";
	String idCause = "0";
	
	@BeforeClass
	@Transactional
	/*public static void initialize(){
		String sql1 = "INSERT INTO catalog (description,english,  is_active, name, spanish, type) values ('Pais','Costa Rica', true,'CRC','Costa Rica', 'country') ;";
		String sql2 = "INSERT INTO catalog (english, spanish, name, description, is_active, type) values ('Animals', 'Animales', 'Protección de Animales', 'Causa', 'true', 'cause') ;";
		idCountry = jdbcTemplate.queryForObject(sql1,String.class);
		idCause = jdbcTemplate.queryForObject(sql2,String.class);
	}*/
	
	
	
	/*@Transactional
	@AfterClass
	public static void finalize(){
		
		jdbcTemplate.update("DELETE FROM catalog WHERE id = ?", idCountry);
		jdbcTemplate.update("DELETE FROM catalog WHERE id = ?", idCause);
	}
	*/
	

	@Test
	public  void testInsertarDatosCorrectos() {
		String[] expected={"ONG Nombre", "email@ong.com", "pass", ""+idCountry+"", idCause};
		
		String[] actual ={};
		
		userController.nonProfitCreate("ONG Nombre", "email@ong.com", "pass", idCountry, idCause, null );
		
		
		assertArrayEquals(expected, expected);
	}

}
