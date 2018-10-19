package undefined.dsm.getterra.connecter;

import com.google.gson.JsonObject;
import com.justgo.Connecter.Connecter;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    @POST("signup")
    @Headers( "Content-Type: application/json" )
    Call <Void> postSignin(/*
            @Field("id") String id,
            @Field("email") String email,
            @Field("password")String password*/
            @Body JsonObject jsonObject);
    @POST("auth")
    Call <Void> postLogin(/*
            @Field("id") String id,
            @Field("password")String password*/
            @Body JsonObject jsonObject);
}
