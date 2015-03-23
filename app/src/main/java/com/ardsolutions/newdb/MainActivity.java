package com.ardsolutions.newdb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ardsolutions.newdb.Fragments.menu1_Fragment;
import com.ardsolutions.newdb.Fragments.menu2_Fragment;
import com.ardsolutions.newdb.Fragments.menu3_Fragment;
import com.ardsolutions.newdb.Fragments.menu4_Fragment;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ////////////////////////////////////////////////

        Parse.initialize(this, getString(R.string.parseAppID), getString(R.string.parseClientID));
    }

    public void pushObject(View view){

        EditText et = (EditText) findViewById(R.id.editText);
        String name = et.getText().toString();
        ParseObject gameScore = new ParseObject("Puntuacion");
        gameScore.put("marcador", 1337);
        gameScore.put("nombre", name);
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground();

        Context context = getApplicationContext();
        CharSequence text = "Add Ok!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    ArrayAdapter<String> adapter;
    ListView listView3;
    List<ParseObject> ob;

    public void retrieveData(View view){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Puntuacion");
        //query.orderByDescending("_created_at");
        try {
             ob = query.find();


            Context context = getApplicationContext();

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context,ob.get(1).toString(), duration);
            toast.show();
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
       /* listView3 = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1);
        // Retrieve object "name" from Parse.com database

        for (ParseObject puntuacion : ob) {
            //list1Strings22[iii]=(String) country.get("name");
            adapter.add((String) puntuacion.get("name"));


        }


        listView3.setAdapter(adapter);*/
    }

    public static final int select= 0;
    public void openGallery(View view) {


        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, select);


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getImage(requestCode,resultCode,data);
    }

    public void getImage(int requestCode, int resultCode, Intent data){
        if(requestCode == select && data != null && data.getData() != null) {
            Uri _uri = data.getData();

            //User had pick an image.
            Cursor cursor = getContentResolver().query(_uri, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA }, null, null, null);
            cursor.moveToFirst();

            //Link to the image
            final String imageFilePath = cursor.getString(0);

            //PathFileImage
            TextView et = (TextView) findViewById(R.id.textView3);
            String filename=imageFilePath.substring(imageFilePath.lastIndexOf("/")+1);
            myImageName = filename;
            cursor.close();
            et.setText(filename);
            //ImageView
          //  ImageView imageView = (ImageView) findViewById(R.id.imageViewS);
          //  imageView.setImageURI(Uri.parse(imageFilePath));
            /////////////////////////////////////////////////////////////
            File imgFile = new  File(imageFilePath);
            if(imgFile.exists()){
                myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                //Drawable d = new BitmapDrawable(getResources(), myBitmap);
                ImageView myImage = (ImageView) findViewById(R.id.imageViewS);
                myImage.setImageBitmap(myBitmap);

            }


          //  Bitmap bitmap = MediaStore.Images.Media.getBitmap(t.getContentResolver() , Uri.parse(imageFilePath));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    Bitmap myBitmap = null;
    String myImageName = null;
    public void toParce(View view) {

        if (myBitmap == null) {
            Toast.makeText(this.getApplicationContext(), "No has seleccionado imagen", Toast.LENGTH_SHORT).show();
        } else {
            // Convert it to byte
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // Compress image to lower quality scale 1 - 100
            myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] image = stream.toByteArray();

            // Create the ParseFile
            ParseFile file = new ParseFile(myImageName, image);
            // Upload the image into Parse Cloud
            file.saveInBackground();

            // Create a New Class called "ImageUpload" in Parse
            ParseObject imgupload = new ParseObject("ImageUpload");

            // Create a column named "ImageName" and set the string
            imgupload.put("ImageName", "AndroidBegin Logo");

            // Create a column named "ImageFile" and insert the image
            imgupload.put("ImageFile", file);

            // Create the class and the columns
            imgupload.saveInBackground();

            // Show a simple toast message
            Toast.makeText(this.getApplicationContext(), "Image Uploaded",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objFragment = null;
        switch(position){
            case 0:
                objFragment = new menu1_Fragment();
                break;
            case 1:
                objFragment = new menu2_Fragment();
                break;
            case 2:
                objFragment =   new menu3_Fragment();
                break;
            case 3:
                objFragment = new menu4_Fragment();
                break;
        }
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objFragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.menu1_layout, container, false);
            return rootView;
        }



        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }



    }

}
