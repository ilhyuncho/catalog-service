package com.cihexample.catalogservice.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTests {
    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    int port;


    @Test
    @Disabled
    public void hello() {

//        String result=testRestTemplate.getForObject("/",String.class);
//        assertThat(result).isEqualTo("Welcom th the catalog from the config server2");
    }

}
