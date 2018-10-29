package com.aayu.aayu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aayu.aayu.Model.Cghs;
import com.aayu.aayu.R;

import java.util.List;

public class CghsAdapter extends RecyclerView.Adapter<CghsAdapter.ListHolder> {

    private List<Cghs> list;
    private LayoutInflater inflater;
    Context context;

    public CghsAdapter(List<Cghs> list, Context context){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CghsAdapter.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_cghs, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CghsAdapter.ListHolder holder, int position) {
        holder.name.setText(list.get(position).getCghs_name());
        holder.address.setText(list.get(position).getCghs_address());
        holder.contact.setText(list.get(position).getCghs_contact());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder{
        private TextView name, address, contact;
        public ListHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.cghs_address);
            contact = itemView.findViewById(R.id.contact);
        }
    }
}
