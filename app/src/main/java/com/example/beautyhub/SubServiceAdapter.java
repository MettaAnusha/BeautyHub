package com.example.beautyhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhub.SubService;
import com.example.beautyhub.R;

import java.util.List;

public class SubServiceAdapter extends RecyclerView.Adapter<SubServiceAdapter.SubServiceViewHolder> {

    private Context mContext;
    private List<SubService> mSubServices;

    public SubServiceAdapter(Context context, List<SubService> subServices) {
        mContext = context;
        mSubServices = subServices;
    }

    @NonNull
    @Override
    public SubServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.service_item_layout, parent, false);
        return new SubServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceViewHolder holder, int position) {
        SubService subService = mSubServices.get(position);
        holder.bind(subService);
    }

    @Override
    public int getItemCount() {
        return mSubServices.size();
    }

    public class SubServiceViewHolder extends RecyclerView.ViewHolder {
        private ImageView mServiceImage;
        private TextView mServiceName;
        private TextView mServiceDescription;
        private TextView mServicePrice;
        private TextView mServiceDuration;

        public SubServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            mServiceImage = itemView.findViewById(R.id.service_image);
            mServiceName = itemView.findViewById(R.id.service_name);
            mServiceDescription = itemView.findViewById(R.id.service_description);
            mServicePrice = itemView.findViewById(R.id.service_price);
            mServiceDuration = itemView.findViewById(R.id.service_duration);
        }

        public void bind(SubService subService) {
            mServiceImage.setImageBitmap(subService.getSubServiceImage());
            mServiceName.setText(subService.getSubServiceName());
            mServiceDescription.setText(subService.getSubServiceDescription());
            mServicePrice.setText(subService.getSubServicePrice());
            mServiceDuration.setText(subService.getSubServiceDuration());
        }
    }
}
