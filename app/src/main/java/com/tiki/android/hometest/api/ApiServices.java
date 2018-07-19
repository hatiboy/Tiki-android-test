package com.tiki.android.hometest.api;

import android.content.Context;

import com.tiki.android.hometest.model.Deal;
import com.tiki.android.hometest.util.AssertUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class ApiServices {

    private static final String TAG = ApiServices.class.getSimpleName();
    private Context context;

    private int INCREASING_SECONDS = 15;
    private int DURATION = 60; // 1 minute
    private String ASSERT_FILE_PATTERN = "data/images/";

    public ApiServices(Context context) {
        this.context = context;
    }

    public ArrayList<Deal> getDeals() {
        try {
            ArrayList<Deal> deals = new ArrayList<>();
            String json = AssertUtil.readFile(context, "data/deals.json");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                Deal deal = new Deal();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has("name"))
                    deal.setProductName(jsonObject.getString("name"));
                if (jsonObject.has("price"))
                    deal.setProductPrice(jsonObject.getDouble("price"));
                if (jsonObject.has("thumbnail"))
                    deal.setProductThumbnail(ASSERT_FILE_PATTERN + jsonObject.getString("thumbnail"));
                Calendar now = Calendar.getInstance();
                now.add(Calendar.SECOND, i * INCREASING_SECONDS);
                deal.setStartedDate(now.getTime());
                now.add(Calendar.SECOND, Math.abs(DURATION - i * INCREASING_SECONDS));
                deal.setEndDate(now.getTime());
                deals.add(deal);
            }
            return deals;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
