package com.bolsadeideas.springboot.webflux.app.models.services;

import com.bolsadeideas.springboot.webflux.app.models.documents.Mutant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MutantService {
	
	public Flux<Mutant> findAll();

	public Mono<Mutant> findById(String id);
	public Mono<Mutant> save(Mutant mutant);
	
	public Mono<Void> delete(String id);

	public boolean isMutant(String[] dna);
}
