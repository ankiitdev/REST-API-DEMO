package com.example.restapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenActivity extends AppCompatActivity {

    Button bt_login, bt_token;
    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    private static String token;
    String uname,upass;
    EditText et_uname,et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        bt_login = findViewById(R.id.btn_login);
        bt_token = findViewById(R.id.btn_token);
        et_uname = findViewById(R.id.et_username);
        et_pass = findViewById(R.id.et_password);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("") //specify base url here
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 login();
            }
        });

        bt_token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 getToken();
            }
        });
    }



         private void login(){

             uname = et_uname.getText().toString().trim();
             upass = et_pass.getText().toString().trim();
             Login login = new Login(uname,upass);
             Call<User> call = jsonPlaceHolderApi.login(login);

             call.enqueue(new Callback<User>() {
                 @Override
                 public void onResponse(Call<User> call, Response<User> response) {
                     if(response.isSuccessful()) {
                         Toast.makeText(TokenActivity.this, ""+response.body().getToken(), Toast.LENGTH_SHORT).show();
                         token = response.body().getToken();
                         Intent intent = new Intent(TokenActivity.this,UploadActivity.class);
                         intent.putExtra("token",token);
                         startActivity(intent);
                     }
                     else{
                         Toast.makeText(TokenActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                     }
                 }

                 @Override
                 public void onFailure(Call<User> call, Throwable t) {
                     Toast.makeText(TokenActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                 }
             });
         }

         private void getToken(){
             Call<ResponseBody> call = jsonPlaceHolderApi.getToken(token);

             call.enqueue(new Callback<ResponseBody>() {
                 @Override
                 public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                     if(response.isSuccessful()) {
                         try {
                             Toast.makeText(TokenActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();

                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }
                     else{
                         Toast.makeText(TokenActivity.this, "Token invalid!", Toast.LENGTH_SHORT).show();
                     }
                 }

                 @Override
                 public void onFailure(Call<ResponseBody> call, Throwable t) {
                         Toast.makeText(TokenActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                 }
             });
         }
}
