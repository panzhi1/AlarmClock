package com.strangeman.alarmclock.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.strangeman.alarmclock.R;
import com.strangeman.alarmclock.fragment.AlarmClockNewFragment;
import com.strangeman.alarmclock.util.ActivityCollector;

/**
 * Created by panzhi on 2018/2/3.
 */

public class AlarmClockNewActivity extends SingleFragmentOrdinaryActivity {

    @Override
    protected Fragment createFragment() {
        return new AlarmClockNewFragment();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 按下返回键开启渐变缩小动画
        overridePendingTransition(0, R.anim.zoomout);
    }


}