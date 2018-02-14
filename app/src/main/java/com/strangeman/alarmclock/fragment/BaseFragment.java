package com.strangeman.alarmclock.fragment;


import android.support.v4.app.Fragment;

import com.squareup.leakcanary.RefWatcher;


public class BaseFragment extends Fragment {
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
