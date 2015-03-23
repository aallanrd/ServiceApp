package com.ardsolutions.newdb;

import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Allan on 19/03/2015.
 */
public class SysControl {


    public SysControl(){

    }

    public String getDay(View rootView){
        String weekDay;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);

        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());

        return weekDay;
       // textView.setText(weekDay);
    }

}
