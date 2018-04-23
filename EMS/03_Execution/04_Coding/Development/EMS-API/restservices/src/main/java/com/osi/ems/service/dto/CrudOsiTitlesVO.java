package com.osi.ems.service.dto;

public class CrudOsiTitlesVO {

	private Integer titleId;
	private String titleShortName;
	private String titleLongName;
	private String titleCode;
	

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
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

	public String getTitleCode(){
		 return titleCode;
	}

	public void setTitleCode(String titleCode){
		 this.titleCode = titleCode;
	}



}