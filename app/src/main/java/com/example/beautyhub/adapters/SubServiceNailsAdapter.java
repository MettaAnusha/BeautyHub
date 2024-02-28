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
import com.example.beautyhub.servercommunication.SubServiceNails;

import java.util.ArrayList;
import java.util.List;

public class SubServiceNailsAdapter extends RecyclerView.Adapter<SubServiceNailsAdapter.SubServiceNailsViewHolder> {
    private Context mContext;

    private List<SubServiceNails> subservicenails = new ArrayList<>();

    public SubServiceNailsAdapter(Context context, List<SubServiceNails> subservicenails) {
        mContext = context;
        this.subservicenails = subservicenails;
    }


    @NonNull

    @Override
    public SubServiceNailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.service_item_layout, parent, false);
        return new SubServiceNailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceNailsViewHolder holder, int position) {
        SubServiceNails subserviceNails = subservicenails.get(position);
        holder.bind(subserviceNails);
    }

    @Override
    public int getItemCount() {
        return subservicenails.size();
    }

    public class SubServiceNailsViewHolder extends RecyclerView.ViewHolder {

        private ImageView service_image;
        private TextView service_name;
        private TextView service_description;
        private TextView service_price;
        private TextView service_duration;

        public SubServiceNailsViewHolder(@NonNull View itemView) {
            super(itemView);
            service_image = itemView.findViewById(R.id.service_image);
            service_name = itemView.findViewById(R.id.service_name);
            service_description = itemView.findViewById(R.id.service_description);
            service_price = itemView.findViewById(R.id.service_price);
            service_duration = itemView.findViewById(R.id.service_duration);
        }

        public void bind(SubServiceNails subserviceNails) {

            service_image.setImageBitmap(subserviceNails.getSubServiceNailsImage());
            service_name.setText(subserviceNails.getSubServiceNailsName());
            service_description.setText(subserviceNails.getSubServiceNailsDescription());
            service_duration.setText(subserviceNails.getSubServiceNailsDuration());
            service_price.setText(subserviceNails.getSubServiceNailsPrice());
        }
    }
}
