package com.example.technicaltest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.example.technicaltest.model.ProductDetail;
import com.example.technicaltest.model.SimilarProducts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailServiceTest {

    @Mock
    private ProductSimilarIdsRequest productSimilarIdsRequest;

    @Mock
    private ProductDetailRequest productDetailRequest;

    @InjectMocks
    private ProductDetailService productDetailService;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetSimilarProductIdsByProductId() throws Exception {
        String productId = "12345";
        ArrayList<String> productList = new ArrayList<String>();
        productList.add("67890");

        when(productSimilarIdsRequest.getProducts(productId)).thenReturn(productList);

        ArrayList<String> result = productDetailService.getSimilarProductIdsByProductId(productId);

        assertNotNull(result);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testGetSimilarProductIdsByProductIdNotFound() throws Exception {

        String productId = "invalid";

        when(productSimilarIdsRequest.getProducts(productId)).thenReturn(null);

        productDetailService.getSimilarProductIdsByProductId(productId);
    }

    @Test
    public void testGetProductDetailByProductId() throws Exception {
        ArrayList<String> productIds = new ArrayList<String>();
        productIds.add("12345");
        ArrayList<ProductDetail> productList = new ArrayList<ProductDetail>();
        productList.add(new ProductDetail());

        when(productDetailRequest.getProduct("12345")).thenReturn(new ProductDetail());

        SimilarProducts response = productDetailService.getProductDetailByProductId(productIds);

        assertNotNull(response);
        assertNotNull(response.getProductModelList());
        assertEquals(1, response.getProductModelList().size());
    }

    @Test(expected = RuntimeException.class)
    public void testGetProductDetailByProductIdException() throws Exception {
        ArrayList<String> productIds = new ArrayList<String>();
        productIds.add("invalid");

        when(productDetailRequest.getProduct("invalid")).thenThrow(new RuntimeException());

        productDetailService.getProductDetailByProductId(productIds);
    }
}
