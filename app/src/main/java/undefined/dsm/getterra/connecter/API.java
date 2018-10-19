package undefined.dsm.getterra.connecter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface API {

    @GET("Solve/solve/{clubId}")
    Call<QuizRetrofit> getQuizAPI (
            @Header("Authorization") String header,
            @Query("problemType") int problemType,
            @Query("problemId")  String problemId,
            @Query("question") String question,
            @Query("choices") String[] choices
    );
    /*@GET("Solve/get_solve__clubId_")
    Call<QuizRetrofit> getProblemIdAPI(
            @Query("problemId") String problemId
    );
    @GET("Solve/get_solve__clubId_")
    Call<QuizRetrofit>getTypeAPI(
            @Query("problemType") int problemType
    );
    @GET("Solve/get_solve__clubId_")
    Call<QuizRetrofit>getQuestionAPI(
            @Query("question") String question
    );*/
    /*@GET("Solve/get_solve__clubId_")
    Call<QuizRetrofit>getSelectAPI(
            @QueryMap("choices (4지선다만)") String[] choices
    )*/
}
