package com.example.rafproject2.adapter;

import android.content.Context;

import com.example.rafproject2.fragment.ChatFragment;
import com.example.rafproject2.fragment.ScheduleFragment;
import com.example.rafproject2.fragment.WallFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 3;
    private List<String> mTitles;

    public FragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        initTitles(context);
    }

    private void initTitles(Context context) {
        mTitles = new ArrayList<>();
        mTitles.add("Raspored");
        mTitles.add("Chat");
        mTitles.add("Wall");
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ScheduleFragment.newInstance();
            case 1:
                return ChatFragment.newInstance();
            case 2:
                return WallFragment.newInstance();

        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
