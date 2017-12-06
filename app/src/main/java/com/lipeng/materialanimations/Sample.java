package com.lipeng.materialanimations;

import android.databinding.BindingAdapter;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by lipeng on 2017/12/4.
 */

public class Sample implements Serializable {
    final int color;
    private final String name;

    public Sample(int color, String name){
        this.color = color;
        this.name = name;
    }

    @BindingAdapter("bind:colorTint")
    public static void setColorTint(ImageView view, int color){
        DrawableCompat.setTint(view.getDrawable(), color);
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
