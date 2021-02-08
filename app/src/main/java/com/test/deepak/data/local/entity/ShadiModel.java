package com.test.deepak.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class ShadiModel{

	@PrimaryKey(autoGenerate = true)
	@SerializedName("id")
	@Expose
	private Long id;

	@SerializedName("image")
	private String image;

	@SerializedName("dob")
	private String dob;

	@SerializedName("fullname")
	private String fullname;

	@SerializedName("status")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
	}

	public void setFullname(String fullname){
		this.fullname = fullname;
	}

	public String getFullname(){
		return fullname;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ShadiModel{" + 
			"image = '" + image + '\'' + 
			",dob = '" + dob + '\'' + 
			",fullname = '" + fullname + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}