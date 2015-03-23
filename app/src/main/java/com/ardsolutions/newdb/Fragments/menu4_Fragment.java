package com.ardsolutions.newdb.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ardsolutions.newdb.R;


/**
 * Created by Allan on 19/03/2015.
 */


public class menu4_Fragment extends android.support.v4.app.Fragment {

    View rootView;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate del Fragment - Init Sys And Data
        rootView = inflater.inflate(R.layout.menu4_layout, container, false);

        return  rootView;
    }







}
