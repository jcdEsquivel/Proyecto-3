package com.treeseed.contracts;

import java.util.List;

import com.treeseed.pojo.TransparencyReportPOJO;

public class TransparencyReportResponse extends BaseResponse {
	
private List<TransparencyReportPOJO> transparencyReports;
private TransparencyReportPOJO transparencyReport;
	
	public TransparencyReportResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<TransparencyReportPOJO> getTransparencyReports() {
		return transparencyReports;
	}

	public void setTransparencyReports(
			List<TransparencyReportPOJO> transparencyReports) {
		this.transparencyReports = transparencyReports;
	}

	public TransparencyReportPOJO getTransparencyReport() {
		return transparencyReport;
	}

	public void setTransparencyReport(TransparencyReportPOJO transparencyReport) {
		this.transparencyReport = transparencyReport;
	}
	
	
}
