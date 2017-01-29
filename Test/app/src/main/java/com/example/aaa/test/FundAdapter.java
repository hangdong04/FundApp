package com.example.aaa.test;

/**
 * Created by AAA on 26/1/2560.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FundAdapter extends BaseAdapter{
    private Context mContext;
    private String[] sName;
    private String[] fName;
    private String[] nav;

    public FundAdapter(Context context, String[] sName, String[] fName, String[] nav) {
        this.mContext= context;
        this.sName = sName;
        this.fName = fName;
        this.nav = nav;
    }

    public int getCount() {
        return sName.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.listview_row, parent, false);

        TextView sNameText = (TextView)view.findViewById(R.id.sName);
        sNameText.setText(sName[position]);
        TextView fNameText = (TextView)view.findViewById(R.id.fName);
        fNameText.setText(fName[position]);
        TextView navText = (TextView)view.findViewById(R.id.nav);
        navText.setText(nav[position]);
        return view;
    }
}
