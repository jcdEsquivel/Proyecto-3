package com.treeseed.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.treeseed.pojo.RecurrableInformationResultPOJO;
import com.treeseed.rowMappers.RecurrableInformationResultRowMapper;

public class RecurrableInformationReporsitory implements RecurrableInformationRepositoryInterface{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public RecurrableInformationReporsitory(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * Returns the list with the recurrable information results.
	 *
	 * @return the RecurrableInformationResultPOJO list
	 * @param the donorId
	 */
	@Override
	public List<RecurrableInformationResultPOJO> getRecurrableInformation(
			int donorId) {
		String sql = "CALL `treeseeddb`.`GET_RECURRABLE_INFORMATION_SP`(?);";

		List<RecurrableInformationResultPOJO> results = (List<RecurrableInformationResultPOJO>) jdbcTemplate
							.query(sql, new RecurrableInformationResultRowMapper(), donorId);

		return results;
	}

}
