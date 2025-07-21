package com.aquarech.farmer.ui.activities.start;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.aquarech.farmer.R;

public class IntroScreenActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout linearLayoutDots;
    View callToAction;
    View login;
    View create;

    IntroPagerAdapter myIntroPagerAdapter;
    int[] slideLayouts;
    AppCompatTextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro_screen);

        viewPager = findViewById(R.id.slider_pager);
        linearLayoutDots = findViewById(R.id.layout_dots);

        callToAction = findViewById(R.id.call_to_action);
        login = findViewById(R.id.login);
        create = findViewById(R.id.create);

        slideLayouts = new int[]{
                R.layout.intro_1_screen,
                R.layout.intro_2_screen,
                R.layout.intro_3_screen
        };

        addBottomDots(0);

        myIntroPagerAdapter = new IntroPagerAdapter();
        viewPager.setAdapter(myIntroPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position){
            addBottomDots(position);

            // if not last screen in slides
            // hide call to action
            if(position == 2){
                linearLayoutDots.setVisibility(View.GONE);
                callToAction.setVisibility(View.VISIBLE);
            }
            // show dots
            else {
                linearLayoutDots.setVisibility(View.VISIBLE);
                callToAction.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class IntroPagerAdapter extends PagerAdapter {
        IntroPagerAdapter(){
        }

        @Override
        @NonNull
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(slideLayouts[position], container, false);

            AppCompatTextView skipIntro = view.findViewById(R.id.skip_intro);
            skipIntro.setOnClickListener(view1 -> {
                viewPager.setCurrentItem(2);
                addBottomDots(2);
            });

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return slideLayouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void addBottomDots(int currentPage){
        dots = new AppCompatTextView[slideLayouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.arrayDotActive);
        int[] colorsInactive = getResources().getIntArray(R.array.arrayDotInactive);

        linearLayoutDots.removeAllViews();

        for(int i = 0; i < dots.length; i++){
            dots[i] = new AppCompatTextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(colorsInactive[currentPage]);
            linearLayoutDots.addView(dots[i]);
        }

        if(dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }
}