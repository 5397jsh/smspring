package edu.sm.security;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
@Slf4j
class SecurityTsets2 {
    @Autowired
    StandardPBEStringEncryptor encoder;
    @Test
    void contextLoads() {
        String txt = "서울시";
        String enctxt = encoder.encrypt(txt);
        log.info("txt:{}", txt);
        log.info("enctxt:{}", enctxt);
        String decTxt = encoder.decrypt(enctxt);
        log.info("decTxt:{}", decTxt);
    }

}