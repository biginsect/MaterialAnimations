package com.lipeng.materialanimations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;

import com.lipeng.materialanimations.databinding.ActivitySharedelementBinding;

/**
 * Created by lipeng on 2017/12/5.
 */

public class SharedElementActivity  extends BaseDetailActivity {
    private Sample sample;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindData(){
        sample = (Sample)getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        ActivitySharedelementBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sharedelement);
        binding.setSharedSample(sample);
    }

    @Override
    protected void setupWindowAnimations(){
        getWindow().getEnterTransition().setDuration(500);
    }

    @Override
    protected void setupLayout(){
        Slide slide = new Slide(Gravity.LEFT);
        slide.setDuration(500);
        //Create fragment and define their transitions
        SharedElementFragment1 fragment1 = SharedElementFragment1.newInstance(sample);
        fragment1.setReenterTransition(slide);
        fragment1.setExitTransition(slide);
        fragment1.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, fragment1)
                .commit();
    }
}
