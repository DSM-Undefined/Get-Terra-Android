package undefined.dsm.getterra.connecter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import undefined.dsm.getterra.Model;

public interface API {
        @GET("/map/android")
        Call<Model> getAppinfo(@Path("user") String user);
}
