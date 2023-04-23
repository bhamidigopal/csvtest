package com.svb.csvjdbc;

public class Catalog {

	private String name;
	private String deployedDate;
	private String version;
	
	
	private Section section;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDeployedDate() {
		return deployedDate;
	}


	public void setDeployedDate(String deployedDate) {
		this.deployedDate = deployedDate;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public Section getSection() {
		return section;
	}


	public void setSection(Section section) {
		this.section = section;
	}
	
	
	
}
