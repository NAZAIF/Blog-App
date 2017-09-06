package com.example.nazaifmoideen.blog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleListActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    ArticleListAdapter articleListAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        articleListAdapter = new ArticleListAdapter();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(articleListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Article");
        progressDialog.show();
        APIManager.getAPInterface().getarticles()
                .enqueue(new Callback<List<article>>() {
                    @Override
                    public void onResponse(Call<List<article>> call, Response<List<article>> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            articleListAdapter.setdata(response.body());
                        }else{
                            alertShow("Failed", "Unable to fetch");

                        }

                    }

                    @Override
                    public void onFailure(Call<List<article>> call, Throwable t) {
                        alertShow("Failed", "Unable to fetch");
                    }
                });
    }

    private void alertShow(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    public class ArticleListAdapter extends RecyclerView.Adapter<ArticleItemViewHolder>{

        List<article> articles = new ArrayList<>();
        @Override
        public ArticleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_article, parent, false);
            return new ArticleItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ArticleItemViewHolder holder, final int position) {
            holder.articleName.setText(articles.get(position).getHeading());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ArticleListActivity.this,"Article Clicked:"+articles.get(position).getHeading(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }

        public void setdata(List<article> data){
            this.articles =data;
            this.notifyDataSetChanged();
        }
    }


}
