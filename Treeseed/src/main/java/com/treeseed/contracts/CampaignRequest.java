package com.treeseed.contracts;

import java.util.Date;
import java.util.List;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.pojo.CampaignPOJO;
import com.treeseed.pojo.NonprofitPOJO;

public class CampaignRequest extends BasePagingRequest {

	private CampaignPOJO campaign;

	private int id;
	private Date startDate;
	
	private int nonprofitId;
	



	private String name;
	private String nonprofitName;
	private int causeId;
	private long fechaInicio;
	private long fechaFin;

	public String getNonprofitName() {
		return nonprofitName;
	}

	private NonprofitPOJO nonprofit;
	public void setNonprofitName(String nonprofitName) {
		this.nonprofitName = nonprofitName;
	}

	public int getCauseId() {
		return causeId;
	}

	public void setCauseId(int causeId) {
		this.causeId = causeId;
	}

	public long getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(long fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public long getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(long fechaFin) {
		this.fechaFin = fechaFin;
	}


	private List<PostCampaign> postCampaigns;

	public CampaignPOJO getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignPOJO campaign) {
		this.campaign = campaign;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public NonprofitPOJO getNonprofit() {
		return nonprofit;
	}

	public void setNonprofit(NonprofitPOJO nonprofit) {
		this.nonprofit = nonprofit;
	}

	public List<PostCampaign> getPostCampaigns() {
		return postCampaigns;
	}

	public void setPostCampaigns(List<PostCampaign> postCampaigns) {
		this.postCampaigns = postCampaigns;
	}

	public int getNonprofitId() {
		return nonprofitId;
	}

	public void setNonprofitId(int nonprofitId) {
		this.nonprofitId = nonprofitId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
