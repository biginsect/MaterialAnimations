package com.lipeng.materialanimations;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Sample> samples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        setupSamples();
        setupToolbar();
        setupLayout();
    }

    private void setupWindowAnimations(){
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        getWindow().setReenterTransition(slide);
        getWindow().setExitTransition(slide);
    }

    private void setupSamples(){
        samples = Arrays.asList(
                new Sample(ContextCompat.getColor(this, R.color.sample_red),"Transitions"),
                new Sample(ContextCompat.getColor(this, R.color.sample_blue), "Shared Elements"),
                new Sample(ContextCompat.getColor(this, R.color.sample_green), "View animations"),
                new Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circular Reveal Animation")
        );
    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupLayout(){
        RecyclerView recyclerView = findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SamplesRecyclerAdapter adapter = new SamplesRecyclerAdapter(this, samples);
        recyclerView.setAdapter(adapter);
    }
}
