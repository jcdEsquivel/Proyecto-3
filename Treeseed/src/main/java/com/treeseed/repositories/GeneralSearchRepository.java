package com.treeseed.repositories;

import java.util.List;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.treeseed.pojo.GeneralSearchResultPOJO;
import com.treeseed.rowMappers.GeneralSearchResultRowMapper;
import com.treeseed.services.GeneralSearchServiceInterface;

@Repository
public class GeneralSearchRepository implements GeneralSearchRepositoryInterface {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GeneralSearchRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<GeneralSearchResultPOJO> search(String filter, String country) {
		
		String sql = "CALL `treeseeddb`.`GENERAL_SEARCH_SP`(?, ?);";

		List<GeneralSearchResultPOJO> results = (List<GeneralSearchResultPOJO>) jdbcTemplate
							.query(sql, new GeneralSearchResultRowMapper(), filter, country);


		return results;
	}

}
