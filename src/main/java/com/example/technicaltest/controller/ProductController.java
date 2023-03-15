package com.example.technicaltest.controller;

import com.example.technicaltest.model.SimilarProducts;
import com.example.technicaltest.service.ProductDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Api(value = "Product Controller", description = "API endpoints for managing products")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDetailService productService;

    @Operation( operationId = "get-product-similar", summary = "Similar products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = SimilarProducts.class))
            }),
            @ApiResponse(responseCode = "404", description = "Product Not found", content = @Content)})
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            value = "/{productId}/similar")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{productId}/similar")
    public ResponseEntity<SimilarProducts> getProductById(
            @Parameter(description = "The ID of the product to retrieve")
            @ApiParam(value = "Id of the appraisal", required = true)
            @PathVariable
            String productId) throws Exception {

        ArrayList<String>  productIds = productService.getSimilarProductIdsByProductId(productId);
        SimilarProducts similarProducts = productService.getProductDetailByProductId(productIds);

        if (similarProducts != null){
            return ResponseEntity.ok(similarProducts);
        }
        return ResponseEntity.notFound().build();
    }
}
