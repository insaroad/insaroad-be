/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InsaroadApplication {

  public static void main(String[] args) {
    SpringApplication.run(InsaroadApplication.class, args);
  }
}
