package undefined.dsm.getterra.connecter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import undefined.dsm.getterra.Model;

public class Connector {
    static String booth;

    public static String getBreakfast() {
        return booth;
    }

    public void init(String date) {
        String baseUrl = "http://ec2.istruly.sexy:1234/";          //값을 가져올 baseUrl 설정
        Retrofit client = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();   //Retrofit client 객체 생성

        API mTestService = client.create(API.class);  //ApiService 가져옴

        Call<Model> call = mTestService.getAppinfo(date);           //ApiService 에 받아온 date(날짜)양식 입력
        Log.v("date", date);                                 //날짜 Log.v로 확인
        final String stringDate = date;

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d("Success date", stringDate);

                if (response.isSuccessful()) {                      //정보를 성공적으로 불러왔다면
                    Log.v("success", "성공");
                    Model body = response.body();                   //받아온 정보를 model 로 body 에 가져옴
                    booth = body.getBooth().toString();
                    booth = booth.replace("[",  "");        //List 타입으로 인해 남아있던 []삭제
                    booth = booth.replace("]",  "");

                }else{
                    Log.e("errorBody()", response.errorBody().toString());  //error 가 났다면 error 가 난 부분 Log.e표시
                }
            }


            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e("Failure date", stringDate);

                Log.e("오류 발생", t.getMessage());             //오류나면 메시지 Log.e에서 확인
                t.printStackTrace();
            }
        });
    }
}
