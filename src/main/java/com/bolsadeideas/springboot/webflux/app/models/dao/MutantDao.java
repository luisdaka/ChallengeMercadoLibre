package com.bolsadeideas.springboot.webflux.app.models.dao;

import com.bolsadeideas.springboot.webflux.app.models.documents.Mutant;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MutantDao extends ReactiveMongoRepository<Mutant, String>{

}
