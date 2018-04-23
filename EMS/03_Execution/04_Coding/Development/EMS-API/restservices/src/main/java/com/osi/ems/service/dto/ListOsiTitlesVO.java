package com.osi.ems.service.dto;

public class ListOsiTitlesVO {

	private Integer titleId;
	private String titleCode;
	private String titleShortName;
	private String titleLongName;
	
	
	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getTitleCode(){
		 return titleCode;
	}

	public void setTitleCode(String titleCode){
		 this.titleCode = titleCode;
	}

	public String getTitleShortName(){
		 return titleShortName;
	}

	public void setTitleShortName(String titleShortName){
		 this.titleShortName = titleShortName;
	}

	public String getTitleLongName(){
		 return titleLongName;
	}

	public void setTitleLongName(String titleLongName){
		 this.titleLongName = titleLongName;
	}

}