package com.tss.ProjektJakubStasiurka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tss.ProjektJakubStasiurka"})
public class ProjektJakubStasiurkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektJakubStasiurkaApplication.class, args);
    }

}
