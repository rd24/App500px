package com.sampleapp.app500px;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sampleapp.app500px.entity.Photo;
import com.squareup.picasso.Picasso;

public class PhotoDetailFragment extends Fragment {
    public Photo photo;
    private ImageView image;
    private TextView camera, description, rating, userName;
    private static String PHOTO_DETAIL = "PHOTO_DETAIL";

    //Empty Constructor
    public PhotoDetailFragment() {
    }

    //New Instance of the fragment
    public static PhotoDetailFragment newInstance(Photo photo) {
        PhotoDetailFragment fragment = new PhotoDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PHOTO_DETAIL, photo);
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            photo = (Photo) getArguments().get(PHOTO_DETAIL); //Get the value of photo object
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);

        setUpToolbar(view);
        initialize(view);

        return view;
    }

    //Set up toolbar
    public void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ImageView back = toolbar.findViewById(R.id.back_arrow);

        //Return to activity when back button is pressed
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        TextView title = view.findViewById(R.id.toolbar_text);
        //Set Photo name as title
        title.setText(photo.getName());
    }

    //Initialize all the views
    public void initialize(View view) {
        image = view.findViewById(R.id.selectedPhoto);
        camera = view.findViewById(R.id.image_camera);
        description = view.findViewById(R.id.image_description);
        rating = view.findViewById(R.id.image_rating);
        userName = view.findViewById(R.id.image_user_name);

        setValues();
    }

    //Set the values foe all views
    public void setValues() {
        Picasso.get()
                .load(photo.getImage_url())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder_error)
                .into(image);

        if (photo.getCamera().equalsIgnoreCase("null") || photo.getCamera().isEmpty()) {
            camera.setText(R.string.not_available);
        } else camera.setText(photo.getCamera());

        if (photo.getDescription().equalsIgnoreCase("null") || photo.getDescription().isEmpty()) {
            description.setText(R.string.not_available);
        } else description.setText(photo.getDescription());

        rating.setText(photo.getRating() + "");
        userName.setText(photo.getUser().getUsername());
    }
}
