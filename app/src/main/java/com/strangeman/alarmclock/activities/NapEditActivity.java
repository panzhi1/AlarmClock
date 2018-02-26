package com.strangeman.alarmclock.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.strangeman.alarmclock.fragment.NapEditFragment;
import com.strangeman.alarmclock.util.ActivityCollector;

/**
 * Created by Administrator on 2018/2/3.
 */

public class NapEditActivity extends SingleFragmentDialogActivity {





    @Override
    protected Fragment createFragment() {
        return new NapEditFragment();
    }

}