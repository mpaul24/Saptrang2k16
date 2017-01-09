package delhi.android.nit.com.saptrang2k16;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.squareup.picasso.Picasso;

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

public class Description extends AppCompatActivity {

    ImageView image;
    TextView despTitle,despTitleHint,despDesp,despContact;
    boolean flag = false;
    //String email ,lname,fname,college,phone;
    int data,mParam1;
    String eventName;
    //String IMAGE_LINK = "http://saptrangnitd.com/beta1/ap/images/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Transition transition =  TransitionInflater.from(this).inflateTransition(R.transition.transition);
            getWindow().setSharedElementEnterTransition(transition);
            /*Slide slide = new Slide(Gravity.RIGHT);
            slide.setStartDelay(300);
            slide.setDuration(300);*/

            Slide fade1 = new Slide(Gravity.LEFT);
            fade1.excludeTarget(android.R.id.navigationBarBackground,true);
            fade1.excludeTarget(android.R.id.statusBarBackground,true);
            fade1.excludeTarget(R.id.appbar,true);
            fade1.setStartDelay(300);
            fade1.setDuration(300);
            getWindow().setEnterTransition(fade1);

            Fade fade = new Fade();
            fade.setDuration(100);
            fade.excludeTarget(android.R.id.navigationBarBackground,true);
            fade.excludeTarget(android.R.id.statusBarBackground,true);
            getWindow().setReturnTransition(fade);
            //getWindow().setReenterTransition(null);
            //getWindow().setExitTransition(null);

        }

        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = getIntent().getIntExtra("data",0);
        mParam1 = getIntent().getIntExtra("mParam1",1);
        eventName = getIntent().getStringExtra("eventName");
        Log.e("Manojit",eventName);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        flag = sharedPreferences.getBoolean("flagEvent"+data+"-"+mParam1,false);

        if(mParam1  == 1) {
            despTitle = (TextView) findViewById(R.id.despTitle);
            despTitleHint = (TextView) findViewById(R.id.despTitleHint);
            despDesp = (TextView) findViewById(R.id.despDesp);
            despContact = (TextView) findViewById(R.id.despContact);

            despTitle.setText(Data.title[data]);
            despTitleHint.setText("(" + Data.titleDescription[data] + ")");
            despDesp.setText(Data.description[data]);
            despContact.setText(Data.contacts[data]);

            image = (ImageView) findViewById(R.id.image);

            Picasso.with(this)
                    .load(Data.images[data])
                    .resize(700,700)
                    .centerCrop()
                    .error(R.drawable.asd)
                    .into(image);
        }
        else{
            image = (ImageView) findViewById(R.id.image);

            Picasso.with(this)
                    .load(Data.images[data])
                    .resize(700,700)
                    .centerCrop()
                    .error(R.drawable.asd)
                    .into(image);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if(Data.numberOfMember[data] == 0){
            fab.setVisibility(View.GONE);
        }

        /*fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Profile profile = Profile.getCurrentProfile();
                        if(profile == null){
                            AlertDialog alertDialog = new AlertDialog.Builder(Description.this)
                                    .setTitle("ERROR")
                                    .setMessage("Register with Facebook!!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setCancelable(false)
                                    .create();
                            alertDialog.show();
                        }
                        else{
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                            String name = sharedPreferences.getString("fname","") + " " + sharedPreferences.getString("lname","");
                            String email = sharedPreferences.getString("email","");
                            new BackgroundCheck(Description.this).execute(name,email);
                        }
                    }
                }
        );*/

        /*new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1100);
                            Dialog dialog = new Dialog();
                            dialog.show(getFragmentManager(),"dialog");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();*/
        //tvDescription = (TextView) findViewById(R.id.tvDescription);
        //tvDescription.setText("Dreaming of becoming an entrepreneur? If owning your own business is your goal, the good news is, you can achieve it with a great idea and some hard work. But coming up with a stellar business idea that's also practical isn't always easy. If you're not sure where to start, here are 24 small business ideas to inspire you on your path to entrepreneurial success. - See more at: http://www.businessnewsdaily.com/1646-great-business-ideas-2012.html#sthash.Rws8TqMc.dpuf");


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class BackgroundCheck extends AsyncTask<String,Void,String> {
        String registration_link = "http://saptrangnitd.com/beta1/ap/saptrang_app_login_check.php";
        Context context;
        AlertDialog.Builder builder;
        Activity activity;
        ProgressDialog progressDialog;

        public BackgroundCheck(Context context) {
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
                    Intent intent = new Intent(Description.this,EventRegistration.class);
                    intent.putExtra("data",data);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(context, "Register Completely", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Description.this, Registration.class));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
