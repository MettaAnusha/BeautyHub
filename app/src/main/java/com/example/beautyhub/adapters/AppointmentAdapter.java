package com.example.beautyhub.adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhub.R;
import com.example.beautyhub.servercommunication.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private Context mContext;

    private List<Appointment> appointment = new ArrayList<>();

    public AppointmentAdapter(Context context, List<Appointment> appointment) {
        mContext = context;
        this.appointment = appointment;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.booking_item, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment1 = appointment.get(position);
        holder.bind(appointment1);

    }

    @Override
    public int getItemCount() {
        return appointment.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {


        private TextView proname;
        private TextView subservicename;
        private TextView date;
        private TextView servicetime;
        private TextView serviceprice;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            proname = itemView.findViewById(R.id.proname);
            subservicename = itemView.findViewById(R.id.subservicename);
            date = itemView.findViewById(R.id.date);
            servicetime = itemView.findViewById(R.id.servicetime);
            serviceprice = itemView.findViewById(R.id.serviceprice);
        }

        public void bind(Appointment appointment1) {

            proname.setText(appointment1.getProName());
            subservicename.setText(appointment1.getSubServiceName());
            date.setText(appointment1.getDate());
            servicetime.setText(appointment1.getTime());
            serviceprice.setText(appointment1.getSubServicePrice());

            Log.d("appointment adapter","appointmentadapter"+appointment1.getProName());
            Log.d("appointment adapter","appointmentadapter"+appointment1.getSubServiceName());
            Log.d("appointment adapter","appointmentadapter"+appointment1.getDate());
            Log.d("appointment adapter","appointmentadapter"+appointment1.getTime());
            Log.d("appointment adapter","appointmentadapter"+appointment1.getSubServicePrice());
        }
    }
}
