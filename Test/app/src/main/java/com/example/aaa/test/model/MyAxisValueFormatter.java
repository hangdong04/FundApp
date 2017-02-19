package com.example.aaa.test.model;

/**
 * Created by phatt on 19/2/2560.
 */


import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class MyAxisValueFormatter implements IAxisValueFormatter
{
    protected String[] xVal = new String[]{
            "1 ปี", "3 ปี", "5 ปี", "7 ปี"
    };
    public MyAxisValueFormatter() {
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        float idx = value / 10f;
        return xVal[(int)idx];
    }
}