package com.treeseed.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.treeseed.pojo.GeneralSearchResultPOJO;

public class GeneralSearchResultRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		GeneralSearchResultPOJO result = new GeneralSearchResultPOJO();
		result.setId(rs.getInt("ID"));
		result.setName(rs.getString("NAME"));
		result.setType(rs.getString("TYPE"));
		result.setImageURL(rs.getString("IMG"));
		result.setCount(rs.getInt("COUNTVALUE"));
		
		
		if(result.getType().equals("nonProfit")){
			result.setParam("nonProfitId");
		}else if(result.getType().equals("campaign")){
			result.setParam("campaignId");
		}else{
			result.setParam("donorId");
		}
		
		return result;
		
	}

	
	
	
}
