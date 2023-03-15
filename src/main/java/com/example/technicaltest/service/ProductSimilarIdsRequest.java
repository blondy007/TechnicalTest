package com.example.technicaltest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@Service
public class ProductSimilarIdsRequest {

    String SIMILAR_PRODUCT_ENDPOINT = "http://localhost:3001/product/";

    @Autowired
    private RestTemplate restTemplate;

    private ArrayList productList;

    public ArrayList getProducts(String productId) throws Exception {

        String similarProductEndPointEdited = SIMILAR_PRODUCT_ENDPOINT + productId + "/similarids";

        try {
            UriComponents uriComponents =
                    UriComponentsBuilder.fromUriString(similarProductEndPointEdited)
                            .build()
                            .expand()
                            .encode();
            URI uri = uriComponents.toUri();
            productList = restTemplate.getForObject(uri, ArrayList.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        } catch (HttpServerErrorException httpServerErrorException) {
            throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return productList;
    }
}
