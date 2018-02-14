package com.strangeman.alarmclock.activities;

import android.support.v4.app.Fragment;

import com.strangeman.alarmclock.fragment.NapEditFragment;

/**
 * 小睡界面activity
 * Created by panzhi on 2018/2/3.
 */

public class NapEditActivity extends SingleFragmentDialogActivity {

    @Override
    protected Fragment createFragment() {
        return new NapEditFragment();
    }

}