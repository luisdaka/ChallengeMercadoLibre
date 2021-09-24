package com.bolsadeideas.springboot.webflux.app.controllers;


import com.bolsadeideas.springboot.webflux.app.models.dao.DnaDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Mutant;
import com.bolsadeideas.springboot.webflux.app.models.services.MutantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/mutant")
@AllArgsConstructor
public class MutantController {

	private MutantService service;
	private static final Logger log = LoggerFactory.getLogger(MutantController.class);

	@ApiOperation(value = "Method to Get all Mutants")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "All Mutants obtained successfully"),
			@ApiResponse(code = 404, message = "not found Url"),
			@ApiResponse(code = 500, message = "Internal error")})
	@GetMapping(path ="/all")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Mutant> findAll(){
        Flux<Mutant> mutants = service.findAll()
        		.doOnNext(m -> log.info(m.getName()));
        return mutants;
	}
	/**
	 * Method to save Mutant
	 * @param mutant {@link Mutant}
	 * @return ResponseEntity {@link ResponseEntity}
	 */
	@ApiOperation(value = "Method to Save Mutant")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Mutant save successfully"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	@PostMapping(path ="/save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Mutant mutant){
		if (mutant.getCreateAt() == null) {
			mutant.setCreateAt(new Date());
		}
		service.save(mutant)
				.doOnNext(m -> log.info(m.getName())).subscribe();
	}

	/**
	 * Method to find Mutant by id
	 * @param id {@link String}
	 * @return Mono<Mutant> {@link Mono<Mutant>}
	 */
	@ApiOperation(value = "Method to find Mutant by id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Mutant found"),
			@ApiResponse(code = 404, message = "Mutant not found "),
			@ApiResponse(code = 500, message = "Internal error")})
	@GetMapping(path ="findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<Mutant> findById(@PathVariable String id){
		 Mono<Mutant> mutant = service.findById(id)
				 .doOnNext(m -> log.info(m.getName() + "has been found"));
		return  mutant;
	}

	/**
	 * Method to know if a Dna belong to a Mutant
	 * @param dna {@link String}
	 * @return ResponseEntity {@link ResponseEntity}
	 */
	@ApiOperation(value = "Using DNA,this method estimates if it belong to a mutant")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Mutant found"),
			@ApiResponse(code = 404, message = "Mutant not found "),
			@ApiResponse(code = 403, message = "This is not a Mutant"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error")})
	@GetMapping(path ="/ismutant/{dna}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity isMutant(@PathVariable String[] dna){

		boolean isMutant = service.isMutant(dna);
		log.info(isMutant == true? "this is a mutant" : "this is not a mutant");

		if (isMutant){

			return new ResponseEntity<>("This is a Mutant",HttpStatus.OK);
		}
		return new ResponseEntity<>("This is not a Mutant",HttpStatus.FORBIDDEN);
	}

	/**
	 * Method to Delete Mutant by Id
	 * @param id {@link String}
	 */
	@ApiOperation(value = "Find Mutant by id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Mutant found"),
			@ApiResponse(code = 404, message = "Mutant not found "),
			@ApiResponse(code = 500, message = "Internal error")})
	@DeleteMapping(path ="deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable String id){
		service.delete(id)
				.doOnNext(m -> log.info("Mutant has been found")).subscribe();
	}

}
