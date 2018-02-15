package com.strangeman.alarmclock.activities;

import android.support.v4.app.Fragment;

import com.strangeman.alarmclock.fragment.ThemeFragment;

/**
 * Created by Administrator on 2018/2/14.
 */

public class ThemeActivity extends SingleFragmentOrdinaryActivity {
    @Override
    protected Fragment createFragment() {
        return new ThemeFragment();
    }
}




