package undefined.dsm.getterra.connecter;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface API {
    @GET("Solve/solve/{boothName}")
    Call<GetSolve> getSolve (
            @Header("") String Authorization,
            @Path("boothName") String boothName
            );
    @POST("Solve/solve/{boothCode}")
    Call<PostSolve> postSolve(
            @Header("") String Authorization,
        @Path("boothCode") String boothCode,
        @Body problemIdAnswer PA
    );
}
