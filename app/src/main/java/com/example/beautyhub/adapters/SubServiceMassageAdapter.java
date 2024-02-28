package com.example.beautyhub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhub.R;
import com.example.beautyhub.servercommunication.SubServiceMassage;

import java.util.ArrayList;
import java.util.List;

public class SubServiceMassageAdapter extends RecyclerView.Adapter<SubServiceMassageAdapter.SubServiceMassageViewHolder> {
    private Context mContext;

    private List<SubServiceMassage> subservicemassage = new ArrayList<>();

    public SubServiceMassageAdapter(Context context, List<SubServiceMassage> subservicemassage) {
        mContext = context;
        this.subservicemassage = subservicemassage;
    }


    @NonNull

    @Override
    public SubServiceMassageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.service_item_layout, parent, false);
        return new SubServiceMassageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceMassageViewHolder holder, int position) {
        SubServiceMassage subserviceMassage = subservicemassage.get(position);
        holder.bind(subserviceMassage);
    }

    @Override
    public int getItemCount() {
        return subservicemassage.size();
    }

    public class SubServiceMassageViewHolder extends RecyclerView.ViewHolder {

        private ImageView service_image;
        private TextView service_name;
        private TextView service_description;
        private TextView service_price;
        private TextView service_duration;

        public SubServiceMassageViewHolder(@NonNull View itemView) {
            super(itemView);
            service_image = itemView.findViewById(R.id.service_image);
            service_name = itemView.findViewById(R.id.service_name);
            service_description = itemView.findViewById(R.id.service_description);
            service_price = itemView.findViewById(R.id.service_price);
            service_duration = itemView.findViewById(R.id.service_duration);
        }

        public void bind(SubServiceMassage subserviceMassage) {

            service_image.setImageBitmap(subserviceMassage.getSubServiceMassageImage());
            service_name.setText(subserviceMassage.getSubServiceMassageName());
            service_description.setText(subserviceMassage.getSubServiceMassageDescription());
            service_duration.setText(subserviceMassage.getSubServiceMassageDuration());
            service_price.setText(subserviceMassage.getSubServiceMassagePrice());
        }
    }
}
