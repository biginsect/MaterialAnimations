package com.lipeng.materialanimations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by lipeng on 2017/12/5.
 */

public class SharedElementFragment1 extends Fragment {

    private static final String EXTRA_SAMPLE = "sample";

    public static SharedElementFragment1 newInstance(Sample sample){
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SAMPLE, sample);
        SharedElementFragment1 fragment1 = new SharedElementFragment1();
        fragment1.setArguments(bundle);
        return fragment1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sharedelement_fragment1, container, false);
        final Sample sample = (Sample)getArguments().getSerializable(EXTRA_SAMPLE);

        final ImageView squareBlue = view.findViewById(R.id.square_blue);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.color);

        view.findViewById(R.id.sample2_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(sample, squareBlue, false);
            }
        });
        view.findViewById(R.id.sample2_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(sample, squareBlue, true);
            }
        });

        return view;
    }

    private void addNextFragment(Sample sample, ImageView squareBlue, boolean overlap){
        SharedElementFragment2 fragment2 = SharedElementFragment2.newInstance(sample);

        Slide slide = new Slide();
        slide.setDuration(300);

        ChangeBounds changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(300);

        fragment2.setEnterTransition(slide);
        fragment2.setAllowEnterTransitionOverlap(overlap);
        fragment2.setAllowReturnTransitionOverlap(overlap);
        fragment2.setSharedElementEnterTransition(changeBoundsTransition);

        getFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, fragment2)
                .addToBackStack(null)
                .addSharedElement(squareBlue, getString(R.string.square_blue_name))
                .commit();
    }
}
