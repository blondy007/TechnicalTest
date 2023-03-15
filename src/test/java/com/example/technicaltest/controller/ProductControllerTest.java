package com.example.technicaltest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.example.technicaltest.model.ProductDetail;
import com.example.technicaltest.model.SimilarProducts;
import com.example.technicaltest.service.ProductDetailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductDetailService productService;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetProductById() throws Exception {
        String productId = "1";
        ArrayList<String> productIds = new ArrayList<String>();
        productIds.add(productId);

        ArrayList<ProductDetail> productlist = new ArrayList<ProductDetail>();
        SimilarProducts similarProducts = new SimilarProducts(productlist);
        ProductDetail productDetail = new ProductDetail();
        similarProducts.productDetailList.add(productDetail);

        when(productService.getSimilarProductIdsByProductId(productId)).thenReturn(productIds);
        when(productService.getProductDetailByProductId(productIds)).thenReturn(similarProducts);

        ResponseEntity<SimilarProducts> responseEntity = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetProductByIdInvalidId() throws Exception {
        String productId = "invalid";
        ArrayList<String> productIds = new ArrayList<String>();

        when(productService.getSimilarProductIdsByProductId(productId)).thenReturn(productIds);

        ResponseEntity<SimilarProducts> responseEntity = productController.getProductById(productId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
