package delhi.android.nit.com.saptrang2k16;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class Registration extends AppCompatActivity {

    ImageView imageView;
    boolean flag = false;
    String email ,lname,fname;
    EditText phone,college,city;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        imageView = (ImageView) findViewById(R.id.background);
        city = (EditText) findViewById(R.id.editText);
        college = (EditText) findViewById(R.id.editText2);
        phone = (EditText) findViewById(R.id.editText3);
        Glide.with(getBaseContext())
                .load(R.drawable.background2)
                .crossFade()
                .centerCrop()
                .into(imageView);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        email = sharedPreferences.getString("email","un-known");
        fname = sharedPreferences.getString("fname","un-known");
        lname = sharedPreferences.getString("lname","un-known");
        name = fname+" "+lname;
        ImageView imageView = (ImageView) findViewById(R.id.profile);
        Picasso.with(getBaseContext())
                .load(LaunchActivity.uri)
                .into(imageView);
        TextView textView = (TextView) findViewById(R.id.hello);
        textView.setText("Hello\n"+fname+" "+lname+"\n"+email);

    }

    public void submit(View view)
    {
        new Background(Registration.this).execute(name,"21",phone.getText().toString(),email,
                college.getText().toString(),city.getText().toString());

    }

    private class Background extends AsyncTask<String,Void,String> {

        String registration_link = "http://saptrangnitd.com/beta1/ap/saptrang_app_register.php";
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
                        URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(params[1],"UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(params[2],"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(params[3],"UTF-8")+"&"+
                        URLEncoder.encode("college","UTF-8")+"="+URLEncoder.encode(params[4],"UTF-8")+"&"+
                        URLEncoder.encode("city","UTF-8")+"="+URLEncoder.encode(params[5],"UTF-8");

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

                if(code.equals("true")){
                    Log.e("Manojit",""+code);
                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("Successfull!!");
                    builder.setMessage(message);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            activity.finish();

                        }
                    });
                    builder.setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("UnSuccessfull!!");
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

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
