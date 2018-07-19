package com.tiki.android.hometest.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tiki.android.hometest.R;
import com.tiki.android.hometest.adapter.DealRecycleViewAdapter;
import com.tiki.android.hometest.api.ApiServices;
import com.tiki.android.hometest.model.Deal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    ApiServices apiServices;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rv_listDeals;
    private DealRecycleViewAdapter adapterDeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiServices = new ApiServices(this);
        initDataDeals();
        rv_listDeals = findViewById(R.id.rv_list_deals);
        GridLayoutManager mGridLayoutManager;
        mGridLayoutManager = new GridLayoutManager(this, 2);
        rv_listDeals.setLayoutManager(mGridLayoutManager);
    }

    private void initDataDeals() {
        new AsyncTask<Void, Void, ArrayList<Deal>>() {
            @Override
            protected ArrayList<Deal> doInBackground(Void... voids) {
                return apiServices.getDeals();
            }

            @Override
            public void onPostExecute(ArrayList<Deal> deals) {
                super.onPostExecute(deals);
                //set Data for RecycleView when load data from assert folder succesful
                adapterDeals = new DealRecycleViewAdapter(MainActivity.this, deals);
                rv_listDeals.setAdapter(adapterDeals);
                createTimerCountDown(deals);
            }
        }.execute();
    }

    private void createTimerCountDown(final ArrayList<Deal> deals) {
        final Handler timerHandler = new Handler();
        Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                //use iterator for remove item instead of for loop
                Iterator<Deal> iter = deals.iterator();
                int i = 0;
                while (iter.hasNext()) {
                    Deal deal = iter.next();
                    Date date = new Date();
                    date.setTime(deal.getEndDate().getTime() - 1000);
                    //if expired deal remove it
                    if (date.getTime() <= deal.getStartedDate().getTime()) {
                        iter.remove();
                        adapterDeals.notifyItemRemoved(i);
                    } else {
                        deal.setEndDate(date);
                        adapterDeals.notifyDataSetChanged();
                    }
                    i++;
                }
                timerHandler.postDelayed(this, 1000); //run every second
            }
        };
        timerHandler.post(timerRunnable);
    }
}
