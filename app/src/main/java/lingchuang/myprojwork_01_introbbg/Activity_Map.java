package lingchuang.myprojwork_01_introbbg;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Activity_Map extends FragmentActivity implements com.google.android.gms.location.LocationListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    LocationManager locationMgr;
    String bestProvider;
    Location myLocation;
    Marker myMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);
        setUpMapIfNeeded();
        locationMgr=(LocationManager)getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();	//資訊提供者選取標準
        bestProvider = locationMgr.getBestProvider(criteria, true);
        myLocation = locationMgr.getLastKnownLocation(bestProvider);
        if (!locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(Activity_Map.this,"您的GPS未啟用!\n建議開啟GPS定位後, 重新進入本頁.", Toast.LENGTH_LONG).show();
        }
        LatLng latLng=new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .anchor(0.5f, 0.5f)
                .icon((BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
                .title("目前位置")
                .snippet("緯度:" + myLocation.getLatitude() + ", 經度:" + myLocation.getLongitude()));
        moveMap(latLng);
    }



    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {

    }


    private void moveMap(LatLng place) {
        // 建立地圖攝影機的位置物件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(15)
                        .build();

        // 使用動畫的效果移動地圖
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
