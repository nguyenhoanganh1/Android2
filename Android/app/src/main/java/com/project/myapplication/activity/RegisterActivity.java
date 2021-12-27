package com.project.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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


public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText confirmPassword;
    EditText email;
    EditText phoneNumber;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        confirmPassword = findViewById(R.id.et_confirm_password);
        email = findViewById(R.id.et_mail);
        phoneNumber = findViewById(R.id.et_phone);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String confirmPass = confirmPassword.getText().toString().trim();
                String em = email.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();

                register(user, pass, confirmPass, em, phone);
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void register(String user, String pass, String confirmPass, String em, String phone) {
        Customer cus = Db.getInstance(this).customerDAO().findCustomerByUsername(user);
        if(cus != null) {
            Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_LONG).show();
            HideKeyboard();
        }
        else {
            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmPass)
                    || TextUtils.isEmpty(em)  || TextUtils.isEmpty(phone)) {
                Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                HideKeyboard();
            }
            else if(!pass.equals(confirmPass)){
                Toast.makeText(RegisterActivity.this, "Xác nhận mật khẩu không đúng, vui lòng nhập lại", Toast.LENGTH_LONG).show();
                HideKeyboard();
            }
            else {
                Customer customer = new Customer();
                customer.setUsername(user);
                customer.setPassword(pass);
                customer.setEmail(em);
                customer.setPhoneNumber(phone);

                Db.getInstance(RegisterActivity.this)
                        .customerDAO()
                        .insertCustomer(customer);
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtra("username", user);
            intent.putExtra("password", pass);
            startActivity(intent);
                clearText();
                HideKeyboard();
            }
        }

    }

    public void clearText(){
        username.setText("");
        password.setText("");
        confirmPassword.setText("");
        email.setText("");
        phoneNumber.setText("");
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