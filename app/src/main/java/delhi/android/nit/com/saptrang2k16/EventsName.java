package delhi.android.nit.com.saptrang2k16;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsName extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private int mParam1;
    //String IMAGE_LINK = "http://saptrangnitd.com/beta1/ap/images/";


    public static EventsName newInstance(int param1) {

        EventsName fragment = new EventsName();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public EventsName() {
        // Required empty public constructor
    }
    RecyclerView rvEvent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(500);
            explode.excludeTarget(android.R.id.navigationBarBackground,true);
            getActivity().getWindow().setEnterTransition(new Slide(Gravity.TOP));
            getActivity().getWindow().setExitTransition(new Fade());
            Transition transition1 =  TransitionInflater.from(getActivity()).inflateTransition(R.transition.transition);
            transition1.excludeTarget(android.R.id.navigationBarBackground,true);
            //transition1.setStartDelay(1000);
            getActivity().getWindow().setSharedElementExitTransition(transition1);
            getActivity().getWindow().setReenterTransition(new Fade());
            /*Explode explode1 = new Explode();
            explode1.setDuration(700);
            getActivity().getWindow().setExitTransition(explode1);*/
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events_name, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvEvent = (RecyclerView) view.findViewById(R.id.rvEvent);
        rvEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvEvent.setAdapter(new Adater());

    }

    private class Adater extends RecyclerView.Adapter<Holder>{

        public Adater() {
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view1,parent,false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            if(mParam1 == 1) {
                holder.textView2.setText(Data.title[position]);
                holder.titleDesp.setText("(" + Data.titleDescription[position] + ")");
                holder.despDesp.setText(Data.description[position]);
                Picasso.with(getContext())
                        .load(Data.images[position])
                        .error(R.drawable.asd)
                        .resize(700,700)
                        .centerCrop()
                        .placeholder(R.drawable.asd)
                        .into(holder.imageView2);
            }else{
                Picasso.with(getContext())
                        .load(R.drawable.asdfgh)
                        .into(holder.imageView2);
            }
            // ViewCompat.setTransitionName(holder.album_image,String.valueOf(position) + "_albumart");
        }

        @Override
        public int getItemCount() {
            if(mParam1 == 1)
                return 16;
            else
                return 5;
        }
    }


    private class Holder extends RecyclerView.ViewHolder{
        ImageView imageView2;
        TextView textView2,titleDesp,despDesp;
        public Holder(final View itemView) {
            super(itemView);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            titleDesp = (TextView) itemView.findViewById(R.id.textView5);
            despDesp = (TextView) itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                            {
                                //imageResId = image[recyclerView1.getChildAdapterPosition(itemView)];
                                //Log.e("Manojit",""+recyclerView1.getChildAdapterPosition(itemView));
                                Intent intent = new Intent(getActivity(),Description.class);
                                intent.putExtra("data",rvEvent.getChildAdapterPosition(itemView));
                                if(mParam1 == 1)
                                    intent.putExtra("eventName",Data.title[rvEvent.getChildAdapterPosition(itemView)]);
                                else
                                    intent.putExtra("eventName","Informal Event");
                                intent.putExtra("mParam1",mParam1);
                                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),imageView2,imageView2.getTransitionName());
                                startActivity(intent,activityOptionsCompat.toBundle());
                            }
                            else{
                                Intent intent = new Intent(getActivity(),Description.class);
                                intent.putExtra("data",rvEvent.getChildAdapterPosition(itemView));
                                intent.putExtra("eventName",Data.title[rvEvent.getChildAdapterPosition(itemView)]);
                                intent.putExtra("mParam1",mParam1);
                                startActivity(intent);
                            }
                        }
                    }
            );
        }
    }

}
