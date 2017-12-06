package com.lipeng.materialanimations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by lipeng on 2017/12/4.
 * 基类，具体布局与动画的实现交由子类
 */

public abstract class BaseDetailActivity extends AppCompatActivity {
    static final String EXTRA_SAMPLE = "sample";
    static final String EXTRA_TYPE = "type";
    static final int TYPE_PROGRAMMATICALLY = 0;
    static final int TYPE_XML = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupLayout();
        setupToolBar();
        setupWindowAnimations();
    }

    void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    protected abstract void bindData();
    protected abstract void setupLayout();
    protected abstract void setupWindowAnimations();

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressWarnings("unchecked")
    void transitionTo(Intent intent){
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent, optionsCompat.toBundle());
    }
}
