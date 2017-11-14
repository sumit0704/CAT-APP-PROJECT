package com.catapp.entity;

public class FileInfo {
	private String entityId;
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	private String fileName;
	private String filePath;
	private String cellLine;
	private String phenotype;
	private String assayType;
	private String fileType;
	private String dilution;
	private String timePoint;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCellLine() {
		return cellLine;
	}

	public void setCellLine(String cellLine) {
		this.cellLine = cellLine;
	}

	public String getPhenotype() {
		return phenotype;
	}

	public void setPhenotype(String phenotype) {
		this.phenotype = phenotype;
	}

	public String getAssayType() {
		return assayType;
	}

	public void setAssayType(String assayType) {
		this.assayType = assayType;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDilution() {
		return dilution;
	}

	public void setDilution(String dilution) {
		this.dilution = dilution;
	}

	public String getTimePoint() {
		return timePoint;
	}

	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}

}
