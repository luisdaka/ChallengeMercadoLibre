package com.bolsadeideas.springboot.webflux.app.services;

import static org.junit.Assert.*;

import com.bolsadeideas.springboot.webflux.app.models.dao.MutantDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Mutant;
import com.bolsadeideas.springboot.webflux.app.models.services.MutantService;
import com.bolsadeideas.springboot.webflux.app.models.services.MutantServiceImpl;
import com.bolsadeideas.springboot.webflux.app.utils.Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.io.Console;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MuntantServiceTest {

    @InjectMocks
    MutantServiceImpl mutantService;

    MutantDao dao = Mockito.mock(MutantDao.class);

    @Mock
    Util util;

    @Test
    public void delete(){
        Mono<Void> ans = Mono.empty();
        when(dao.deleteById(anyString())).thenReturn(Mono.empty());

        mutantService.delete("1").subscribe();
        assertEquals(mutantService.delete("1"),ans);
    }

    @Test
    public void save(){
        Mutant mutant =  Mutant.builder()
                .id("1").mutant(false).createAt(new Date()).name("Luiskk").build();
        when(dao.save(any())).thenReturn(Mono.just(mutant));

        String ans = mutantService.save(mutant)
                .map(Mutant::getName).block();
        assertEquals(ans,mutant.getName());
    }

    @Test
    public void findby(){
        Mutant mutant =  Mutant.builder()
                .id("1").mutant(false).createAt(new Date()).name("Luiskk").build();
        when(dao.findById(anyString())).thenReturn(Mono.just(mutant));
        mutantService.findById("1").subscribe();
        String ans = mutantService.findById("1")
                .map(Mutant::getName).block();
        assertEquals(ans,mutant.getName());
    }

    @Test
    public void isMutant() {
        when(util.isMutant(any(String[].class))).thenReturn(false);
        String[] dna = {"xxxxx","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        Assert.assertEquals(false,util.isMutant(dna));
    }

}

