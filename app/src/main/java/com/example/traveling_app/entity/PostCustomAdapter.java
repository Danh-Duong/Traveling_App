package com.example.traveling_app.entity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.traveling_app.R;
import com.example.traveling_app.model.Post;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PostCustomAdapter extends BaseAdapter {
    private List<Post> posts;
    private Context context;
    private LayoutInflater layoutInflater;

    public PostCustomAdapter(Context context, List<Post> posts) {
        this.posts = posts;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView img_blog;
        TextView name_user_blog;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = layoutInflater.inflate(R.layout.post_item, null);
            holder=new ViewHolder();
            holder.img_blog=(ImageView) convertView.findViewById(R.id.img_blog);
            holder.name_user_blog=(TextView) convertView.findViewById(R.id.name_user_blog);
            convertView.setTag(holder);
        }
        else
            holder= (ViewHolder) convertView.getTag();
        Post post=posts.get(position);

        holder.name_user_blog.setText(post.getUsername());
        ImageLoader.loadImage(post.getPostImgUrl(),holder.img_blog);
        return convertView;
    }
}