package com.nestor.globalLog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class DetailActivity extends AppCompatActivity {
    TextView title,desc;
    ImageView img;

    String txtTitle,txtDesc,urlImg;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtTitle = getIntent().getStringExtra("txtTitle");
        txtDesc = getIntent().getStringExtra("txtDesc");
        urlImg = getIntent().getStringExtra("urlImg");


        title = findViewById(R.id.detail_tilte);
        desc = findViewById(R.id.detail_description);
        img = findViewById(R.id.detail_img);
        progressBar = findViewById(R.id.detail_progress);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.unpluged);
        requestOptions.override(200);


        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(urlImg)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(DetailActivity.this,"No se pudo cargar la imagen",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        img.setVisibility(View.VISIBLE);
                        img.setAlpha(50);
                        return  false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        img.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(img);

        title.setText(txtTitle);
        desc.setText(txtDesc);
    }

}