package com.lipeng.materialanimations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;

import com.lipeng.materialanimations.databinding.ActivityTransition2Binding;

/**
 * Created by lipeng on 2017/12/5.
 */

public class TransitionActivity2 extends BaseDetailActivity {
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindData(){
        ActivityTransition2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_transition2);
        Sample sample = (Sample)getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        binding.setTransition2Sample(sample);
    }

    @Override
    protected void setupWindowAnimations(){
        Transition transition;

        if (type == TYPE_PROGRAMMATICALLY){
            transition = buildEnterTransition();
        }else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
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

    private Transition buildEnterTransition(){
        Explode enterTransition = new Explode();
        enterTransition.setDuration(500);
        return enterTransition;
    }
}
