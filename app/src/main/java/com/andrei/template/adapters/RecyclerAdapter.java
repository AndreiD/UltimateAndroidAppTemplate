package com.andrei.template.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.andrei.template.R;
import com.andrei.template.activities.MainActivity;
import com.andrei.template.utils.DUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.io.File;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.XPhotoViewHolder> {


    private ArrayList<String> mPhotos;
    Context mContext;


    public RecyclerAdapter(Context xContext, ArrayList<String> mPhotos) {

        this.mContext = xContext;
        this.mPhotos = mPhotos;


    }


    @Override
    public XPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview, parent, false);
        XPhotoViewHolder pvh = new XPhotoViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(final XPhotoViewHolder holder, final int position) {

        String url = mPhotos.get(position);

        Glide.with(mContext).load(url)
                .error(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.the_photo);


        holder.row_rellayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DUtils.inform((MainActivity)mContext,"Clicked on adapter position number " + String.valueOf(position),R.color.orange_light);

            }
        });


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public static class XPhotoViewHolder extends RecyclerView.ViewHolder {

        TextView the_text;
        ImageView the_photo;
        RelativeLayout row_rellayout;

        XPhotoViewHolder(View itemView) {
            super(itemView);


            the_text = (TextView) itemView.findViewById(R.id.the_text);
            the_photo = (ImageView) itemView.findViewById(R.id.the_photo);
            row_rellayout = (RelativeLayout) itemView.findViewById(R.id.row_rellayout);
        }


    }


}