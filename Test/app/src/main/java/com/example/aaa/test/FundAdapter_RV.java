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

import com.example.aaa.test.EventBus.FABButtonSetupEvent;
import com.example.aaa.test.model.Fund;
import com.example.aaa.test.model.MyAxisValueFormatter;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.silencedut.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FundAdapter_RV extends RecyclerView.Adapter <FundAdapter_RV.ViewHolder>{
    private Context mContext;
    private List<Fund> funds;
    private HashSet<Integer> mExpandedPositionSet = new HashSet<>();

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
        ExpandableLayout expandableLayout ;
        HorizontalBarChart chart;
        ViewHolder(View v){
            super(v);
            sNameText = (TextView)v.findViewById(R.id.sName);
            fNameText = (TextView)v.findViewById(R.id.fName);
            growthText = (TextView)v.findViewById(R.id.growth);
            expandableLayout = (ExpandableLayout) v.findViewById(R.id.expandable_layout);
            chart = (HorizontalBarChart) v.findViewById(R.id.chart);
        }

        private void updateItem(final int position,final HorizontalBarChart chart) {
            expandableLayout.setOnExpandListener(new ExpandableLayout.OnExpandListener() {
                @Override
                public void onExpand(boolean expanded) {
                    registerExpand(position);

                }
            });
            expandableLayout.setExpand(mExpandedPositionSet.contains(position));

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
        chartSetup(position,holder.chart);

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
        holder.updateItem(position,holder.chart);
    }

    @Override
    public int getItemCount() {
        return funds.size();
    }

    private void registerExpand(int position) {
        if (mExpandedPositionSet.contains(position)) {
            removeExpand(position);
        }else {
            addExpand(position);
        }
    }

    private void removeExpand(int position) {
        mExpandedPositionSet.remove(position);
    }

    private void addExpand(int position) {
        mExpandedPositionSet.add(position);
    }
    private void chartSetup(int position, HorizontalBarChart chart){
        // chart.setHighlightEnabled(false);
        float one = funds.get(position).getOne();
        float three = funds.get(position).getThree();
        float five = funds.get(position).getFive();
        float seven = funds.get(position).getSeven();
        float[] per_growth = {one,three,five,seven};
        Arrays.sort(per_growth);
        float min = Math.round(per_growth[0]);
        if (min >0){
            System.out.println("######MIN:"+min);
            min = 0f;
        }else if (min > -20)
        {
            System.out.println("######MIN-20:"+min);
            min -= 10f;
        }
        else {
            System.out.println("######MIN-EL:"+min);
            min -= 2f;
        }
        float max = Math.round(per_growth[per_growth.length -1]);
        max = max+10f;
        chart.setDrawBarShadow(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setDrawValueAboveBar(true);
        chart.setTouchEnabled(false);
        chart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);

        chart.setDrawGridBackground(false);
        IAxisValueFormatter custom = new MyAxisValueFormatter();
        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xl.setTypeface(mTfLight);
        xl.setDrawAxisLine(false);
        xl.setDrawGridLines(false);
        xl.setGranularity(1f);
        xl.setValueFormatter(custom);


        YAxis yl = chart.getAxisLeft();
//        yl.setTypeface(mTfLight);
        yl.setDrawAxisLine(false);
        yl.setDrawGridLines(false);
        yl.setAxisMinimum(min); // this replaces setStartAtZero(true)
        yl.setDrawLabels(false);
//        yl.setInverted(true);

        YAxis yr = chart.getAxisRight();
//        yr.setTypeface(mTfLight);
        yr.setDrawAxisLine(false);
        yr.setDrawGridLines(false);
        yr.setDrawLabels(false);
//        yr.setAxisMinimum(max); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

        setData(position, chart);
        chart.setFitBars(true);
        chart.animateY(2500);

        Legend l = chart.getLegend();
        l.setEnabled(false);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setFormSize(16f);
//        l.setXEntrySpace(4f);
//        l.setWordWrapEnabled(false);

    }
    private void setData(int position, HorizontalBarChart chart) {

        float barWidth = 9f;
        float spaceForBar = 10f;
        float one = funds.get(position).getOne();
        float three = funds.get(position).getThree();
        float five = funds.get(position).getFive();
        float seven = funds.get(position).getSeven();

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0 * spaceForBar, one));
        yVals1.add(new BarEntry(1 * spaceForBar, three));
        yVals1.add(new BarEntry(2 * spaceForBar, five));
        yVals1.add(new BarEntry(3 * spaceForBar, seven));

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)chart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "ผลตอบแทนการลงทุน");


            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
//            data.setValueTypeface(mTfLight);
            data.setBarWidth(barWidth);
            data.setDrawValues(true);
            chart.setData(data);
            chart.invalidate();
        }
    }
}
