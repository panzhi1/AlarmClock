package com.strangeman.alarmclock.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.strangeman.alarmclock.R;
import com.strangeman.alarmclock.fragment.AlarmClockEditFragment;
import com.strangeman.alarmclock.util.ActivityCollector;

/**
 * Created by panzhi on 2018/2/3.
 */

public class AlarmClockEditActivity extends SingleFragmentOrdinaryActivity{


    @Override
    protected Fragment createFragment() {
        return new AlarmClockEditFragment();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 按下返回键开启移动退出动画
        overridePendingTransition(0, R.anim.move_out_bottom);
    }



}
