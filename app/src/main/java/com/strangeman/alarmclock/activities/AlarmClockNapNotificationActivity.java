package com.strangeman.alarmclock.activities;

import android.os.Bundle;

import com.strangeman.alarmclock.bean.AlarmClock;
import com.strangeman.alarmclock.common.AlarmClockCommon;
import com.strangeman.alarmclock.util.ActivityCollector;
import com.strangeman.alarmclock.util.MyUtil;

/**
 * Created by Administrator on 2018/2/1.
 */

public class AlarmClockNapNotificationActivity extends BaseActivitySimple{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlarmClock alarmClock = getIntent().getParcelableExtra(
                AlarmClockCommon.ALARM_CLOCK);
        // 关闭小睡
        MyUtil.cancelAlarmClock(this, -alarmClock.getId());
        ActivityCollector.addActivity(this);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
