# SamplePostViewer
**SamplePostViewer** is a sample Android application that demonstrates the implementation of an endless scrolling feed with a clean, efficient UI. It serves as an abstraction for building feature-rich apps by showcasing preloading of images, smooth data loading, and responsive user interactions. All essential components—RecyclerView, ViewHolder, Adapter, and ViewModel—are fully integrated, leaving only the API logic for fetching posts.


## Features
- **Endless Scrolling:** Scroll through a continuous list of posts without interruptions.
- **Preloading Images and Posts:** Images and new posts are preloaded for a fast and fluid user experience.
- **Clean and Fast UI:** A simple, minimalist interface designed for performance and ease of use.


## Demo
https://github.com/user-attachments/assets/f0bb67f9-8bcc-4d67-b506-dbe0a86c73df


## Overview
- **MainActivity**: Initializes the RecyclerView and adapter, and implements the logic for preloading images and fetching new posts during scrolling.
- **PostAdapter**: A custom implementation of `RecyclerView.Adapter` that manages the binding of post data to views via the `ViewHolder`.
- **PostAPI**: Contains the logic for fetching post data and supplying it to the adapter.
- **PostModel**: Encapsulates post details such as title, tags, and URL in a structured data model.
- **Preloader**: A `RecyclerView.OnScrollListener` that handles the logic for preloading images and fetching additional posts as the user scrolls.
- **TagSpan**: Assigns a unique background color to each tag based on the text's hash, ensuring consistent colors for the same text.


## Libraries
This app leverages two third-party libraries:
- **[Picasso](https://square.github.io/picasso/):** For image loading and caching.
- **[Stfalcon ImageViewer](https://github.com/stfalcon-studio/StfalconImageViewer):** For full-screen image viewing with smooth transitions.


## Getting Started
1. Clone this repository.
   ```bash
   git clone https://github.com/zahidquraishi41/SamplePostViewer.git
   ```
2. Open the project in Android Studio.
3. write the logic to fetch posts in PostAPI.java.
4. Build and run the project.

## Contribution

Feel free to contribute by submitting a pull request or suggesting improvements in the issues section!
