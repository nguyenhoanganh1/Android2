package com.project.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.myapplication.R;
import com.project.myapplication.database.Db;
import com.project.myapplication.entities.Customer;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnRegister;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.button_signin);
        btnRegister = findViewById(R.id.button_signup);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String user = username.getText().toString().trim();
               String pass = password.getText().toString().trim();
               if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
               }
               else {
                   login(user, pass);
               }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login(String username, String password){
        Customer customer = Db.getInstance(LoginActivity.this)
                .customerDAO().findCustomerByUsername(username);
        if(customer.getUsername().equals(username) && customer.getPassword().equals(password)){
            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
            HideKeyboard();
        }
        else{

            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        this.username.setText(username);
        this.password.setText(password);

    }

    public void HideKeyboard(){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}