package com.bolsadeideas.springboot.webflux.app.utils;

import com.bolsadeideas.springboot.webflux.app.models.services.MutantServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Util {

    private boolean DnaValidate(String[] dna) {


        for (int i = 0; i < dna.length; i++) {
            if (dna[i].matches(".*[^ATCG].*")) {
                return false;
            }
            if (dna[i].length() < 4 || dna.length < 4 || dna[i].length() != dna.length) {
                return false;
            }
        }
        return true;
    }

    private boolean isEqual(char a, char b, char c, char d) {
        return a == b && b == c && c == d;
    }

    public boolean isMutant(String[] dna) {

        if (!DnaValidate(dna)){
            return false;
        }

        for (int i = 0; i < dna.length; i++) {

            for (int j = 0; j < dna[i].length(); j++) {


                if (j < dna[i].length() - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2), dna[i].charAt(j + 3))) {
                        return true;
                    }
                }

                if (i < dna.length - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j), dna[i + 3].charAt(j))) {
                        return true;
                    }
                }


                if (i < dna.length - 3 && j < dna[i].length() - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
                        return true;
                    }
                }


                if (i >= 3 && j < dna[i].length() - 3) {
                    if (isEqual(dna[i].charAt(j), dna[i - 1].charAt(j + 1), dna[i - 2].charAt(j + 2), dna[i - 3].charAt(j + 3))) {
                        return true;
                    }
                }

            }

        }
        return false;
    }
}
