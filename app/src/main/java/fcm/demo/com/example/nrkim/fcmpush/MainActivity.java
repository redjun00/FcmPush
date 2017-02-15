package fcm.demo.com.example.nrkim.fcmpush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);

        //news 구독
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        String token = FirebaseInstanceId.getInstance().getToken();
        System.out.println("token=" + token);
        //token 저장 요청
        requestSaveToDb(token);
        Toast.makeText(this, "finished request to save the token.", Toast.LENGTH_SHORT).show();
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private void requestSaveToDb(String token) {
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("token", token);
        }catch (JSONException e){
            e.printStackTrace();
            return;
        }
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("10.0.2.2")
                .port(8080)
                .addPathSegment("register")
                .build();

        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());

        final Request request = new Request.Builder()
                .url(httpUrl)
                .post(requestBody)
                .build();

        Callback callbackAfterRequest = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.print("call=" + call.toString() + ", response=" + response.toString());
            }
        };

        try {
            client.newCall(request).enqueue(callbackAfterRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
