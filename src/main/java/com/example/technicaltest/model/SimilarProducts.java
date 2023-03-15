package com.example.technicaltest.model;

import java.util.ArrayList;
import java.util.List;

public class SimilarProducts {
    public SimilarProducts(ArrayList<ProductDetail> productList) {
        this.productDetailList = productList;
    }

    public SimilarProducts() {
    }

    public List<ProductDetail> getProductModelList() {
        return productDetailList;
    }

    public void setProductModelList(List<ProductDetail> productDetailList) {
        this.productDetailList = productDetailList;
    }

    public void addProductModelList(ProductDetail productDetail) {
        this.productDetailList.add(productDetail);
    }

    public List<ProductDetail> productDetailList;
}
