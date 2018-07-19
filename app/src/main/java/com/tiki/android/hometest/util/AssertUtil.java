package com.tiki.android.hometest.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class AssertUtil {
    public static String readFile(Context context, String filename) {
        String json;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
