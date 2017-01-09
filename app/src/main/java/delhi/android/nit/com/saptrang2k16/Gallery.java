package delhi.android.nit.com.saptrang2k16;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Gallery extends AppCompatActivity {

    RecyclerView galleryRV;
    List<Images> setofFlowers;
    Adapter adapter;
    private static String IMAGE_BASE_URL = "http://services.hanselandpetal.com/photos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        galleryRV = (RecyclerView) findViewById(R.id.galleryRV);
        galleryRV.setLayoutManager(new GridLayoutManager(getBaseContext(),1));

        if (isOnline()) {
            new MyTask().execute("http://saptrang.net16.net/");
            //new MyTask().execute("http://saptrang2016.comlu.com/index.php");
        } else {
            Toast.makeText(Gallery.this, "Network not available!", Toast.LENGTH_SHORT).show();
        }

        //galleryRV.setAdapter(new Adapter());
    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    public class MyTask extends AsyncTask<String, Void, List<Images>> {

        String content;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Images> doInBackground(String... params) {
            content = OkHttpManager.getData(params[0]);
            Log.e("Manojit",""+content);
            //setofFlowers = JSONParsedData.getParsedData(content);
            return setofFlowers;
            //return null;
        }

        @Override
        protected void onPostExecute(List<Images> flowersList) {

            if (setofFlowers != null) {
                adapter = new Adapter();
                galleryRV.setAdapter(adapter);
            } else {
                Toast.makeText(Gallery.this, "setofFlower is null", Toast.LENGTH_SHORT).show();
            }

        }
    }





    private class Adapter extends RecyclerView.Adapter<Holder>{

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_gallery,parent,false));
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Log.e("ManojitM",""+setofFlowers.size());
            if((3*position+2)<setofFlowers.size()) {
                Images image1 = setofFlowers.get(3*position);
                Images image2 = setofFlowers.get(3*position+1);
                Images image3 = setofFlowers.get(3*position+2);
                Log.e("ManojitM",""+3*position+" "+(3*position+1)+" "+(3*position+2));

                try {
                    /*Bitmap bitmap1 = Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image1.getImageLink())
                            .get();
                    image1.setImage(bitmap1);
                    holder.image1.setImageBitmap(bitmap1);

                    Bitmap bitmap2 = Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image2.getImageLink())
                            .get();
                    image2.setImage(bitmap2);
                    holder.image2.setImageBitmap(bitmap2);

                    Bitmap bitmap3 = Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image3.getImageLink())
                            .get();
                    image3.setImage(bitmap3);
                    holder.image3.setImageBitmap(bitmap3);*/
                    /*Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image1.getImageLink())
                            .into(holder.image1);
                    Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image2.getImageLink())
                            .into(holder.image2);
                    Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image3.getImageLink())
                            .into(holder.image3);*/
                    holder.image2.setVisibility(View.VISIBLE);
                    holder.image3.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else if((3*position+1)<setofFlowers.size()){
                Images image1 = setofFlowers.get(3*position);
                Images image2 = setofFlowers.get(3*position+1);
                Log.e("ManojitM",""+3*position+" "+(3*position+1));
                try {
                    /*Bitmap bitmap1 = Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image1.getImageLink())
                            .get();
                    image1.setImage(bitmap1);
                    holder.image1.setImageBitmap(bitmap1);

                    Bitmap bitmap2 = Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image2.getImageLink())
                            .get();
                    image2.setImage(bitmap2);
                    holder.image2.setImageBitmap(bitmap2);*/
                    /*Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image1.getImageLink())
                            .into(holder.image1);
                    Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image2.getImageLink())
                            .into(holder.image2);*/
                    holder.image2.setVisibility(View.VISIBLE);
                    holder.image3.setVisibility(View.GONE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                Images image1 = setofFlowers.get(3*position);
                Log.e("ManojitM",""+3*position);
                try {
                    /*Bitmap bitmap1 = Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image1.getImageLink())
                            .get();
                    image1.setImage(bitmap1);
                    holder.image1.setImageBitmap(bitmap1);*/
                    /*Picasso.with(getBaseContext())
                            .load(IMAGE_BASE_URL+image1.getImageLink())
                            .into(holder.image1);*/
                    holder.image2.setVisibility(View.GONE);
                    holder.image3.setVisibility(View.GONE);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }

        @Override
        public int getItemCount() {
            if(setofFlowers==null){
                return 0;
            }
            if(setofFlowers.size()%3 == 0){
                return (setofFlowers.size()/3);
            }else {
                return ((setofFlowers.size()/3) + 1);
            }
        }
    }

    private class Holder extends RecyclerView.ViewHolder{

        ImageView image1,image2,image3;
        public Holder(View itemView) {
            super(itemView);
            image1 = (ImageView) itemView.findViewById(R.id.image1);
            image2 = (ImageView) itemView.findViewById(R.id.image2);
            image3 = (ImageView) itemView.findViewById(R.id.image3);
        }
    }

}

