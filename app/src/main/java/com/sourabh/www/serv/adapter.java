package com.sourabh.www.serv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class adapter extends BaseAdapter {

    private Context mContext;
    List<String> ImageUrl;

    public adapter(Context mContext, List imageUrl) {
        this.mContext = mContext;
        ImageUrl=imageUrl;



    }

    @Override
    public int getCount() {
        return ImageUrl.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.activity_adapter, null);
            ImageView imageView = (ImageView)grid.findViewById(R.id.img);
            String str= ImageUrl.get(position);
            int image = mContext.getResources().getIdentifier(str, "drawable", mContext.getPackageName());

            Glide
                    .with(mContext)
                    .load(image)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);


        } else {
            grid = convertView;
        }

        return grid;
    }



    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
