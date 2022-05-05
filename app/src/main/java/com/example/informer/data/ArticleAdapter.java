package com.example.informer.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.informer.DetailsActivity;
import com.example.informer.R;
import com.example.informer.util.Util;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    ArrayList<Article> articles = new ArrayList<>(); // we have just declared a arraylist further we'll use it
    Context context;
    private OnItemClickListener itemClickListener;

    public ArticleAdapter(ArrayList<Article> articles, Context context) {
        this.articles = articles;// here this arraylist contain all the data present in Articledata.getNewsListth
        this.context = context;
    }
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View articleRow = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.news_rows,parent ,false);
        return new ViewHolder(articleRow) ;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.date.setText(Util.dateFormatted(article.getPublishDate()));
        holder.author.setText(article.getAuthor());
        Picasso.get()
                .load(article.getImageUrl())
                .into(holder.articleImage);
    }
    @Override
    public int getItemCount() {
        return articles.size();
    }//this is how other class can access adapter position

    public void setOnClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener= itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView articleImage;
        public TextView author, description, title,date;
        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            //becuz this is not a activity class so it can't call any intent or can't go to detail activity
            //so what we'll tryna implement this on click from activity class

            articleImage = itemView.findViewById(R.id.newsImageId);
            author = itemView.findViewById(R.id.author);
            description= itemView.findViewById(R.id.descriptionNews);
            title = itemView.findViewById(R.id.newsTitle);
            date = itemView.findViewById(R.id.date);

        }
        @Override
        public void onClick(View view) {
            //we can call detailActivity from right here but it is not a very good way to so

//            Article article= articles.get(getAdapterPosition());
//            Intent intent= new Intent(context, DetailsActivity.class);
//            intent.putExtra("url", article.getNewsUrl());
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
            itemClickListener.onItemClick(view,getAdapterPosition());
        }

    }
    //it dont have access to getAdapterPosition(); hence position is passed from onClick()
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        //this method is implemented in MainActivity
        //we cant directly access Articles from here, hence we aren't calling detailActivity()
        //calling detail Activity from here is not a good way too

    }
}
////private OnItemClickListener itemClickListener;
//public void setOnClickListener(OnItemClickListener itemClickListener){
//    this.itemClickListener= itemClickListener;
//}