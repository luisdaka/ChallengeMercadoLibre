package com.bolsadeideas.springboot.webflux.app.utils;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UtilTest {

    @InjectMocks
    Util util;

    @Test
    public void successValidate(){
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        Assert.assertEquals(true,util.isMutant(dna));
    }

    @Test
    public void falseValidate(){
        String[] dna = {"xxxxx","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        Assert.assertEquals(false,util.isMutant(dna));
    }
}
