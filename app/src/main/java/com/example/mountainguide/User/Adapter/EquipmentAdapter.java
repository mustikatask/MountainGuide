package com.example.mountainguide.User.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mountainguide.*;
import com.example.mountainguide.User.Details;
import com.example.mountainguide.User.Model.EquipmentModel;

import java.util.ArrayList;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.myviewholder> {
    ArrayList<EquipmentModel> dataList;

    public EquipmentAdapter(ArrayList<EquipmentModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowdesign, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.t1.setText(dataList.get(position).getTitle());
        holder.t2.setText(dataList.get(position).getSubtitle());

        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.t1.getContext(), Details.class);
                intent.putExtra("uname", dataList.get(position).getTitle());
                intent.putExtra("ustatus", dataList.get(position).getSubtitle());
                intent.putExtra("udesc", dataList.get(position).getDesc());

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

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
        }
    }
}

