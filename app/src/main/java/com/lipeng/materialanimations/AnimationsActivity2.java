package com.lipeng.materialanimations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lipeng.materialanimations.databinding.ActivityTransition2Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipeng on 2017/12/5.
 */

public class AnimationsActivity2 extends BaseDetailActivity {
    private static final int DELAY = 100;
    private Scene scene0
            , scene1
            , scene2
            , scene3
            , scene4;
    private final List<View> viewToAnimate = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void bindData(){
        ActivityTransition2Binding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_animations2 );
        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setTransition2Sample(sample);
    }

    @Override
    protected void setupWindowAnimations(){
        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(
                R.transition.slide_from_bottom
        ));
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                getWindow().getEnterTransition().removeListener(this);
                TransitionManager.go(scene0);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    @Override
    protected void setupLayout(){
        final ViewGroup activityRoot = findViewById(R.id.buttons_group);
        ViewGroup sceneRoot = findViewById(R.id.scene_root);

        scene0 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene0, this);
        scene0.setEnterAction(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < viewToAnimate.size(); i++){
                    View child = viewToAnimate.get(i);
                    child.animate()
                            .setStartDelay(i * DELAY)
                            .scaleX(1)
                            .scaleY(1);
                }
            }
        });
        scene0.setExitAction(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(activityRoot);
                View title = scene0.getSceneRoot().findViewById(R.id.scene0_title);
                title.setScaleX(0);
                title.setScaleY(0);
            }
        });

        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene1, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene2, this);
        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene3, this);
        scene4 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene4, this);

        Button button1 = findViewById(R.id.sample3_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene1, new ChangeBounds());
            }
        });
        Button button2 = findViewById(R.id.sample3_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene2, TransitionInflater.from(AnimationsActivity2.this).
                        inflateTransition(R.transition.slide_and_changebounds));
            }
        });
        Button button3 = findViewById(R.id.sample3_button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene3, TransitionInflater.from(AnimationsActivity2.this).
                        inflateTransition(R.transition.slide_and_changebounds_sequential));
            }
        });
        Button button4 = findViewById(R.id.sample3_button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene4, TransitionInflater.from(AnimationsActivity2.this).
                        inflateTransition(R.transition.slide_and_changebounds_sequential_with_interpolators));
            }
        });

        viewToAnimate.add(button1);
        viewToAnimate.add(button2);
        viewToAnimate.add(button3);
        viewToAnimate.add(button4);
    }
}
