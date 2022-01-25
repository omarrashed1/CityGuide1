package com.developerdepository.scout.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.developerdepository.scout.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            R.drawable.illustration_first_onboarding,
            R.drawable.illustration_second_onboarding,
            R.drawable.illustration_third_onboarding,
            R.drawable.illustration_fourth_onboarding
    };

    int titles[] = {
            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title,
            R.string.fourth_slide_title
    };

    int descriptions[] = {
            R.string.first_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc,
            R.string.fourth_slide_desc
    };

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_onboarding_slider, container, false);
        TextView sliderTitle = view.findViewById(R.id.slider_title);
        TextView sliderDesc = view.findViewById(R.id.slider_desc);
        sliderTitle.setText(titles[position]);
        sliderDesc.setText(descriptions[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
