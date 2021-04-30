package com.nestor.globalLog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nestor.apimanager.Clases.Item;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListAdapter extends  RecyclerView.Adapter<ListAdapter.ViewHolder> {
    Context context;
    ArrayList<Item> values;

    public ListAdapter (Context ctx, ArrayList<Item> values){
        this.context = ctx;
        this.values = values;
    }


    public class ViewHolder extends  RecyclerView.ViewHolder{

        public ViewHolder(View itemView){
            super(itemView);
        }

    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.row_item, parent, false);

        ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        TextView txt,subt;
        CircleImageView img;


        txt = holder.itemView.findViewById(R.id.row_title);
        subt = holder.itemView.findViewById(R.id.row_subt);
        img = holder.itemView.findViewById(R.id.row_img);

        txt.setText(values.get(position).title);
        subt.setText(values.get(position).desc);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_launcher);

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(values.get(position).image)
                .into(img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("txtTitle",values.get(position).title);
                intent.putExtra("txtDesc",values.get(position).desc);
                intent.putExtra("urlImg",values.get(position).image);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
