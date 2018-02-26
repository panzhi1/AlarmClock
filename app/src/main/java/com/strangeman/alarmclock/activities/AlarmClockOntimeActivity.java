package com.strangeman.alarmclock.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.strangeman.alarmclock.fragment.AlarmClockOntimeFragment;
import com.strangeman.alarmclock.util.ActivityCollector;

/**
 * Created by Administrator on 2018/1/30.
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


