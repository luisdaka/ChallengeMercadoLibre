package com.bolsadeideas.springboot.webflux.app.models.services;

import com.bolsadeideas.springboot.webflux.app.models.dao.DnaDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Dna;
import com.bolsadeideas.springboot.webflux.app.utils.Util;
import com.bolsadeideas.springboot.webflux.app.models.dao.MutantDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Mutant;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@AllArgsConstructor
public class MutantServiceImpl implements MutantService{

    private static final Logger log = LoggerFactory.getLogger(MutantServiceImpl.class);

    private MutantDao dao;
    private DnaDao dnaDao;
    private Util util;

    @Override
    public Flux<Mutant> findAll() {
        return dao.findAll().
        doOnNext(m -> log.info(m.getName()));
    }

    @Override
    public Mono<Mutant> findById(String id) {
        return dao.findById(id).
                doOnNext(m -> log.info(m.getName()));
    }

    @Override
    public Mono<Mutant> save(Mutant mutant) {
        return dao.save(mutant).
                doOnNext(m -> log.info(m.getName()));

    }

    @Override
    public Mono<Void> delete(String id) {
        return dao.deleteById(id);
    }

    @Override
    public boolean isMutant(String[] dna) {

        if(util.isMutant(dna)){
            dnaDao.save(Dna.builder().dna(dna).date(new Date()).build())
                    .doOnNext(m -> log.info("DNA saved")).subscribe();
            return true;
        }
        return false ;
    }

}
