package com.example.aaa.test;

/**
 * Created by AAA on 26/1/2560.
 */
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aaa.test.model.Fund;

import java.util.List;

public class FundAdapter_RV extends RecyclerView.Adapter <FundAdapter_RV.ViewHolder>{
    private Context mContext;
    private List<Fund> funds;

    LayoutInflater inflater;

    public FundAdapter_RV(Context context, List<Fund> funds) {
        super();
        this.mContext= context;
        this.funds = funds;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView sNameText;
        TextView fNameText;
        TextView growthText;
        ViewHolder(View v){
            super(v);
            sNameText = (TextView)v.findViewById(R.id.sName);
            fNameText = (TextView)v.findViewById(R.id.fName);
            growthText = (TextView)v.findViewById(R.id.growth);
        }
    }

    @Override
    public FundAdapter_RV.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sNameText.setText(funds.get(position).getName());
        holder.fNameText.setText(funds.get(position).getName_th());
        Float year_return;
        year_return = funds.get(position).getOne();
        if (year_return < 0){
            holder.growthText.setTextColor(ContextCompat.getColor(mContext,R.color.textRed));
        }
        else {
            holder.growthText.setTextColor(ContextCompat.getColor(mContext,R.color.textGreen));
        }
        String returnPoint;
        returnPoint = String.format("%.2f %%",funds.get(position).getOne());
        holder.growthText.setText(returnPoint);
    }

    @Override
    public int getItemCount() {
        return funds.size();
    }
}
