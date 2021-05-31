package com.example.mountainguide.User.Adapter;

import android.app.AppOpsManager;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mountainguide.User.Model.BasecampModel;

import com.example.mountainguide.R;
import com.example.mountainguide.User.BasecampDetail;

import java.util.ArrayList;

public class BasecampAdapter extends RecyclerView.Adapter<BasecampAdapter.myviewholder> {
    ArrayList<BasecampModel> dataList;

    public BasecampAdapter(ArrayList<BasecampModel> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BasecampAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowdesign, parent, false);
        return new BasecampAdapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasecampAdapter.myviewholder holder, int position) {
        holder.t1.setText(dataList.get(position).getName());
        holder.t2.setText(dataList.get(position).getAddr());

        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.t1.getContext(), BasecampDetail.class);
                intent.putExtra("uname", dataList.get(position).getName());
                intent.putExtra("uaddr", dataList.get(position).getAddr());
                intent.putExtra("uestimation", dataList.get(position).getEstimation_time());
                intent.putExtra("uticket", dataList.get(position).getTicket());
                intent.putExtra("udesc", dataList.get(position).getDesc());
//                intent.putExtra("uno_telp", dataList.get(position).getNo_telp());

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
        TextView t1, t2;
//        ImageView img;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
//            img = itemView.findViewById(R.id.img_detail);
        }
    }
}
