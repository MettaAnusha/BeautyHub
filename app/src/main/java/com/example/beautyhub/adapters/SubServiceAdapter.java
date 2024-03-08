package com.example.beautyhub.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhub.R;
import com.example.beautyhub.activities.Booking_Screen1;
import com.example.beautyhub.activities.Onboarding_Screen4;
import com.example.beautyhub.servercommunication.SubService;

import java.util.ArrayList;
import java.util.List;

public class SubServiceAdapter extends RecyclerView.Adapter<SubServiceAdapter.SubServiceViewHolder> {

    private Context mContext;

    private List<SubService> subservice = new ArrayList<>();

    public SubServiceAdapter(Context context, List<SubService> subservice) {
        mContext = context;
        this.subservice = subservice;
    }

    @NonNull
    @Override
    public SubServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.service_item_layout, parent, false);
        return new SubServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceViewHolder holder, int position) {
        SubService subService = subservice.get(position);
        holder.bind(subService);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("subservicename", subService.getSubServiceName());
                editor.putString("subserviceprice", subService.getSubServicePrice());
                editor.apply();



                Intent intent = new Intent(mContext, Onboarding_Screen4.class);


                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subservice.size();
    }

    public class SubServiceViewHolder extends RecyclerView.ViewHolder {

        private ImageView service_image;
        private TextView service_name;
        private TextView service_description;
        private TextView service_price;
        private TextView service_duration;

        public SubServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            service_image = itemView.findViewById(R.id.service_image);
            service_name = itemView.findViewById(R.id.service_name);
            service_description = itemView.findViewById(R.id.service_description);
            service_price = itemView.findViewById(R.id.service_price);
            service_duration = itemView.findViewById(R.id.service_duration);
        }

        public void bind(SubService subService) {

            service_image.setImageBitmap(subService.getSubServiceImage());
            service_name.setText(subService.getSubServiceName());
            service_description.setText(subService.getSubServiceDescription());
            service_duration.setText(subService.getSubServiceDuration());
            service_price.setText(subService.getSubServicePrice());
        }
    }
}
