package com.strangeman.alarmclock.activities;

import android.support.v4.app.Fragment;

import com.strangeman.alarmclock.fragment.RingSelectFragment;

/**
 * Created by panzhi on 2018/2/3.
 */

public class RingSelectActivity extends SingleFragmentDialogActivity {

    @Override
    protected Fragment createFragment() {
        return new RingSelectFragment();
    }

}