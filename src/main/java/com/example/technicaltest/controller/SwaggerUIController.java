package com.example.technicaltest.controller;

import io.swagger.v3.oas.models.OpenAPI;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SwaggerUIController {

    @GetMapping(value = "/api-docs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OpenAPI> getDocumentation() {
        return ResponseEntity.ok(new io.swagger.v3.oas.models.OpenAPI());
    }

    @GetMapping(value = "/swagger-ui.html")
    public ModelAndView redirectToUi(HttpServletRequest request) {
        return new ModelAndView(
                "redirect:/swagger-ui/index.html?configUrl=" + request.getRequestURL().toString()
                        .replace("swagger-ui.html", "api-docs"));
    }
}
