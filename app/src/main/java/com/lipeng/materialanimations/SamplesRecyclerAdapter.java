package com.lipeng.materialanimations;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lipeng.materialanimations.databinding.RowSampleBinding;

import java.util.List;

/**
 * Created by lipeng on 2017/12/5.
 */

public class SamplesRecyclerAdapter extends RecyclerView.Adapter<SamplesRecyclerAdapter.SamplesViewHolder>{

    private final Activity activity;
    private final List<Sample> samples;

    public SamplesRecyclerAdapter(Activity activity, List<Sample> samples){
        this.activity = activity;
        this.samples = samples;
    }

    @Override
    public SamplesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowSampleBinding binding = RowSampleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SamplesViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final SamplesViewHolder holder, final int position) {
        final Sample sample = samples.get(holder.getAdapterPosition());
        holder.binding.setSample(sample);
        holder.binding.sampleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()){
                    case 0:
                        transitionToActivity(TransitionActivity1.class, sample);
                        break;
                    case 1:
                        transitionToActivity(SharedElementActivity.class, holder, sample);
                        break;
                    case 2:
                        transitionToActivity(AnimationsActivity1.class, sample);
                        break;
                    case 3:
                        transitionToActivity(RevealActivity.class, holder,
                                sample, R.string.transition_reveal1);
                        break;
                }
            }
        });
    }

    private void transitionToActivity(Class target, Sample sample){
        final Pair<View, String>[] pairs = TransitionHelper
                .createSafeTransitionParticipants(activity, true);
        startActivity(target, pairs, sample);
    }

    private void transitionToActivity(Class target, SamplesViewHolder holder, Sample sample){
        final Pair<View, String>[] pairs = TransitionHelper
                .createSafeTransitionParticipants(activity, false,
                new Pair<>(holder.binding.sampleIcon, activity.getString(R.string.square_blue_name)),
                new Pair<>(holder.binding.sampleName, activity.getString(R.string.sample_blue_title)));
        startActivity(target, pairs, sample);

    }

    private void transitionToActivity(
            Class target, SamplesViewHolder holder, Sample sample, int transitionName){
        final Pair<View, String>[] pairs = TransitionHelper
                .createSafeTransitionParticipants(activity, false,
                new Pair<>(holder.binding.sampleIcon, activity.getString(transitionName))
                );
        startActivity(target, pairs, sample);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, Sample sample){
        Intent i = new Intent(activity, target);
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        i.putExtra("sample", sample);
        activity.startActivity(i, optionsCompat.toBundle());
    }

    @Override
    public int getItemCount() {
        return samples.size();
    }

    class SamplesViewHolder extends RecyclerView.ViewHolder{
        final RowSampleBinding binding;
        public SamplesViewHolder(View rootView){
            super(rootView);
            binding = DataBindingUtil.bind(rootView);
        }
    }
}
