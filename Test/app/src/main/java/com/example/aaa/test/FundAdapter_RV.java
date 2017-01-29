package com.example.aaa.test;

/**
 * Created by AAA on 26/1/2560.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FundAdapter_RV extends RecyclerView.Adapter <FundAdapter_RV.ViewHolder>{
    private Context mContext;
    private String[] sName;
    private String[] fName;
    private String[] nav;

    LayoutInflater inflater;

    public FundAdapter_RV(Context context, String[] sName, String[] fName, String[] nav) {
        super();
        this.mContext= context;
        this.sName = sName;
        this.fName = fName;
        this.nav = nav;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView sNameText;
        TextView fNameText;
        TextView navText;
        ViewHolder(View v){
            super(v);
            sNameText = (TextView)v.findViewById(R.id.sName);
            fNameText = (TextView)v.findViewById(R.id.fName);
            navText = (TextView)v.findViewById(R.id.nav);
        }
    }

    @Override
    public FundAdapter_RV.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sNameText.setText(sName[position]);
        holder.fNameText.setText(fName[position]);
        holder.navText.setText(nav[position]);
    }

    @Override
    public int getItemCount() {
        return sName.length;
    }
}
