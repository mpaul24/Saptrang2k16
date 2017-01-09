package delhi.android.nit.com.saptrang2k16;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Contact_Us extends AppCompatActivity {
    RecyclerView contactRV;
    String[] name = {"Manojit","Akaash","Yash"};
    String[] post = {"Android Developer","Android Developer","Android Developer"};
    String[] number = {"9650419350","8130113842","9717271101"};
    String[] mail = {"manojitp4@gmail.com@asd.com","asdhadkj@asf.com","kdhakdj@sda.com"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        contactRV = (RecyclerView) findViewById(R.id.contactRV);
        contactRV.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        contactRV.setAdapter(new Adapter());


    }

    private class Adapter extends RecyclerView.Adapter<Holder>{

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_contact_us,parent,false));
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Picasso.with(getBaseContext())
                    .load(R.drawable.asdfgh)
                    .into(holder.imageView);
            holder.textView1.setText(name[(position)%3]);
            holder.textView2.setText(post[(position)%3]);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }


    private class Holder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView1,textView2;
        ImageButton imageButton1,imageButton2;

        public Holder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.people_image);
            textView1 = (TextView) itemView.findViewById(R.id.people_name);
            textView2 = (TextView) itemView.findViewById(R.id.people_post);
            imageButton1 = (ImageButton) itemView.findViewById(R.id.people_call);
            imageButton2 = (ImageButton) itemView.findViewById(R.id.people_mail);

            imageButton1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int n = contactRV.getChildAdapterPosition(itemView);
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number[(n)%3]));
                            startActivity(intent);
                        }
                    }
            );

            imageButton2.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int n = contactRV.getChildAdapterPosition(itemView);
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto",mail[(n%3)],null));
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "FeedBack");
                            //emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                            startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        }
                    }
            );

        }
    }

}
