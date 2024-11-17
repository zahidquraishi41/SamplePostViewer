package com.example.samplepostviewer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplepostviewer.utils.Preloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Preloader.PreloadListener, PostAPI.PostCallback {
    private PostAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new PostAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new Preloader(layoutManager, this));

        PostAPI.fetchPosts(this);
    }

    @Override
    public void onSuccess(List<PostModel> posts) {
        progressBar.setVisibility(View.GONE);
        adapter.addPosts(posts);
    }

    @Override
    public void onError(String e) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, e, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPreload(int position) {
        String url = adapter.getPost(position).getUrl();
        Picasso.get().load(url).fetch();
    }

    @Override
    public void onLoadMore() {
        progressBar.setVisibility(View.VISIBLE);
        PostAPI.fetchPosts(this);
    }

}