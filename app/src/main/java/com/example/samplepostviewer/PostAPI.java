package com.example.samplepostviewer;

import java.util.List;

public class PostAPI {
    public static void fetchPosts(PostCallback callback) {
        // TODO: implement fetchPosts
    }

    public interface PostCallback {
        void onSuccess(List<PostModel> posts);

        void onError(String e);
    }

}
