package com.example.luos.demofortest;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView mImageView;
    private CollapsingToolbarLayout collapsingToolbar;
    private ViewPager mViewPager;
    private NewsBean news;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_content_layout);
        initData();
        init();
    }

    private void initData() {
        Intent intent = getIntent();
        news = (NewsBean) intent.getExtras().get(MainFragment.EXTRA_ITEM);
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.content_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mImageView = (ImageView) findViewById(R.id.ivImage);
        mImageView.setImageResource(news.getImageId());

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbar.setTitle(news.getTitle());

        mViewPager = (ViewPager) findViewById(R.id.viwepager);
        setupAdapter(mViewPager);

    }

    private void  setupAdapter(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ContentFragment.newInstance(news.getContent()),null);
        mViewPager.setAdapter(adapter);
    }
}
