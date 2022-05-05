package com.example.informer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.informer.data.Article;
import com.example.informer.data.ArticleAdapter;
import com.example.informer.data.ArticleData;
import com.example.informer.data.ArticleListAsyncResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ArticleData().getNewsList(new ArticleListAsyncResponse() {
            @Override
            public void processFinish(ArrayList<Article> articles) {

                recyclerView= findViewById(R.id.recyclerView);
                articleAdapter = new ArticleAdapter(articles,getApplicationContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(articleAdapter);
                articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListener() {
                    //here we aren't implementing onItemClick() directly becuz it don't have access to articles arrayList
                    @Override
                    public void onItemClick(View view, int position) {
                        Article article= articles.get(position);
                        Intent intent= new Intent(getApplicationContext(),DetailsActivity.class);
                        intent.putExtra("url", article.getNewsUrl());
                        startActivity(intent);
                    }
                });
            }
        });
    }
}