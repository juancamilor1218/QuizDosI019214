package com.example.acer.quizdosi019214.Parser;

import com.example.acer.quizdosi019214.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 19/04/2018.
 */

public class Json {
    public static List<User> getData(String content) throws JSONException {

        JSONArray jsonArray = new JSONArray(content);

        List<User> UserList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = jsonArray.getJSONObject(i);
            JSONObject itemStreet=item.getJSONObject("address");
            User user = new User();
            user.setName(item.getString("name"));
            user.setUsername(item.getString("username"));
            user.setEmail(item.getString("email"));
            user.setStreet(itemStreet.getString("street"));
            UserList.add(user);

        }

        return UserList;


    }

}
