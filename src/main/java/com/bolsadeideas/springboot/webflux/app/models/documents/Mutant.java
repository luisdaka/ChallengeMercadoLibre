package com.bolsadeideas.springboot.webflux.app.models.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "Mutants")

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mutant {

	@Id
	private String id;

	@NotEmpty
	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	@NotNull
	private Boolean mutant;
}
