package com.roadhelp.border.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.roadhelp.border.Fragment2;
import com.roadhelp.border.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                //fragment = new Fragment1Chat();
                fragment = fragments.get(1);
                break;
            case 1:
                fragment =fragments.get(2);
                break;
            case 2:
                // так не надо делать, надо исправить когда появится чат и камеры.
                // при выборе 3 вкладки берётся 0 фрагмент из массива fragments, который мы добавили методом addFragment
                fragment = fragments.get(0);
                break;
        }
        return fragment;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }

    public void addFragment(Fragment fragment) {

        fragments.add(fragment);
    }
}