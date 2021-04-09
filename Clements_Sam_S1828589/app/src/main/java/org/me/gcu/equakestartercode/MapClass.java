//Clements_Sam_S1828589
package org.me.gcu.equakestartercode;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapClass extends FragmentActivity implements OnMapReadyCallback , View.OnClickListener {

    private GoogleMap QuakeMap;

    protected void onCreate(Bundle savedinstancestate) {
        super.onCreate(savedinstancestate);
        setContentView(R.layout.map_layout);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
       QuakeMap = googleMap;
        for (int i = 0; i <ItemKeeper.itemGeoLat.size() ;i++)
        {
            LatLng mapPin = new LatLng(Double.valueOf(ItemKeeper.itemGeoLat.get(i)),  (Double.valueOf(ItemKeeper.itemGeoLong.get(i))));
           // LatLng mapPin = new LatLng(40,2);



            if(ItemKeeper.itemMag.get(i) >= 0 && ItemKeeper.itemMag.get(i)<1)
            {
                QuakeMap.addMarker(new MarkerOptions().position(mapPin).title(ItemKeeper.itemTitles.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

            }
            else if (ItemKeeper.itemMag.get(i) >= 1 && ItemKeeper.itemMag.get(i)<2)
            {
                QuakeMap.addMarker(new MarkerOptions().position(mapPin).title(ItemKeeper.itemTitles.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            }

            else if (ItemKeeper.itemMag.get(i) >= 2 && ItemKeeper.itemMag.get(i)<3)
            {
                QuakeMap.addMarker(new MarkerOptions().position(mapPin).title(ItemKeeper.itemTitles.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


            }

            else if (ItemKeeper.itemMag.get(i) >= 3 && ItemKeeper.itemMag.get(i)<4)
            {
                QuakeMap.addMarker(new MarkerOptions().position(mapPin).title(ItemKeeper.itemTitles.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

            }
        }

    }



}
