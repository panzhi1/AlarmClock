package com.strangeman.alarmclock.listener;

import android.view.View;

/**
 * Created by panzhi on 2018/1/29.
 */

public interface OnItemClickListener {
    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}
