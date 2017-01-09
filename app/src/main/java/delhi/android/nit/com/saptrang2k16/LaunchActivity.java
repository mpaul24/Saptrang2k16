package delhi.android.nit.com.saptrang2k16;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LaunchActivity extends AppCompatActivity {

    RecyclerView rvMain;
    ImageView imgMain;
    Integer[] integer = {R.drawable.eents,R.drawable.loation,R.drawable.gallery,R.drawable.sponsor,R.drawable.about_us,R.drawable.contact_us};
    CollapsingToolbarLayout collapsingTool;
    LoginButton loginButton;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    String fname,lname,email;
    static Uri uri;
    GraphRequest graphRequest;
    AccessToken accessToken;
    boolean onCreatecalled = false;
    boolean registrationNotDone = true;
    int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_launch);


        AppEventsLogger.activateApp(this);

        collapsingTool = (CollapsingToolbarLayout) findViewById(R.id.collapsingTool);
        collapsingTool.setTitleEnabled(false);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        registrationNotDone = sharedPreferences.getBoolean("registrationNotDone",true);

        rvMain = (RecyclerView) findViewById(R.id.rvMain);
        imgMain = (ImageView) findViewById(R.id.imgMain);

        Glide.with(this)
                .load(R.drawable.abc)
                .centerCrop()
                .crossFade()
                .into(imgMain);

        rvMain.setLayoutManager(new GridLayoutManager(getBaseContext(),1));
        rvMain.setAdapter(new Adapter());


        //callbackManager = CallbackManager.Factory.create();
        //loginButton = (LoginButton) findViewById(R.id.login_button);
        //loginButton.setReadPermissions("email");
        /*loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                onCreatecalled = true;
                accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                if(profile!=null){
                    fname = profile.getFirstName();
                    lname = profile.getLastName();
                    uri = profile.getProfilePictureUri(100,100);

                }

                graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            email = object.getString("email");
                            //Log.e("ManojitM","onCreate    "+email+" "+fname+" "+lname);
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email",email);
                            editor.putString("fname",fname);
                            editor.putString("lname",lname);
                            Log.i("myname",fname);
                            Log.i("mylastname",lname);
                            Log.i("Email",email);

                            editor.putBoolean("registrationNotDone",false);
                            editor.apply();
                            String name = fname+" "+lname;
                            new Background(LaunchActivity.this).execute(name,email);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if(profile!=null){
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "email");
                    graphRequest.setParameters(parameters);
                    graphRequest.executeAsync();
                }

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LaunchActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });*/

        /*accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                //accessToken = currentAccessToken;
                if(currentAccessToken == null){
                    SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.clear();
                    editor.commit();
                }
            }
        };

        accessTokenTracker.startTracking();*/

    }


    @Override
    protected void onResume() {
        super.onResume();
        //Log.e("Manojitm", "onResume    " + onCreatecalled);
        /*if (!onCreatecalled) {
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                fname = profile.getFirstName();
                lname = profile.getLastName();
                uri = profile.getProfilePictureUri(100, 100);
            }
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            email = sharedPreferences.getString("email", "un-known");
            registrationNotDone = sharedPreferences.getBoolean("registrationNotDone",true);

            onCreatecalled = false;
        }*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void printKey(){
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo(
                    "activityanim.android.example.com.facebookintegration",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                //String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    private class Adapter extends RecyclerView.Adapter<Holder>{

        public Adapter() {
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view,parent,false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            //holder.menuImage.setImageResource(integer[position]);

            Glide.with(getBaseContext())
                    .load(integer[2*position])
                    .centerCrop()
                    .crossFade()
                    .into(holder.menuImage);

            Glide.with(getBaseContext())
                    .load(integer[2*position+1])
                    .centerCrop()
                    .crossFade()
                    .into(holder.menuImage1);

        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }


    private class Holder extends RecyclerView.ViewHolder{
        ImageView menuImage,menuImage1;
        public Holder(final View itemView) {
            super(itemView);
            menuImage = (ImageView) itemView.findViewById(R.id.imgMenu);
            menuImage1 = (ImageView) itemView.findViewById(R.id.imgMenu1);

            menuImage.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = rvMain.getChildAdapterPosition(itemView);
                            switch (position){
                                case 0:
                                    startActivity(new Intent(LaunchActivity.this,Event.class));
                                    break;
                                case 1:
                                    startActivity(new Intent(LaunchActivity.this,instagram.class));
                                    break;
                                case 2:
                                    startActivity(new Intent(LaunchActivity.this,About_Us.class));
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                            }
                        }
                    }
            );

            menuImage1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = rvMain.getChildAdapterPosition(itemView);
                            switch (position){
                                case 0:
                                    startActivity(new Intent(LaunchActivity.this,MappNITD.class));
                                    break;
                                case 1:
                                    startActivity(new Intent(LaunchActivity.this,Sponsor.class));
                                    break;
                                case 2:
                                    startActivity(new Intent(LaunchActivity.this,Contact_Us.class));
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                            }
                        }
                    }
            );
        }
    }

    private class Background extends AsyncTask<String,Void,String> {
        String registration_link = "http://saptrangnitd.com/beta1/ap/saptrang_app_login_check.php";
       // String registration_link = "http://manojit.tk/images/login_check.php";
        Context context;
        AlertDialog.Builder builder;
        Activity activity;
        ProgressDialog progressDialog;

        public Background(Context context) {
            this.context = context;
            activity = (Activity) context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(activity);
            progressDialog.setTitle("Working!!");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(registration_link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                for(int i=0;i<params.length;i++){
                    Log.e("Manojit",params[i]+"\n");
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(params[0],"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(params[1],"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line+"\n");
                }
                connection.disconnect();
                //Log.e("Manoit",stringBuilder.toString()+"");
                Thread.sleep(3000);
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                progressDialog.dismiss();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                JSONObject JO = jsonArray.getJSONObject(0);
                String code = JO.getString("code");
                String message = JO.getString("message");
                Log.e("Manojit","CODE:"+code);
                if(code.equals("true")){
                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("Successfull!!");
                    builder.setMessage(message);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();


                        }
                    });
                    builder.setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    Toast.makeText(context, "Register Completely", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LaunchActivity.this, Registration.class));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
