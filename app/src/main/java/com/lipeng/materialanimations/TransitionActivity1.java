package com.lipeng.materialanimations;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.View;

import com.lipeng.materialanimations.databinding.ActivityTransition1Binding;

/**
 * Created by lipeng on 2017/12/5.
 */

public class TransitionActivity1 extends BaseDetailActivity {
    private Sample sample;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindData(){
        ActivityTransition1Binding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_transition1);
        sample = (Sample)getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setTransition1Sample(sample);
    }

    @Override
    protected void setupWindowAnimations(){
        Visibility enterTransition = buildEnterTransition();
        //进入过渡动画
        getWindow().setEnterTransition(enterTransition);
    }

    @Override
    protected void setupLayout(){
        findViewById(R.id.sample1_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransitionActivity1.this, TransitionActivity2.class);
                i.putExtra(EXTRA_SAMPLE, sample)
                    .putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);
            }
        });

        findViewById(R.id.sample1_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransitionActivity1.this, TransitionActivity2.class);
                i.putExtra(EXTRA_SAMPLE, sample)
                        .putExtra(EXTRA_TYPE, TYPE_XML);
                transitionTo(i);
            }
        });

        findViewById(R.id.sample1_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransitionActivity1.this, TransitionActivity3.class);
                i.putExtra(EXTRA_SAMPLE, sample)
                        .putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
                transitionTo(i);
            }
        });

        findViewById(R.id.sample1_button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransitionActivity1.this, TransitionActivity3.class);
                i.putExtra(EXTRA_SAMPLE, sample)
                        .putExtra(EXTRA_TYPE, TYPE_XML);
                transitionTo(i);
            }
        });

        findViewById(R.id.sample1_button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visibility returnTransition = buildReturnTransition();
                //返回时过渡动画
                getWindow().setReturnTransition(returnTransition);
                finishAfterTransition();
            }
        });

        findViewById(R.id.sample1_button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    private Visibility buildEnterTransition(){
        Fade enterTransition = new Fade();
        enterTransition.setDuration(500);
        enterTransition.excludeTarget(R.id.square_red, true);
        return enterTransition;
    }

    private Visibility buildReturnTransition(){
        Visibility returnTransition = new Slide();
        returnTransition.setDuration(500);
        return returnTransition;
    }
}
