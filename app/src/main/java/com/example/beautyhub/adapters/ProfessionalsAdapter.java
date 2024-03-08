package com.example.beautyhub.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhub.R;
import com.example.beautyhub.activities.Booking_Screen1;
import com.example.beautyhub.servercommunication.Professionals;

import java.util.ArrayList;
import java.util.List;

public class ProfessionalsAdapter extends RecyclerView.Adapter<ProfessionalsAdapter.ProfessionalsViewHolder> {

    private Context mContext;

    private List<Professionals> professionals = new ArrayList<>();

    public ProfessionalsAdapter(Context context, List<Professionals> professionals) {
        mContext = context;
        this.professionals = professionals;
    }

    @NonNull
    @Override
    public ProfessionalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.professional_item_layout, parent, false);
        return new ProfessionalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessionalsViewHolder holder, int position) {
        Professionals proProfessionals = professionals.get(position);
        holder.bind(proProfessionals);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String proname=proProfessionals.getProName();
                editor.putString("proname", proname);

                Log.d("ProfessionalAdapter1","ProfessionalAdapter1"+proname);
                editor.apply();



                Intent intent = new Intent(mContext, Booking_Screen1.class);


                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return professionals.size();
    }

    public class ProfessionalsViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView name;
        private TextView pro;
        private TextView rate;


        public ProfessionalsViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            rate = itemView.findViewById(R.id.rate);
            pro = itemView.findViewById(R.id.pro);
        }

        public void bind(Professionals proProfessionals) {

            img.setImageBitmap(proProfessionals.getProImage());
            name.setText(proProfessionals.getProName());
            rate.setText(proProfessionals.getRating());
            pro.setText(proProfessionals.getProfession());

        }
    }
}
