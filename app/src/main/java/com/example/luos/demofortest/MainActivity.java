package com.example.luos.demofortest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private DrawerLayout mDarwerLayout;
    private ActionBarDrawerToggle mDarwerToggle;
    private NavigationView mNavigationView;
    private ArrayList<NewsBean> datas_one = new ArrayList<>();
    private ArrayList<NewsBean> datas_two = new ArrayList<>();
    private ArrayList<NewsBean> datas_three = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        init();

        //加入toolbar

    }

    private void initDatas() {
        datas_one.add(new NewsBean(R.drawable.content1,getString(R.string.title1),getString(R.string.content1)));
        datas_one.add(new NewsBean(R.drawable.content2,getString(R.string.title2),getString(R.string.content2)));
        datas_one.add(new NewsBean(R.drawable.content3,getString(R.string.title3),getString(R.string.content3)));
        datas_one.add(new NewsBean(R.drawable.content4,getString(R.string.title4),getString(R.string.content4)));
        datas_one.add(new NewsBean(R.drawable.content5,getString(R.string.title5),getString(R.string.content5)));
        datas_two.add(new NewsBean(R.drawable.content6,getString(R.string.title6),getString(R.string.content6)));
        datas_two.add(new NewsBean(R.drawable.content7,getString(R.string.title7),getString(R.string.content7)));
        datas_two.add(new NewsBean(R.drawable.content1,getString(R.string.title1),getString(R.string.content1)));
        datas_two.add(new NewsBean(R.drawable.content2,getString(R.string.title2),getString(R.string.content2)));
        datas_two.add(new NewsBean(R.drawable.content3,getString(R.string.title3),getString(R.string.content3)));
        datas_three.add(new NewsBean(R.drawable.content4,getString(R.string.title4),getString(R.string.content4)));
        datas_three.add(new NewsBean(R.drawable.content5,getString(R.string.title5),getString(R.string.content5)));
        datas_three.add(new NewsBean(R.drawable.content6,getString(R.string.title6),getString(R.string.content6)));
        datas_three.add(new NewsBean(R.drawable.content7,getString(R.string.title7),getString(R.string.content7)));
        datas_three.add(new NewsBean(R.drawable.content1,getString(R.string.title1),getString(R.string.content1)));
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MyDemo");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTabLayout = (TabLayout) findViewById(R.id.main_activity_tabs);
        mViewpager = (ViewPager) findViewById(R.id.main_activity_viwepager);
        setupViewPager(mViewpager);
        mTabLayout.setupWithViewPager(mViewpager);

        mDarwerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        //关联toolbar和DrawerLayou
        mDarwerToggle = new ActionBarDrawerToggle(this,mDarwerLayout,toolbar,
                R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                drawerView.bringToFront();
                super.onDrawerOpened(drawerView);
            }
        };
        //同步状态
        mDarwerToggle.syncState();
        //DrawerLayout监听关联事件
        mDarwerLayout.addDrawerListener(mDarwerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawContent(mNavigationView);


    }

    private void setupViewPager(ViewPager mViewpager) {
        MyPagerAdapter adpter = new MyPagerAdapter(getSupportFragmentManager());
        Log.i(TAG,datas_one.size()+ " "+datas_two.size()+" "+datas_three.size());
        adpter.addFragment(MainFragment.getInstance(datas_one),"标签一");
        adpter.addFragment(MainFragment.getInstance(datas_two),"标签二");
        adpter.addFragment(MainFragment.getInstance(datas_three),"标签三");
        mViewpager.setAdapter(adpter);
    }


    private void setupDrawContent(final NavigationView mNavigationView) {
        Log.i(TAG,"setupDrawContent");
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Log.i(TAG,"menu onClick");
                switch (item.getItemId()){
                    case R.id.navigation_menu_example1:
                        Log.i(TAG,"menu1 onClick");
                        buildDailogr("menu_example1");
                        break;
                    case R.id.navigation_menu_example2:
                        Log.i(TAG,"menu1 onClick");
                        buildDailogr("menu_example2");
                        break;
                    case R.id.navigation_menu_example3:
                        Log.i(TAG,"menu1 onClick");
                        buildDailogr("menu_example3");
                        break;
                    case R.id.navigation_menu_example4:
                        Log.i(TAG,"menu1 onClick");
                        buildDailogr("menu_example4");
                        break;
                }
                item.setChecked(true);
                mDarwerLayout.closeDrawers();
                return true;
            }
        });
    }

    public void buildDailogr(final String title){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(mViewpager,"操作成功",Snackbar.LENGTH_SHORT).show();
                    }
        }).create();
        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.menu_system:
                buildDailogr("设置中心");
                break;
            case R.id.menu_help:
                buildDailogr("帮助中心");
                break;
        }
        return true;
    }
}
