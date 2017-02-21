package in.myinnos.customimagetablayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ChangeColorTab extends LinearLayout {

    private ViewPager mViewPager;
    private List<ChangeColorItem> children = new ArrayList<>();
    private OnClickListener tabOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            clickTab(v);
        }
    };

    public ChangeColorTab(Context context) {
        this(context, null);
    }

    public ChangeColorTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChangeColorTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundResource(R.drawable.tab_layout_bg);
        setOrientation(HORIZONTAL);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!children.isEmpty()) return;
        for (int i = 0; i < getChildCount(); ++i) {
            children.add((ChangeColorItem) getChildAt(i));
        }
        for (ChangeColorItem item : children) {
            item.setOnClickListener(tabOnClick);
        }
        children.get(mViewPager.getCurrentItem()).setIconAlpha(1.0f);
    }

    public void setViewpager(ViewPager viewpager) {
        this.mViewPager = viewpager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    ChangeColorItem left = children.get(position);
                    ChangeColorItem right = children.get(position + 1);
                    left.setIconAlpha(1 - positionOffset);
                    right.setIconAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void clickTab(View v) {
        resetOtherTabs();
        int index = children.indexOf(v);
        children.get(index).setIconAlpha(1.0f);
        mViewPager.setCurrentItem(index, false);
    }


    private void resetOtherTabs() {
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setIconAlpha(0);
        }
    }
}
