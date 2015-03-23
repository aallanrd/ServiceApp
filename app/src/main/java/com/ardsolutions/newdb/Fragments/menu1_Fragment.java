package com.ardsolutions.newdb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ardsolutions.newdb.DataControl;
import com.ardsolutions.newdb.R;
import com.ardsolutions.newdb.SysControl;
import com.google.android.gms.maps.MapFragment;


/**
 * Created by Allan on 19/03/2015.
 */


public class menu1_Fragment extends android.support.v4.app.Fragment {

    View rootView;
    SysControl sysControl;
    DataControl dataControl;
    private ListView myListView;
    private String[] strListView;
    Context context;
    MapFragment mMapFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate del Fragment - Init Sys And Data
        rootView = inflater.inflate(R.layout.menu1_layout, container, false);





        /*sysControl = new SysControl();
        dataControl = new DataControl();
        context = this.getActivity();


        /////////////////Control Metodos del Sistema/////////////////////
        TextView txtView = (TextView) rootView.findViewById(R.id.textViewSC);
        txtView.setText(sysControl.getDay(rootView));

        ////////////////Control de Datos////////////////////////////////

        //ParseObject.registerSubclass(Puntuacion.class);
        Parse.initialize(this.getActivity(), getString(R.string.parseAppID), getString(R.string.parseClientID));

        myListView = (ListView) rootView.findViewById(R.id.listView1);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Puntuacion");
        query.getInBackground("7TJyqvsmqi", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    String[] menu = {
                            "Data1 :"+ object.getString("nombre") +"` Date:  05:15",
                            "Data2 : 12345` Date:  05:20",
                            "Data3 : 12778` Date:  05:30"};
                    dataControl.fillList(context,myListView,android.R.layout.simple_list_item_1,menu);
                    // initializeParseObject();
                } else {
                    // something went wrong
                }
            }



        });*/
        return rootView;
    }

}


/*
    public void initializeParseObject(){
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        Toast.makeText(getActivity(), "Parse Send :D", Toast.LENGTH_SHORT).show();

    }
    public void newUser(){
        ParseUser user = new ParseUser();
        user.setUsername("my name");
        user.setPassword("my pass");
        user.setEmail("email@example.com");

// other fields can be set just like with ParseObject
        user.put("phone", "650-555-0000");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        }); }
        */




