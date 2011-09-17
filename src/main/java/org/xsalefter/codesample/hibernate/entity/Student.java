package org.xsalefter.codesample.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student implements Serializable {

	private static final long serialVersionUID = 2166429239305851272L;
	
	private Long id;
	private String name;
	private String email;
	
	public Student() {}
	
	public Student(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Student(Long id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.email = mail;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="student_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="name", nullable=false, length=30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic(fetch=FetchType.LAZY)
	@Column(name="email", nullable=false, length=60)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
