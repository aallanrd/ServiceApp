package com.ardsolutions.newdb;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Allan on 19/03/2015.
 */
public class DataControl {


    public  DataControl(){

    }

    /* *Llamado desde el Fragment*
       Context = this.getActivity()
       ListView = (ListView) rootView.findViewById(R.id.listView1);
       resourse = android.R.layout.simple_list_item_1
       strListView = new String { "Allan" , "Rojas" , "Duran"}
     */

    public void fillList(Context context,ListView listView,@LayoutRes int resourse, String[] strListView ){


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        context,
                        resourse,
                       strListView);

        listView.setAdapter(adapter);
    }
}
