package com.example.daybook_frontend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class StudentFragment extends Fragment {

    private ViewPager viewPager;
    private FragmentPagerAdapter adapterViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        return view;
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 5;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return DayFragment.newInstance(position);
        }

        // Returns the total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Day " + (position + 1);
        }
    }
}