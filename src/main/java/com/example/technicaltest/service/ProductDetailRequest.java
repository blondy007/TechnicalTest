package com.example.technicaltest.service;

import com.example.technicaltest.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ProductDetailRequest {

    public final String PRODUCT_END_POINT = "http://localhost:3001/product/";

    @Autowired
    private RestTemplate restTemplate;

    ProductDetail getProduct(String product) throws Exception {

        String detailProductEndPointEdited = PRODUCT_END_POINT + product;

        ProductDetail productDetail = null;

        try {
            UriComponents uriComponents =
                    UriComponentsBuilder.fromUriString(detailProductEndPointEdited)
                            .build()
                            .expand()
                            .encode();
            URI uri = uriComponents.toUri();


            productDetail = restTemplate.getForObject(uri, ProductDetail.class);

        } catch (HttpClientErrorException httpClientErrorException) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        } catch (HttpServerErrorException httpServerErrorException) {
            throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE);
        }

        return productDetail;
    }
}
