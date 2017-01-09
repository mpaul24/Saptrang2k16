package delhi.android.nit.com.saptrang2k16;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EventRegistration extends AppCompatActivity {

    int item;
    RecyclerView recyclerView;
    Map<Integer,String> map = new HashMap<Integer, String>();EditText editText;
    ArrayList<String> member = new ArrayList<>();
    String tableName;
    String teamName = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        int position = getIntent().getIntExtra("data",0);
        item = Data.numberOfMember[position];
        tableName = Data.tableName[position];
        recyclerView = (RecyclerView) findViewById(R.id.eventRegRV);
        recyclerView.setAdapter(new Adapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        editText = (EditText) findViewById(R.id.erET);
        if(item<=1){
            editText.setVisibility(View.GONE);
        }

    }

    void take(View view){
        member.clear();
        if(item>1){
            teamName = editText.getText().toString();
        }
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry mentry = (Map.Entry)iterator.next();
            String value = mentry.getValue().toString();
            member.add(value);
            //Log.e("Manojit",value+"");

        }
        if(member.size() == item ) {
            if(item>1){
                if(teamName.equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("Fill all the fields.")
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
                    new Background(this).execute();
                }
            }
            else {
                new Background(this).execute();
            }
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("ERROR")
                    .setMessage("Fill all the fields.")
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
    }

    private class Adapter extends RecyclerView.Adapter<Holder>{

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.edittext,parent,false));
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return item;
        }
    }

    private class Holder extends RecyclerView.ViewHolder{
        EditText editText;
        public Holder(final View itemView) {
            super(itemView);
            editText = (EditText) itemView.findViewById(R.id.eventReget1team);

            editText.addTextChangedListener(
                    new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            int pos = recyclerView.getChildAdapterPosition(itemView);
                            String input = charSequence.toString();
                            if(input.equals("")){
                                if(map.containsKey(pos)) {
                                    map.remove(pos);
                                }
                            }
                            else {
                                map.put(pos, input);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    }
            );
        }
    }

    private class Background extends AsyncTask<String,Void,String> {

        String registration_link = "http://saptrangnitd.com/beta1/ap/eventRegister.php";
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
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                StringBuilder encoderString = new StringBuilder();
                encoderString.append(URLEncoder.encode("tableName","UTF-8")+"="+URLEncoder.encode(tableName,"UTF-8")+"&");
                encoderString.append(URLEncoder.encode("size","UTF-8")+"="+URLEncoder.encode(String.valueOf(item),"UTF-8")+"&");
                if(item>1){
                    encoderString.append(URLEncoder.encode("teamName","UTF-8")+"="+URLEncoder.encode(teamName,"UTF-8")+"&");
                }
                for(int i=0;i<item;i++){
                    if(i == (item-1)){
                        encoderString.append(URLEncoder.encode("id"+i,"UTF-8")+"="+URLEncoder.encode(member.get(i),"UTF-8"));
                        break;
                    }
                    encoderString.append(URLEncoder.encode("id"+i,"UTF-8")+"="+URLEncoder.encode(member.get(i),"UTF-8")+"&");
                }
                String data = encoderString.toString();

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
