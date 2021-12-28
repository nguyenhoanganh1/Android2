package com.project.myapplication.repository;

import com.project.myapplication.model.ProductInsertModel;
import com.project.myapplication.model.ProductModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IProductDAO {

    @GET("/api/product/index")
    Call<ProductModel> getAllProduct();

    @POST("/api/product")
    Call<ProductInsertModel> insertProduct(@Body ProductInsertModel productInsertModel);
}
