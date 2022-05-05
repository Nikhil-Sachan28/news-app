package com.example.informer.data;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.informer.controller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArticleData {

    ArrayList<Article> articles = new ArrayList<>();

    public void getNewsList(ArticleListAsyncResponse callBack){

        String URL  = "https://newsapi.org/v2/top-headlines?country=in&apiKey=2e44d4ded3204ed0ab249efcb2e34ccd";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray articleArray= response.getJSONArray("articles");
                    for(int i=0; i<articleArray.length(); i++){
                        JSONObject articleObject = articleArray.getJSONObject(i);
                        Article article= new Article();

                        article.setAuthor(articleObject.getString("author"));
                        article.setTitle(articleObject.getString("title"));
                        article.setDescription(articleObject.getString("description"));
                        article.setImageUrl(articleObject.getString("urlToImage"));
                        article.setPublishDate(articleObject.getString("publishedAt"));
                        article.setNewsUrl(articleObject.getString("url"));

                        articles.add(article);
                    }
                    if(null!=callBack)
                        callBack.processFinish(articles);

                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
