package com.example.technicaltest.service;

import com.example.technicaltest.model.ProductDetail;
import com.example.technicaltest.model.SimilarProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

@Service
public class ProductDetailService {

    @Autowired
    ProductSimilarIdsRequest productSimilarIdsRequest;

    @Autowired
    ProductDetailRequest productDetailRequest;

    public ArrayList getSimilarProductIdsByProductId(String productId) throws Exception {
        ArrayList productList = productSimilarIdsRequest.getProducts(productId);

        if (productList == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return productList;
    }

    public SimilarProducts getProductDetailByProductId(ArrayList productIds) {

        ArrayList<ProductDetail> productlist = new ArrayList<ProductDetail>();
        productIds.stream().forEach((p) -> {
            ProductDetail productDetail = null;
            try {
                productDetail = productDetailRequest.getProduct(p.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            productlist.add(productDetail);
        });
        return new SimilarProducts(productlist);
    }
}
