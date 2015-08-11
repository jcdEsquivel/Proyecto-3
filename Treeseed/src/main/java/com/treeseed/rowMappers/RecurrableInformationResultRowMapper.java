package com.treeseed.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.treeseed.pojo.RecurrableInformationResultPOJO;

/**
 * The Class RecurrableInformationResultRowMapper, maps the result 
 * of the query into the POJO object.
 */
public class RecurrableInformationResultRowMapper implements RowMapper{
	
	/**
	 * Returns the mapped object.
	 *
	 * @return the RecurrableInformationResultPOJO
	 * @param the resultset
	 * @param the args
	 */
	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		RecurrableInformationResultPOJO result = new RecurrableInformationResultPOJO();
		result.setAmount(rs.getDouble("amount"));
		result.setNonprofitId(rs.getInt("non_profit_id"));
		result.setNonprofitName(rs.getString("name"));
		result.setEnglishCause(rs.getString("english"));
		result.setSpanishCause(rs.getString("spanish"));
		return result;		
	}	
}
