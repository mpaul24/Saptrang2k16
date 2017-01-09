package delhi.android.nit.com.saptrang2k16;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Manojit Paul on 11/5/2016.
 */

public class OkHttpManager {

    public static String getData(String uri){

        URL url = null;
        try {
            url = new URL(uri);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}