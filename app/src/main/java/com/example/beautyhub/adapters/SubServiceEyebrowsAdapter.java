package com.example.beautyhub.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhub.R;
import com.example.beautyhub.activities.Onboarding_Screen4;
import com.example.beautyhub.servercommunication.SubServiceEyebrows;

import java.util.ArrayList;
import java.util.List;

public class SubServiceEyebrowsAdapter extends RecyclerView.Adapter<SubServiceEyebrowsAdapter.SubServiceEyebrowsViewHolder> {
    private Context mContext;

    private List<SubServiceEyebrows> subserviceeyebrows = new ArrayList<>();

    public SubServiceEyebrowsAdapter(Context context, List<SubServiceEyebrows> subserviceeyebrows) {
        mContext = context;
        this.subserviceeyebrows = subserviceeyebrows;
    }


    @NonNull

    @Override
    public SubServiceEyebrowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.service_item_layout, parent, false);
        return new SubServiceEyebrowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceEyebrowsViewHolder holder, int position) {
        SubServiceEyebrows subserviceEyebrows = subserviceeyebrows.get(position);
        holder.bind(subserviceEyebrows);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, Onboarding_Screen4.class);


                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subserviceeyebrows.size();
    }

    public class SubServiceEyebrowsViewHolder extends RecyclerView.ViewHolder {

        private ImageView service_image;
        private TextView service_name;
        private TextView service_description;
        private TextView service_price;
        private TextView service_duration;

        public SubServiceEyebrowsViewHolder(@NonNull View itemView) {
            super(itemView);
            service_image = itemView.findViewById(R.id.service_image);
            service_name = itemView.findViewById(R.id.service_name);
            service_description = itemView.findViewById(R.id.service_description);
            service_price = itemView.findViewById(R.id.service_price);
            service_duration = itemView.findViewById(R.id.service_duration);
        }

        public void bind(SubServiceEyebrows subserviceEyebrows) {

            service_image.setImageBitmap(subserviceEyebrows.getSubServiceEyebrowsImage());
            service_name.setText(subserviceEyebrows.getSubServiceEyebrowsName());
            service_description.setText(subserviceEyebrows.getSubServiceEyebrowsDescription());
            service_duration.setText(subserviceEyebrows.getSubServiceEyebrowsDuration());
            service_price.setText(subserviceEyebrows.getSubServiceEyebrowsPrice());
        }
    }
}
