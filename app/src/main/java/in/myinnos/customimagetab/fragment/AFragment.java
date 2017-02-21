package in.myinnos.customimagetab.fragment;

/**
 * Created by 10 on 21-02-2017.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AFragment extends Fragment {
    private String mTitle = "A";

    public static final String TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }

        TextView tv = new TextView(getActivity());
        tv.setTextSize(50);
        tv.setTextColor(Color.parseColor("#ff000000"));
        tv.setBackgroundColor(Color.parseColor("#ffffffff"));
        tv.setText(mTitle);
        tv.setGravity(Gravity.CENTER);
        return tv;

    }
}