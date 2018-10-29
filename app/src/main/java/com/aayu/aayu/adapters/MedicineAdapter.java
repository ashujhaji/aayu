package com.aayu.aayu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aayu.aayu.Model.Medicines;
import com.aayu.aayu.R;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ListViewHolder> {

    private List<Medicines> list;
    private LayoutInflater inflater;
    Context context;

    public MedicineAdapter(List<Medicines> list, Context context){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public MedicineAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_medicine_cards, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ListViewHolder holder, int position) {
        holder.med_name.setText(list.get(position).getMed_name());
        holder.med_status.setText(list.get(position).getAvailability());
        if (list.get(position).getAvailability().equals("available")){
            holder.med_status.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }else {
            holder.med_status.setTextColor(context.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{

        TextView med_name, med_status;

        public ListViewHolder(View itemView) {
            super(itemView);
            med_name = itemView.findViewById(R.id.med_name);
            med_status = itemView.findViewById(R.id.med_status);
        }
    }
}
