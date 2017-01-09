package delhi.android.nit.com.saptrang2k16;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manojit Paul on 11/5/2016.
 */

public class JSONParsedData {

    public static List<Images> getParsedData(String jsonData) {
        try {
            List<Images> setofFlowers = new ArrayList<>();
            JSONArray bundleFlowers = new JSONArray(jsonData);
            for (int i = 0; i < bundleFlowers.length(); i++) {
                JSONObject jsonObject = bundleFlowers.getJSONObject(i);
                //Images flowers = new Images();
                //flowers.setImageLink(jsonObject.getString("photo"));
                //flowers.setImage(jsonObject.getString("price"));
                //setofFlowers.add(flowers);
            }
            return setofFlowers;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}