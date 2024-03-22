package com.dev.lab5.reader;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "readers")
@Getter
@Setter
public class Reader {
	@Id
	private String email;
	private String name;
	private String surname;
}
