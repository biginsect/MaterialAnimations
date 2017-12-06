package com.lipeng.materialanimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.lipeng.materialanimations.databinding.ActivityRevealBinding;

/**
 * Created by lipeng on 2017/12/5.
 */

public class RevealActivity extends BaseDetailActivity implements View.OnTouchListener{
    private static final int DELAY = 100;
    private RelativeLayout bgViewGroup;
    private Toolbar toolbar;
    private Interpolator interpolator;
    private TextView body;
    private View btnRed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindData() {
        ActivityRevealBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_reveal);
        Sample sample = (Sample)getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setReveal1Sample(sample);
    }

    @Override
    protected void setupLayout() {
        bgViewGroup = findViewById(R.id.reveal_root);
        toolbar = findViewById(R.id.toolbar);
        body = findViewById(R.id.sample_body);
        findViewById(R.id.square_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealGreen();
            }
        });
        findViewById(R.id.square_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealRed();
            }
        });
        btnRed = findViewById(R.id.square_red);
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealBlue();
            }
        });
        findViewById(R.id.square_yellow).setOnTouchListener(this);
    }

    @Override
    protected void setupWindowAnimations() {
        interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
        setupEnterAnimations();
        setupExitAnimations();
    }

    private void setupEnterAnimations(){
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.changebounds_with_arcmotion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                hideTarget();
                animateRevealShow(toolbar);
                animateButtonsIn();
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

    private void setupExitAnimations(){
        Fade returnTransition = new Fade();
        getWindow().setReturnTransition(returnTransition);
        returnTransition.setDuration(300);
        returnTransition.setStartDelay(300);
        returnTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                transition.removeListener(this);
                animateButtonOut();
                animateRevealHide(bgViewGroup);
            }

            @Override
            public void onTransitionEnd(Transition transition) {

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

    private void revealBlue(){
        animateButtonOut();
        Animator animator = animateRevealColorFromCoordinates(bgViewGroup, R.color.sample_blue,
                bgViewGroup.getWidth() / 2, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animateButtonsIn();
            }
        });

        body.setText(R.string.reveal_body4);
        body.setTextColor(ContextCompat.getColor(this, R.color.theme_blue_background));
    }

    private void revealRed(){
        final ViewGroup.LayoutParams params = btnRed.getLayoutParams();
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.changebounds_with_arcmotion);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                animateRevealColor(bgViewGroup, R.color.sample_red);
                body.setText(R.string.reveal_body3);
                body.setTextColor(ContextCompat.getColor(RevealActivity.this, R.color.theme_red_background));
                btnRed.setLayoutParams(params);
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
        TransitionManager.beginDelayedTransition(bgViewGroup, transition);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        btnRed.setLayoutParams(layoutParams);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (v.getId() == R.id.square_yellow){
                revealYellow(event.getRawX(), event.getRawY());
            }
        }

        return false;
    }

    private void revealYellow(float x, float y){
        animateRevealColorFromCoordinates(bgViewGroup, R.color.sample_yellow, (int)x, (int)y);
        body.setText(R.string.reveal_body1);
        body.setTextColor(ContextCompat.getColor(this, R.color.theme_yellow_background));
    }

    private void revealGreen(){
        animateRevealColor(bgViewGroup, R.color.sample_green);
        body.setText(R.string.reveal_body2);
        body.setTextColor(ContextCompat.getColor(this, R.color.theme_green_background));
    }

    private void hideTarget(){
        findViewById(R.id.shared_target).setVisibility(View.GONE);
    }

    private void animateButtonsIn(){
        for (int i = 0; i < bgViewGroup.getChildCount(); i++){
            View child = bgViewGroup.getChildAt(i);
            child.animate()
                    .setStartDelay(100 + i * DELAY)
                    .setInterpolator(interpolator)
                    .alpha(1)
                    .scaleX(1)
                    .scaleY(1);
        }
    }

    private void animateButtonOut(){
        for (int i = 0; i < bgViewGroup.getChildCount(); i++){
            View child = bgViewGroup.getChildAt(i);
            child.animate()
                    .setStartDelay(i)
                    .setInterpolator(interpolator)
                    .alpha(0)
                    .scaleX(0f)
                    .scaleY(0f);
        }
    }

    private void animateRevealShow(View viewRoot){
        int x = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int y = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    private void animateRevealColor(ViewGroup viewRoot, @ColorRes int color){
        int x = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int y = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        animateRevealColorFromCoordinates(viewRoot, color, x, y);
    }

    private Animator animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y){
        float finalRadius = (float)Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(ContextCompat.getColor(this, color));
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
        return animator;
    }

    private void animateRevealHide(final View viewRoot){
        int x = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int y = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int initialRadius = viewRoot.getWidth();
        Animator animator = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, initialRadius, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewRoot.setVisibility(View.INVISIBLE);
            }
        });
        animator.setDuration(300)
                .start();
    }
}
