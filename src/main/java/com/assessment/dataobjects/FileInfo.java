package com.assessment.dataobjects;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Prerna Garg
 *
 * This is the data object class.
 */
@Entity
@Table(name = "images")
public class FileInfo {

	/**
	 * Default Constructor
	 */
	public FileInfo() {
		super();
	}

	/**
	 * A constructor to set the file data.
	 * 
	 * @param imageId
	 * @param imageName
	 * @param picBytes
	 */
	public FileInfo(String imageName, byte[] picBytes) {
		super();
		this.imageName = imageName;
		this.picBytes = picBytes;
	}

	@Id
	@Column(name = "name")
	private String imageName;

	@Column(name = "pic_bytes")
	private byte[] picBytes;

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the picBytes
	 */
	public byte[] getPicBytes() {
		return picBytes;
	}

	/**
	 * @param picBytes the picBytes to set
	 */
	public void setPicBytes(byte[] picBytes) {
		this.picBytes = picBytes;
	}

	@Override
	public String toString() {
		return "FileInfo [imageName=" + imageName + ", picBytes=" + Arrays.toString(picBytes) + "]";
	}

}
