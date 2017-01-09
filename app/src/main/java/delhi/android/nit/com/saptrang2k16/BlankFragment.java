package delhi.android.nit.com.saptrang2k16;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int[] images = {R.drawable.bookmyshow,R.drawable.flipkart,R.drawable.myntra,R.drawable.pvr};

    String[] title = {"BookMyShow","Flikart","Myntra","PVR"};

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;


    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(int param1, int param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    ImageView imageView;
    TextView sponsorDESP,sponsorTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        imageView = (ImageView) v.findViewById(R.id.imgView);
        sponsorDESP = (TextView) v.findViewById(R.id.sponsorDESP);
        sponsorTitle = (TextView) v.findViewById(R.id.sponsorTitle);

        imageView.setImageResource(images[mParam1]);
        sponsorTitle.setText(title[mParam1]);
        return v;
    }



}

