package com.mohit.maskclassifier.adapters;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mohit.maskclassifier.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ProcessedImageAdapter extends PagerAdapter {
    Bitmap[] banners;
    String[] maskRecognised;
    String[] timeElapsed;
    String[] batteryLevel;
    Context context;
    LayoutInflater mLayoutInflater;

    public ProcessedImageAdapter(Bitmap[] banners, String[] maskRecognised, String[] timeElapsed, String[] batteryLevel  , Context context){
        // constructor for getting data from main activity.
        Log.d("image_pager","true");
        this.banners=banners;
        this.maskRecognised=maskRecognised;
        this.timeElapsed=timeElapsed;
        this.batteryLevel=batteryLevel;
        this.context=context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return banners.length;} // for getting the no of pages for view adapter.

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) { // for setting the data for main activity.
        View itemView = mLayoutInflater.inflate(R.layout.image_processed_item, container, false);
        ImageView maskImage = (ImageView) itemView.findViewById(R.id.ivProfile);
        maskImage.setImageBitmap(banners[position]);
        TextView tvOutputValue = (TextView) itemView.findViewById(R.id.tvOutputValue);
        tvOutputValue.setText(maskRecognised[position]);
        tvOutputValue.setOnClickListener(view -> { // searching thr mask name in google .
            String escapedQuery = null;
            try {
                escapedQuery = URLEncoder.encode(maskRecognised[position], "UTF-8");
                Uri uri = Uri.parse("http://www.google.com/search?q=" + escapedQuery);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        TextView tvTimeElapsedValue = (TextView) itemView.findViewById(R.id.tvTimeElapsedValue);
        tvTimeElapsedValue.setText(timeElapsed[position]);
        TextView tvBatteryLevel = (TextView) itemView.findViewById(R.id.tvBatteryLevel);
        tvBatteryLevel.setText(batteryLevel[position]);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) { // for removing the pager after slide complete.
        container.removeView((RelativeLayout) object);
    }

}