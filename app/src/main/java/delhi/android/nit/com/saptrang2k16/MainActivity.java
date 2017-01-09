package delhi.android.nit.com.saptrang2k16;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.listener.OnStateChangeListener;

public class MainActivity extends AppCompatActivity {

    public String path = "M379.7,284.4l-0.7-1.7c-5.8-25-25.6-39.7-41.6-57.2c-8.6-9.4-17.1-19-25.3-28.7c-1.3-1.5-2.3-3.3-3.1-5.2\n" +
            "\t\t\tc-0.7-0.8-1.2-1.6-1.7-2.6c-1.1-1.8-1.9-3.7-2.7-5.5c-1.3-3.3-2.6-7.3-2.1-11c-0.1-0.4-0.1-0.8-0.1-1.2c0-10.4,1.1-21.4,4.6-31.1\n" +
            "\t\t\tc7-19.2,21.7-33.1,36.7-46.4c3-2.7,6-5.4,9-8.1c-0.7-0.7-1.4-1.4-2-2.2c-7.2,5.8-14.4,11.6-21.7,17.3c-7.2,5.6-14.6,11.1-22.4,17\n" +
            "\t\t\tc0,0,0,0,0,0c-0.4,0.3-0.8,0.5-1.2,0.8c-0.3,0.3-0.7,0.5-1,0.8c-0.9,0.9-1.8,1.7-2.8,2.5c-0.6,0.6-1.3,1.1-2,1.6\n" +
            "\t\t\tc-0.1,0.2-0.3,0.3-0.4,0.5c-1.6,1.5-2.3,2.3-2.3,2.3c0,0,0,0,0,0c0,0,0,0,0,0c-8.6,10.8-18.6,20.8-25.2,32.7\n" +
            "\t\t\tc-16.5,30-12.5,57.8,10.7,83.9c15.7,17.7,32.1,34.8,47.8,52.6c8.4,9.5,11.5,21.2,8.9,33.6c-1.3,6.4-5.2,12.3-8.7,20.2\n" +
            "\t\t\tc10.5-9.3,19.5-17.5,28.6-25.5c9.9-8.7,16.3-19.4,18.7-32.5c0.2-1.3,1.2-2.4,2.2-3.5c-0.2-0.7-0.2-1.3-0.2-2\n" +
            "\t\t\tC379.9,285.1,379.8,284.8,379.7,284.4z"
            + "M311,684.1c0.9-3.2,1.8-6.4,2.7-9.6c6.1-20.3,0.6-38.6-10.5-55.4c-16-24.2-32.8-47.8-49-71.9\n" +
            "\t\t\tC242.1,529,229.5,511,225.3,489c-4.2-22.4-3-44.8,9.4-64.2c22.6-35.4,46.8-69.8,70.5-104.4c5.6-8.3,11.9-16.1,17.9-24.1\n" +
            "\t\t\tc0.6,0.4,1.2,0.7,1.9,1.1c-1.4,2.4-2.7,5-4.3,7.3c-13.3,18.6-27.3,36.7-39.9,55.9c-15.7,24-29.5,48.8-25.7,79.4\n" +
            "\t\t\tc2.7,21.7,12.8,39.9,24.6,57.4c16.9,24.9,34.8,49.1,51.6,74.1c9.5,14.2,18.1,29,16.8,47.3c-0.9,12.8-4.2,24.5-12.1,34.9\n" +
            "\t\t\tc-6.6,8.7-12.8,17.7-19.2,26.5c-1.3,1.8-2.9,3.3-4.3,5C312,684.7,311.5,684.4,311,684.1z"
            + "M407.9,327.9c-7.9-10.7-14.8-22-22.2-33.1c-0.3-0.5-0.5-1.1-0.8-1.6c-0.2-0.1-0.3-0.1-0.4-0.2c-1,1.4-2.7,3-2.4,4\n" +
            "\t\t\tc3.3,9.6-0.4,17.6-6.4,24.2c-6.5,7.3-14.1,13.7-21.3,20.3c-17.8,16.4-37.3,31.3-53.1,49.4c-31,35.4-33,65.1-2,99.3\n" +
            "\t\t\tc23.8,26.2,48.7,51.4,73.1,77c0.6,0.6,1.6,0.7,2.5,1.1c-0.6-3.1-2.3-4.9-3.9-6.7c-12.3-13.2-25-26.2-36.9-39.8\n" +
            "\t\t\tc-24.2-27.5-25.3-57.5-0.4-88.4c15-18.6,34.9-33.3,52.3-50.1c8-7.7,16.3-15.4,22.9-24.3C416,349.1,415.7,338.4,407.9,327.9z";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FillableLoader fillableLoader = (FillableLoader) findViewById(R.id.fillableLoader);
        fillableLoader.setSvgPath(path);
        fillableLoader.start();
        fillableLoader.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == State.FINISHED) {
                    startActivity(new Intent(MainActivity.this, LaunchActivity.class));
                    finish();
                }
            }
        });
    }
}
