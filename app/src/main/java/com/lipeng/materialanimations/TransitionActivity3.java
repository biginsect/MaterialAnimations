package com.lipeng.materialanimations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;

import com.lipeng.materialanimations.databinding.ActivityTransition3Binding;

/**
 * Created by lipeng on 2017/12/5.
 */

public class TransitionActivity3 extends BaseDetailActivity {
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindData(){
        ActivityTransition3Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_transition3);
        Sample sample = (Sample)getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        binding.setTransition3Sample(sample);
    }

    @Override
    protected void setupWindowAnimations(){
        Transition transition ;

        if (type == TYPE_PROGRAMMATICALLY){
            transition = buildEnterTransition();
        }else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom);
        }
        getWindow().setEnterTransition(transition);
    }

    @Override
    protected void setupLayout(){
        findViewById(R.id.exit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    private Visibility buildEnterTransition(){
        Slide enterTransition = new Slide();
        enterTransition.setDuration(500);
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }
}
