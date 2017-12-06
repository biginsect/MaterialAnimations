package com.lipeng.materialanimations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by lipeng on 2017/12/5.
 */

public class SharedElementFragment2 extends Fragment {
    private static final String EXTRA_SAMPLE = "sample";

    public static SharedElementFragment2 newInstance(Sample sample){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        SharedElementFragment2 fragment2 = new SharedElementFragment2();
        fragment2.setArguments(args);
        return fragment2;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sharedelement_fragment2,
                container, false);
        Sample sample = (Sample)getArguments().getSerializable(EXTRA_SAMPLE);

        ImageView squareBlue = view.findViewById(R.id.square_blue);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.color);

        return view;
    }
}
