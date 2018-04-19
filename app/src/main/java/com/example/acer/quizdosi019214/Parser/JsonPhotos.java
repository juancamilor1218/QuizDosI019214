package com.example.acer.quizdosi019214.Parser;

import com.example.acer.quizdosi019214.Model.Photos;
import com.example.acer.quizdosi019214.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 19/04/2018.
 */

public class JsonPhotos {
    public static List<Photos> getData(String content) throws JSONException {

        JSONArray jsonArray = new JSONArray(content);

        List<Photos> photosList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = jsonArray.getJSONObject(i);
            Photos photos=new Photos();
            photos.setTitle(item.getString("title"));
            photos.setThumbnailUrl(item.getString("thumbnailUrl"));


           photosList.add(photos);

        }

        return photosList;


    }
}
