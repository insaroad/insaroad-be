/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.global.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerRedirectController {

  @GetMapping("/")
  public String redirectToSwaggerUi() {
    return "redirect:/swagger-ui/index.html";
  }
}
