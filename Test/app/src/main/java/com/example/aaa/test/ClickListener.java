package com.example.aaa.test;

import android.view.View;

/**
 * Created by phatt on 19/2/2560.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}