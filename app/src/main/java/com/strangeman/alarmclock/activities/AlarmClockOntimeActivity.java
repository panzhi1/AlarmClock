package com.strangeman.alarmclock.activities;

import android.support.v4.app.Fragment;

import com.strangeman.alarmclock.fragment.AlarmClockOntimeFragment;

/**
 * Created by panzhi on 2018/1/30.
 */

public class AlarmClockOntimeActivity extends SingleFragmentDialogActivity {

    @Override
    protected Fragment createFragment() {
        return new AlarmClockOntimeFragment();
    }

    @Override
    public void onBackPressed() {
        // 禁用back键
    }
}


