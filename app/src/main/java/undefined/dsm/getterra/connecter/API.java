package undefined.dsm.getterra.connecter;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
        @GET("/map/android")
        Call<List<Repo>> listRepos(@Path("user") String user);
    }
}
