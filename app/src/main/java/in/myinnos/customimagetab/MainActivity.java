package in.myinnos.customimagetab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.myinnos.customimagetab.fragment.AFragment;
import in.myinnos.customimagetab.fragment.BFragment;
import in.myinnos.customimagetab.fragment.CFragment;
import in.myinnos.customimagetab.fragment.DFragment;
import in.myinnos.customimagetablayout.ChangeColorTab;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        ChangeColorTab changeColorTab = (ChangeColorTab) findViewById(R.id.tabChangeColorTab);
        changeColorTab.setViewpager(mViewPager);
        // Attach page Adapter
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapterViewPager);

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        Fragment fragment;

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment = new AFragment();
                    return fragment;
                case 1:
                    fragment = new BFragment();
                    return fragment;
                case 2:
                    fragment = new CFragment();
                    return fragment;
                case 3:
                    fragment = new DFragment();
                    return fragment;
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }
    }
}