package com.subject.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Teacher {

	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	private String email;
	private String role;
	private String username;
	private String password;
	private Boolean status;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date createdOn;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date updatedOn;
}