package com.example.sub3eca.ViewModel;

import android.app.Application;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.sub3eca.Items;
import com.example.sub3eca.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowVM  extends AndroidViewModel {

    private MutableLiveData<ArrayList<Items>> items = new MutableLiveData<>();
    public  ArrayList<Items> mitems = new ArrayList<>();
    private RequestQueue rq;
    private String url;

    public ShowVM(@NonNull Application application) {
        super(application);
        rq = Volley.newRequestQueue(application);
        url = application.getResources().getString(R.string.api_tv);
    }
    public void getAPI() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    int length = jsonArray.length();
                    for(int i = 0;i<length;i++){
                        JSONObject result = jsonArray.getJSONObject(i);
                        String title = result.getString("original_name");
                        String photo = result.getString("poster_path");
                        String overview = result.getString("overview");
                        String realease = result.getString("first_air_date");
                        Log.d("title", title);
                        Items items = new Items(realease,title,overview,photo);

                        mitems.add(items);

                    }

                    items.postValue(mitems);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        rq.add(request);
    }
    public LiveData<ArrayList<Items>> getShow(){

       return items ;
    }
}
