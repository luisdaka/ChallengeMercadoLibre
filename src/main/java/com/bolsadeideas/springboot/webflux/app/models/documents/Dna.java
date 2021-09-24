package com.bolsadeideas.springboot.webflux.app.models.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Document(collection = "Dna")

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dna {

	@Id
	private String id;

	@NotEmpty
	private String[] dna;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

}
