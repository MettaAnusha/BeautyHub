//package com.example.beautyhub.adapters;
//
//import android.content.Context;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.beautyhub.R;
//import com.example.beautyhub.servercommunication.Appointment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
//
//    private Context mContext;
//
//    private List<Appointment> appointment = new ArrayList<>();
//
//    public AppointmentAdapter(Context context, List<Appointment> appointment) {
//        mContext = context;
//        this.appointment = appointment;
//    }
//
//    @NonNull
//    @Override
//    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.booking_item, parent, false);
//        return new AppointmentViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
//        Appointment appointment1 = appointment.get(position);
//        holder.bind(appointment1);
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return appointment.size();
//    }
//
//    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
//
//
//        private TextView proname;
//        private TextView subservicename;
//        private TextView date;
//        private TextView servicetime;
//        private TextView serviceprice;
//        private Button cancelButton;
//
//        public AppointmentViewHolder(@NonNull View itemView) {
//            super(itemView);
//            proname = itemView.findViewById(R.id.proname);
//            subservicename = itemView.findViewById(R.id.subservicename);
//            date = itemView.findViewById(R.id.date);
//            servicetime = itemView.findViewById(R.id.servicetime);
//            serviceprice = itemView.findViewById(R.id.serviceprice);
//            cancelButton = itemView.findViewById(R.id.button19);
//        }
//
//        public void bind(Appointment appointment1) {
//
//            proname.setText(appointment1.getProName());
//            subservicename.setText(appointment1.getSubServiceName());
//            date.setText(appointment1.getDate());
//            servicetime.setText(appointment1.getTime());
//            serviceprice.setText(appointment1.getSubServicePrice());
//            cancelButton.setOnClickListener(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//                                                    int position = getAdapterPosition();
//                                                    if (position != RecyclerView.NO_POSITION) {
//                                                        appointment.remove(position);
//                                                        notifyItemRemoved(position);
//
//                                                    }
//                                                }
//                                            });
//
//
//            Log.d("appointment adapter","appointmentadapter"+appointment1.getProName());
//            Log.d("appointment adapter","appointmentadapter"+appointment1.getSubServiceName());
//            Log.d("appointment adapter","appointmentadapter"+appointment1.getDate());
//            Log.d("appointment adapter","appointmentadapter"+appointment1.getTime());
//            Log.d("appointment adapter","appointmentadapter"+appointment1.getSubServicePrice());
//        }
//    }
//}


package com.example.beautyhub.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.beautyhub.R;
import com.example.beautyhub.servercommunication.Appointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private Context mContext;
    private List<Appointment> appointmentList = new ArrayList<>();
    private OnCancelButtonClickListener cancelButtonClickListener;

    public AppointmentAdapter(Context context, List<Appointment> appointmentList) {
        mContext = context;
        this.appointmentList = appointmentList;
    }

    public interface OnCancelButtonClickListener {
        void onCancelButtonClick(Appointment appointment);
    }

    public void setOnCancelButtonClickListener(OnCancelButtonClickListener listener) {
        this.cancelButtonClickListener = listener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.booking_item, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.bind(appointment);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private TextView proname;
        private TextView subservicename;
        private TextView date;
        private TextView servicetime;
        private TextView serviceprice;
        private Button cancelButton;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            proname = itemView.findViewById(R.id.proname);
            subservicename = itemView.findViewById(R.id.subservicename);
            date = itemView.findViewById(R.id.date);
            servicetime = itemView.findViewById(R.id.servicetime);
            serviceprice = itemView.findViewById(R.id.serviceprice);
            cancelButton = itemView.findViewById(R.id.button19);
        }

        public void bind(final Appointment appointment) {
            proname.setText(appointment.getProName());

            subservicename.setText(appointment.getSubServiceName());

            date.setText(appointment.getDate());

            servicetime.setText(appointment.getTime());

            serviceprice.setText(appointment.getSubServicePrice());
//
//            try {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                Date appointmentDate = sdf.parse(appointment.getTime());
//                Log.d("date","date in adapter"+appointment.getTime());
//                Date currentDate = new Date();
//                if (appointmentDate != null && appointmentDate.after(currentDate)) {
//                    cancelButton.setEnabled(true);
//                } else {
//                    // Disable the cancel button
//                    cancelButton.setEnabled(false);// Black out the button
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        appointmentList.remove(position);
                        notifyItemRemoved(position);
                        if (cancelButtonClickListener != null) {
                            cancelButtonClickListener.onCancelButtonClick(appointment);
                        }
                    }
                }
            });
        }
    }
}
