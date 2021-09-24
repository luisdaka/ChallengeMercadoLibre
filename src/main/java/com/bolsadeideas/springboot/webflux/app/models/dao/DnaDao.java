package com.bolsadeideas.springboot.webflux.app.models.dao;

import com.bolsadeideas.springboot.webflux.app.models.documents.Dna;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DnaDao extends ReactiveMongoRepository<Dna, String>{

}
