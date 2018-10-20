package undefined.dsm.getterra.connecter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    @GET("team")
    Call<JsonArray> getTeam(@Header("Authorization") String token);

    @POST("team")
    Call<Void> postTeam(@Header("Authorization") String token, @Query("team") String team);

    @GET("map/android")
    Call<JsonArray> getMap(@Header("Authorization")String token);

    @POST("signup")
    @Headers( "Content-Type: application/json" )
    Call <Void> postSignin(
            @Body JsonObject jsonObject);

    @POST("auth")
    @Headers( "Content-Type: application/json" )
    Call <ItemLogin> postLogin(
            @Body JsonObject jsonObject);
}
