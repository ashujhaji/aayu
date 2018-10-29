package com.aayu.aayu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aayu.aayu.Model.Doctors;
import com.aayu.aayu.R;

import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ListHolder> {

    private List<Doctors> list;
    private LayoutInflater inflater;
    Context context;

    public DoctorsAdapter(List<Doctors> list, Context context){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorsAdapter.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_doctors, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsAdapter.ListHolder holder, int position) {
        holder.name.setText(list.get(position).getDoc_name());
        holder.spec.setText(list.get(position).getSpecialisation());
        holder.timing.setText(list.get(position).getTiming());
        holder.days.setText(list.get(position).getDays());
        holder.place.setText(list.get(position).getPlace_id());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder{
        private TextView name, spec, timing, days, place;
        public ListHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            spec = itemView.findViewById(R.id.spec);
            timing = itemView.findViewById(R.id.timing);
            days = itemView.findViewById(R.id.days);
            place = itemView.findViewById(R.id.place);
        }
    }
}
