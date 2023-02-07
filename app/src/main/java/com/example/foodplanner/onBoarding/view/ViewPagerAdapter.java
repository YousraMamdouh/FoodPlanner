package com.example.foodplanner.onBoarding.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.foodplanner.R;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private int images[] = {
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3
    };
    private int description[] = {
            R.string.description1, R.string.description2, R.string.description3
    };

    public ViewPagerAdapter(Context context) {

        this.context = context;

    }


    @Override
    public int getCount() {
        return description.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider, container, false);

        ImageView slideimage = view.findViewById(R.id.background_slider);
        TextView slideDescription = view.findViewById(R.id.tv_description);


        slideimage.setImageResource(images[position]);
        slideDescription.setText(description[position]);


        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout) object);

    }
}
