package com.lipeng.materialanimations;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lipeng.materialanimations.databinding.ActivityTransition1Binding;

public class AnimationsActivity1 extends BaseDetailActivity {

    private ImageView square;
    private ViewGroup viewRoot;
    private boolean sizeChange;
    private int savedWidth;
    private boolean positionChanged;
    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void setupWindowAnimations(){
        getWindow().setReenterTransition(new Fade());
    }

    @Override
    protected void bindData(){
        ActivityTransition1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_animations1);
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setTransition1Sample(sample);
    }

    @Override
    protected void setupLayout(){
        square = findViewById(R.id.square_green);
        viewRoot = findViewById(R.id.sample3_root);
        findViewById(R.id.sample3_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout();
            }
        });
        findViewById(R.id.sample3_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePosition();
            }
        });
        findViewById(R.id.sample3_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnimationsActivity1.this, AnimationsActivity2.class);
                i.putExtra(EXTRA_SAMPLE, sample);
                transitionTo(i);
            }
        });
    }

    private void changeLayout(){
        TransitionManager.beginDelayedTransition(viewRoot);
        ViewGroup.LayoutParams params = square.getLayoutParams();
        if (sizeChange){
            params.width = savedWidth;
        }else {
            savedWidth = params.width;
            params.width = 200;
        }

        sizeChange = !sizeChange;
        square.setLayoutParams(params);
    }

    private void changePosition(){
        TransitionManager.beginDelayedTransition(viewRoot);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) square.getLayoutParams();
        if (positionChanged){
            layoutParams.gravity = Gravity.CENTER;
        }else {
            layoutParams.gravity = Gravity.LEFT;
        }

        positionChanged = !positionChanged;
        square.setLayoutParams(layoutParams);
    }
}
