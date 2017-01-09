package delhi.android.nit.com.saptrang2k16;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Manojit Paul on 11/5/2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh(){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("token", FirebaseInstanceId.getInstance().getToken());
        Log.e("Manojit",FirebaseInstanceId.getInstance().getToken());
    }
}
