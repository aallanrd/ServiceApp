package com.ardsolutions.newdb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ardsolutions.newdb.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


/**
 * Created by Allan on 19/03/2015.
 */


public class menu3_Fragment extends Fragment{

    View rootView;
    Fragment mMapFragment;
    boolean mShowMap;
    GoogleMap map;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate del Fragment - Init Sys And Data
        rootView = inflater.inflate(R.layout.menu3_layout, container, false);
       // int isA = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        Toast.makeText(getActivity(), "Nop", Toast.LENGTH_SHORT).show();
     // mShowMap =  && initMap();

        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        mMapFragment = SupportMapFragment.newInstance();
        android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.map, mMapFragment).commit();

        return  rootView;
    }
  public boolean initMap(){
      return false;
  }






}
