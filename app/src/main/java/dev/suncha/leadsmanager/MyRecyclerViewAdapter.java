package dev.suncha.leadsmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Sunny on 7/30/2015.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    DatabaseHelper databaseHelper;
    private ArrayList<Lead> leadinfo;



    public MyRecyclerViewAdapter(ArrayList<Lead> leadinfo) {
        this.leadinfo = leadinfo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.companyname.setText(leadinfo.get(position).getCompany_name());
        holder.personname.setText(leadinfo.get(position).getPerson_name());
        holder.personemail.setText(leadinfo.get(position).getPerson_email());
        holder.personphone.setText(leadinfo.get(position).getPerson_mobile());
    }

    @Override
    public int getItemCount() {
        return leadinfo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        ImageView checkIcon;
        TextView companyname;
        TextView personname;
        TextView personemail;
        TextView personphone;
        RelativeLayout relativeLayout;
        private final Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.mainLayout);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            checkIcon = (ImageView) itemView.findViewById(R.id.checkIcon);
            companyname = (TextView) itemView.findViewById(R.id.companyname);
            personname = (TextView) itemView.findViewById(R.id.personname);
            personemail = (TextView) itemView.findViewById(R.id.personemail);
            personphone = (TextView) itemView.findViewById(R.id.personphone);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context,DisplayLeadDetails.class);
            context.startActivity(intent);
        }
    }
}
