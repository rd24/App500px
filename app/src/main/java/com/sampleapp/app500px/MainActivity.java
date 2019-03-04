package com.sampleapp.app500px;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sampleapp.app500px.adapter.GridAdapter;
import com.sampleapp.app500px.entity.Photo;
import com.sampleapp.app500px.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GridAdapter.imagePressed {

    private GridView gridView;
    private GridAdapter gridAdapter;
    private ArrayList<Photo> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup toolbar
        setUpToolbar();

        //Initialize and set up gridView
        initializeValues();

        //Get Data from the server
        getData();

    }

    //Initialize and set up gridView
    public void initializeValues() {
        gridView = findViewById(R.id.gridView);

        photos = new ArrayList<>();
        gridAdapter = new GridAdapter(this, photos);
        gridAdapter.imagePressed = this;
        gridView.setAdapter(gridAdapter);
    }

    //Setup toolbar
    public void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView back = toolbar.findViewById(R.id.back_arrow);
        back.setVisibility(View.INVISIBLE);
    }

    @Override
    public void imagePressed(int position, Photo photo) {
        //Add Fragment when image is pressed and pass the detail of the image to the fragment
        addFragment(R.id.main_activity_frame_layout, PhotoDetailFragment.newInstance(photo), "PhotoDetailFragment");
    }

    //Add fragment to the activity
    public void addFragment(@IdRes int containerViewId,
                            @NonNull Fragment fragment,
                            @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .addToBackStack(null)
                .commit();
    }

    //Get Images from server
    public void getData() {
        //Build the url using Uri.Builder
        Uri.Builder urlBuilder = new Uri.Builder();
        urlBuilder.scheme("https")
                .authority("api.500px.com")
                .appendPath("v1")
                .appendPath("photos")
                .appendQueryParameter("feature", "popular")
                .appendQueryParameter("consumer_key", ConsumerKey.consumerKey);

        //The URL having the json data
        String url = urlBuilder.build().toString();

        //Display progressbar
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //Creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Hide Progress
                progressDialog.dismiss();

                try {
                    //Getting the whole json object from the response
                    JSONObject obj = new JSONObject(response);

                    //Get users in json array object
                    JSONArray photoArray = obj.getJSONArray("photos");

                    //Now looping through all the elements of the photoArray
                    for (int i = 0; i < photoArray.length(); i++) {
                        //Getting the json object of the particular index inside the array
                        JSONObject photoObj = photoArray.getJSONObject(i);

                        //Creating a photo object and giving them the values from json object
                        Photo photo = new Photo(photoObj.getInt("id"),
                                photoObj.getString("name"),
                                photoObj.getString("description"),
                                photoObj.getInt("times_viewed"),
                                photoObj.getDouble("rating"),
                                photoObj.getString("created_at"),
                                photoObj.getString("category"),
                                photoObj.getBoolean("privacy"),
                                photoObj.getInt("width"),
                                photoObj.getInt("height"),
                                photoObj.getInt("votes_count"),
                                photoObj.getInt("comments_count"),
                                photoObj.getBoolean("nsfw"),
                                photoObj.getJSONArray("image_url").get(0).toString().replace("\\/", "/"),
                                photoObj.getString("camera"),
                                new User(photoObj.getJSONObject("user").getInt("id"),
                                        photoObj.getJSONObject("user").getString("username"),
                                        photoObj.getJSONObject("user").getString("firstname"),
                                        photoObj.getJSONObject("user").getString("lastname"),
                                        photoObj.getJSONObject("user").getString("city"),
                                        photoObj.getJSONObject("user").getString("country"),
                                        photoObj.getJSONObject("user").getString("fullname"),
                                        photoObj.getJSONObject("user").getString("userpic_url"),
                                        photoObj.getJSONObject("user").getInt("upgrade_status"))

                        );
                        //Adding the photo to photoList
                        photos.add(photo);
                        //Notify adapter for updated values
                        gridAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.toString());
                //Hide Progress
                progressDialog.dismiss();
            }
        });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
