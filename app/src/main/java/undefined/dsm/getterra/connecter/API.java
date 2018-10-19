package undefined.dsm.getterra.connecter;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface API {
    @GET("Solve/solve/{boothName}")
    Call<GetSolve> getSolve (
            @Path("boothName") String boothName
            );
    @POST("Solve/solve/{boothName}")
    Call<PostSolve> postSolve(
        @Path("boothCode") String boothCode,
        @Body String problemId,
        @Body String answer
    );
}
