package com.tiki.android.hometest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiki.android.hometest.R;
import com.tiki.android.hometest.model.Deal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DealRecycleViewAdapter extends RecyclerView.Adapter<DealRecycleViewAdapter.RecyclerViewHolder> {

    private ArrayList<Deal> data;
    private Context context;

    public DealRecycleViewAdapter(Context context, ArrayList<Deal> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public DealRecycleViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_deal, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        holder.tv_dealName.setText(data.get(position).getProductName());
        holder.tv_dealPrice.setText(data.get(position).getProductPrice() + "$");
        //time for deal(s) = end time minus start time
        long count = (data.get(position).getEndDate().getTime() - data.get(position).getStartedDate().getTime()) / 1000;
        holder.tv_dealtimer.setText((count / 60) + ":" + (count % 60));
        //read picture from assest folder
        try {
            InputStream is = null;
            is = context.getAssets().open(data.get(position).getProductThumbnail());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.imv_dealpicture.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_dealName;
        TextView tv_dealPrice;
        TextView tv_dealtimer;
        ImageView imv_dealpicture;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            tv_dealName = itemView.findViewById(R.id.tv_deal_name);
            tv_dealPrice = itemView.findViewById(R.id.tv_deal_price);
            tv_dealtimer = itemView.findViewById(R.id.tv_dealtimer);
            imv_dealpicture = itemView.findViewById(R.id.imv_deal_picture);
        }
    }
}
