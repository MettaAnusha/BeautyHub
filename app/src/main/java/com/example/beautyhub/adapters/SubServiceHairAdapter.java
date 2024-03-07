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
import com.example.beautyhub.servercommunication.SubServiceHair;

import java.util.ArrayList;
import java.util.List;

public class SubServiceHairAdapter extends RecyclerView.Adapter<SubServiceHairAdapter.SubServiceHairViewHolder> {
    private Context mContext;

    private List<SubServiceHair> subservicehair = new ArrayList<>();

    public SubServiceHairAdapter(Context context, List<SubServiceHair> subservicehair) {
        mContext = context;
        this.subservicehair = subservicehair;
    }


    @NonNull

    @Override
    public SubServiceHairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.service_item_layout, parent, false);
        return new SubServiceHairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceHairViewHolder holder, int position) {
        SubServiceHair subserviceHair = subservicehair.get(position);
        holder.bind(subserviceHair);
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
        return subservicehair.size();
    }

    public class SubServiceHairViewHolder extends RecyclerView.ViewHolder {

        private ImageView service_image;
        private TextView service_name;
        private TextView service_description;
        private TextView service_price;
        private TextView service_duration;

        public SubServiceHairViewHolder(@NonNull View itemView) {
            super(itemView);
            service_image = itemView.findViewById(R.id.service_image);
            service_name = itemView.findViewById(R.id.service_name);
            service_description = itemView.findViewById(R.id.service_description);
            service_price = itemView.findViewById(R.id.service_price);
            service_duration = itemView.findViewById(R.id.service_duration);
        }

        public void bind(SubServiceHair subserviceHair) {

            service_image.setImageBitmap(subserviceHair.getSubServiceHairImage());
            service_name.setText(subserviceHair.getSubServiceHairName());
            service_description.setText(subserviceHair.getSubServiceHairDescription());
            service_duration.setText(subserviceHair.getSubServiceHairDuration());
            service_price.setText(subserviceHair.getSubServiceHairPrice());
        }
    }
}
