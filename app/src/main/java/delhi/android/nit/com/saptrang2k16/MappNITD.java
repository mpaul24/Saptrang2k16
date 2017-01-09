package delhi.android.nit.com.saptrang2k16;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MappNITD extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapp_nitd);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(28.843111, 77.104945);
        LatLng bank=new LatLng(28.842284, 77.105452);
        LatLng park=new LatLng(28.842376, 77.105665);
        LatLng hostel=new LatLng(28.842723, 77.105840);
        LatLng playing=new LatLng(28.842791, 77.104437);
        LatLng football=new LatLng(28.842615, 77.105414);
        LatLng stage=new LatLng(28.843243, 77.105390);
        LatLng audi=new LatLng(28.843389, 77.105195);
        LatLng guest=new LatLng(28.843813, 77.106152);
        LatLng academics=new LatLng(28.842975, 77.105066);
        mMap.addMarker(new MarkerOptions().position(academics).title("ACADEMIC SECTION")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        mMap.addMarker(new MarkerOptions().position(guest).title("GUEST HOUSE")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        mMap.addMarker(new MarkerOptions().position(audi).title("AUDITORIUM")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        mMap.addMarker(new MarkerOptions().position(stage).title("ECHO STAGE")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        mMap.addMarker(new MarkerOptions().position(football).title("FOOTBALL GROUND")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        mMap.addMarker(new MarkerOptions().position(playing).title("PLAYING GROUNDS")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        mMap.addMarker(new MarkerOptions().position(hostel).title("IAMR HOSTEL")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        mMap.addMarker(new MarkerOptions().position(park).title("PARKING")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(new MarkerOptions().position(sydney).title("NIT DELHI"));
        mMap.addMarker(new MarkerOptions().position(bank).title("ATM")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.2f));

    }
}
