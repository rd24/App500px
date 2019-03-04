package com.sampleapp.app500px.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sampleapp.app500px.R;
import com.sampleapp.app500px.entity.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Photo> photos;
    private LayoutInflater mLayoutInflate;
    public static imagePressed imagePressed;

    public interface imagePressed {
        //This method will be called when Image is selected from gridView
        void imagePressed(int position, Photo photo);
    }

    //public constructor
    public GridAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.photos = photos;
        this.mLayoutInflate = LayoutInflater.from(context);
    }

    //Returns total of items in the list
    public int getCount() {
        if (photos != null) return photos.size();
        return 0;
    }

    //returns list item at the specified position
    @Override
    public Object getItem(int position) {
        if (photos != null && photos.size() > position) return photos.get(position);

        return null;
    }

    @Override
    public long getItemId(int position) {
        if (photos != null && photos.size() > position) return photos.get(position).getId();
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        // inflate the layout for each list row
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = mLayoutInflate.inflate(R.layout.grid_list_item, parent,
                    false);
            viewHolder.imageView = convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // get current item to be displayed
        Photo photo = (Photo) getItem(position);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridAdapter.imagePressed.imagePressed(position, photos.get(position));
            }
        });

        if (photo != null) {
            //Load image from url into imageview
            Picasso.get()
                    .load(photo.getImage_url())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder_error)
                    .into(viewHolder.imageView);
        }

        // returns the view for the current row
        return convertView;
    }

    private class ViewHolder {
        public ImageView imageView;
    }
}
