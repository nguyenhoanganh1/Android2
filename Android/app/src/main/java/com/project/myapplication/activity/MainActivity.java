package com.project.myapplication.activity;

import static com.project.myapplication.RetrofitFactory.RetrofitClient.getRetrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.project.myapplication.R;
import com.project.myapplication.adaper.ProductAdapter;
import com.project.myapplication.model.ProductModel;
import com.project.myapplication.repository.IProductDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    String url = "http://192.168.1.4:8085/api/product/index";
    TextView tv_info;
    Button btnMoveLogin;

    ProductAdapter productAdapter;
    ListView lvAdvance;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        lvAdvance = findViewById(R.id.lvAdvance);
        productAdapter = new ProductAdapter(MainActivity.this, R.layout.product_layout);

        tv_info = findViewById(R.id.tv_info);
        btnMoveLogin = findViewById(R.id.btn_moveLogin);
        btnMoveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        getAllProduct();

    }


    public void getAllProduct(){
        IProductDAO pDAO = getRetrofit().create(IProductDAO.class);
        Call<ProductModel> call = pDAO.getAllProduct();
        call.enqueue(new Callback<ProductModel>() {

            @Override
            public void onResponse(Call<ProductModel> call, retrofit2.Response<ProductModel> response) {
                List<ProductModel.Data> data =  response.body().getData();
                tv_info.setText(data.get(0).getName());
                for (ProductModel.Data dt : data) {
                    productAdapter.add(dt);
                }
                Toast.makeText(MainActivity.this, "Loading Data", Toast.LENGTH_SHORT).show();
                productAdapter.notifyDataSetChanged();
                lvAdvance.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                tv_info.setText("Lỗi");
            }
        });
    }
}