package com.example.beautyhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.beautyhub.SubService;
import com.example.beautyhub.R;

import java.util.List;

public class SubServiceAdapter extends ArrayAdapter<SubService> {

    private Context mContext;
    private List<SubService> mSubServices;

    public SubServiceAdapter(@NonNull Context context, List<SubService> subServices) {
        super(context, 0, subServices);
        mContext = context;
        mSubServices = subServices;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.service_item_layout, parent, false);
        }

        SubService currentSubService = mSubServices.get(position);

        ImageView imageView = listItem.findViewById(R.id.serviceimage5);
        imageView.setImageBitmap(currentSubService.getServiceImage());

        TextView serviceNameTextView = listItem.findViewById(R.id.servicename5);
        serviceNameTextView.setText(currentSubService.getSubServiceName());

        TextView servicePriceTextView = listItem.findViewById(R.id.serviceprice1);
        servicePriceTextView.setText(currentSubService.getPrice());

        return listItem;
    }
}
