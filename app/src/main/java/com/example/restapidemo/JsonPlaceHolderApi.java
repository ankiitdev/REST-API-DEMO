package com.example.restapidemo;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @POST("")
    Call<User> login(@Body Login login);

    @GET("")
    Call<ResponseBody> getToken(@Header("Authorization") String authToken);

    @Multipart
    @POST("")
    Call<User> uploadPhoto(
      @Header("Authorization") String token,
      @Part MultipartBody.Part photo
    );
}
