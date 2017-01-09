package delhi.android.nit.com.saptrang2k16;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class Sponsor extends AppCompatActivity {

    ViewPager viewPager;
    ImageButton next,prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.vp);
        viewPager.setAdapter(new Adpater(getSupportFragmentManager()));
        next = (ImageButton) findViewById(R.id.next);
        prev = (ImageButton) findViewById(R.id.prev);
        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a = viewPager.getCurrentItem();
                        viewPager.setCurrentItem(a+1);
                    }
                }
        );

        prev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a = viewPager.getCurrentItem();
                        viewPager.setCurrentItem(a-1);
                    }
                }
        );
    }

    private class Adpater extends FragmentPagerAdapter {

        public Adpater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            BlankFragment blankFragment = BlankFragment.newInstance(position,0);
            return blankFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
