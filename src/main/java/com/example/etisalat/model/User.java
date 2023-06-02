package com.example.etisalat.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UserData")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4800733178523158989L;

	@Id
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	private Long id;
	private String name;
	private long followers;

	public User(String name, long followers) {
		super();
		this.name = name;
		this.followers = followers;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", followers=" + followers + "]";
	}

}
