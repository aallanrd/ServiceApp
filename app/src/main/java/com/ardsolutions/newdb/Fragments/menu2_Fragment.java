package com.ardsolutions.newdb.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ardsolutions.newdb.DataControl;
import com.ardsolutions.newdb.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Allan on 19/03/2015.
 */


public class menu2_Fragment extends android.support.v4.app.Fragment {

    View rootView;
    DataControl dataControl;
    private ListView myListView;
    Context context;
  ////////////////////////////////////

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate del Fragment - Init Sys And Data
        rootView = inflater.inflate(R.layout.menu2_layout, container, false);
        dataControl = new DataControl();
        context = this.getActivity();
        ////////////////Control de Datos///////////////////////////////
        myListView =(ListView) rootView.findViewById(R.id.listView2);

        Parse.initialize(this.getActivity(), getString(R.string.parseAppID), getString(R.string.parseClientID));
        runDialog();


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = myListView.getItemAtPosition(position);
                String str=o.toString();//As you are using Default String Adapter
                Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
            }
        });

        return  rootView;
    }

    private boolean responded = false;






    public void runDialog(){
        final ProgressDialog ringProgressDialog = ProgressDialog.show(context, "Please wait ...", "Downloading Info ...", true);

        ringProgressDialog.setCancelable(true);

        new Thread(new Runnable() {

            @Override

            public void run() {
                try {

                    int c = 0;
                   /* if(isOnline()==false){
                        Toast.makeText(context, "No Internet Access", Toast.LENGTH_SHORT).show();
                    }
                    else {*/
                        boolean getData = getData();
                        if (getData == true) {
                            while ((getData != true) || (c == 5)) {
                                Thread.sleep(3000);
                                c = c + 1;

                            }
                            Toast.makeText(context, "Access Ok", Toast.LENGTH_SHORT).show();
                        }


                } catch (Exception e) {
                }
                ringProgressDialog.dismiss();
            }
        }).start();

    }
    boolean rete = true;



        public boolean isOnline() {

            Toast.makeText(context, "Enter Get Connection>: " + responded, Toast.LENGTH_SHORT).show();
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(3000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        return new Boolean(true);
                    }
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return false;
        }





    public boolean getData(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Puntuacion");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    objectsWereRetrievedSuccessfully(objects);
                } else {
                    //  objectRetrievalFailed();
                    rete = false;
                }
            }
        });
        return rete;
    }


public boolean objectsWereRetrievedSuccessfully(  List<ParseObject> objects){

    boolean ret = true;
try {

    ArrayAdapter<Object> adapter;
    final ArrayList<Object> objsList = new ArrayList<Object>();
    if(objects.size()==0){
        ret = false;
    }else{
    int c = 0;
    while (c != objects.size()) {
        String name = objects.get(c).getString("nombre");

        objsList.add(name);
        c = c + 1;


    }
    myListView = (ListView) rootView.findViewById(R.id.listView2);
    adapter = new ArrayAdapter<Object>(context, android.R.layout.simple_list_item_1, objsList);
    myListView.setAdapter(adapter);
    }
}
catch(Exception e){
 ret = false;
}
    return ret;
 }




}
