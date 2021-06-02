package com.example.mountainguide.User.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mountainguide.R;
import com.example.mountainguide.User.DetailsMountain;
import com.example.mountainguide.User.Model.MountainModel;

import java.util.ArrayList;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.myviewholder>{
    ArrayList<MountainModel> dataList;

    public MountainAdapter(ArrayList<MountainModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MountainAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowdesignmountain, parent, false);
        return new MountainAdapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MountainAdapter.myviewholder holder, int position) {
        holder.t1.setText(dataList.get(position).getName());
        holder.t2.setText(dataList.get(position).getStatus());
        holder.t3.setText(dataList.get(position).getHeight());
        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.t1.getContext(), DetailsMountain.class);
                intent.putExtra("uid", dataList.get(position).getId());
                intent.putExtra("uname", dataList.get(position).getName());
                intent.putExtra("ustatus", dataList.get(position).getStatus());
                intent.putExtra("udesc", dataList.get(position).getDesc());
                intent.putExtra("uheight", dataList.get(position).getHeight());
                intent.putExtra("unote", dataList.get(position).getNote());
                intent.putExtra("uterrain", dataList.get(position).getTerrain());
                intent.putExtra("uweather", dataList.get(position).getWeather());
                intent.putExtra("ulocation", dataList.get(position).getLocation());
                intent.putExtra("ugeometry", dataList.get(position).getGeometry());
                intent.putExtra("uimgurl", dataList.get(position).getImgurl());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.t1.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3;
//        ImageView img;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
//            img = itemView.findViewById(R.id.img_detail);
        }
    }
}

