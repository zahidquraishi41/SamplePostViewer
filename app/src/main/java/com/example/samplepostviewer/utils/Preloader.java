package com.example.samplepostviewer.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;

public class Preloader extends RecyclerView.OnScrollListener {
    private final HashSet<Integer> preloaded;
    private final LinearLayoutManager llm;
    private final PreloadListener listener;
    private final int PRELOAD_THRESHOLD = 3; // number of posts to preload
    private int lastLoadMoreTriggerIndex; // index at which onLoadMore was invoked last time

    public Preloader(LinearLayoutManager llm, PreloadListener listener) {
        this.preloaded = new HashSet<>();
        this.llm = llm;
        this.listener = listener;
        preloaded.add(0);
        lastLoadMoreTriggerIndex = 0;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int lvip = llm.findLastVisibleItemPosition();
        preloaded.add(lvip);

        // preloading images
        for (int i = lvip + 1; i < Math.min(lvip + PRELOAD_THRESHOLD + 1, llm.getItemCount()); i++)
            if (!preloaded.contains(i)) {
                preloaded.add(i);
                listener.onPreload(i);
            }

        // preloading new content
        if (lvip + PRELOAD_THRESHOLD >= llm.getItemCount() && lastLoadMoreTriggerIndex != llm.getItemCount()) {
            lastLoadMoreTriggerIndex = llm.getItemCount();
            listener.onLoadMore();
        }
    }

    public interface PreloadListener {
        void onPreload(int position);
        void onLoadMore();
    }
}
