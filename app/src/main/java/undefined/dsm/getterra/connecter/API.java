package undefined.dsm.getterra.connecter;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @GET("team")
    Call<JsonArray> getTeam(@Header("Authorization") String token);

    @POST("team")
    Call<Void> postTeam(@Header("Authorization") String token, @Query("team") String team);
}
