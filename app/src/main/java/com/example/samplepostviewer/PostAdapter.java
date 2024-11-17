package com.example.samplepostviewer;

import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplepostviewer.utils.TagSpan;
import com.squareup.picasso.Picasso;
import com.stfalcon.imageviewer.StfalconImageViewer;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private final ArrayList<PostModel> posts;

    public PostAdapter() {
        posts = new ArrayList<>();
    }

    public PostModel getPost(int position) {
        return posts.get(position);
    }

    public void addPosts(List<PostModel> posts) {
        int start = this.posts.size();
        this.posts.addAll(posts);
        notifyItemRangeInserted(start, posts.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPostTitle, tvPostTags;
        private final ImageView ivPostImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPostTitle = itemView.findViewById(R.id.tv_post_title);
            tvPostTags = itemView.findViewById(R.id.tv_post_tags);
            ivPostImage = itemView.findViewById(R.id.iv_post_image);
            ivPostImage.setOnClickListener(v -> {
                if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
                new StfalconImageViewer.Builder<>(
                        itemView.getContext(),
                        new String[]{posts.get(getAdapterPosition()).getUrl()},
                        (imageView, image) -> Picasso.get().load(image).into(imageView)
                ).withHiddenStatusBar(false).show();
            });
        }

        public void bind(PostModel post) {
            tvPostTitle.setText(post.getTitle());
            tvPostTags.setText(coloredTags(post.getTags()));
            Picasso.get().load(post.getUrl()).into(ivPostImage);
        }

        private SpannableStringBuilder coloredTags(List<String> tags) {
            SpannableStringBuilder builder = new SpannableStringBuilder();
            for (String tag : tags) {
                builder.append(TagSpan.getTag(tag));
                builder.append("  ");
            }
            if (builder.length() > 0) builder.delete(builder.length() - 1, builder.length());
            return builder;
        }
    }

}
