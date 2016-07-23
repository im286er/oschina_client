package test.oschina_client;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;

import config.AppConfig;
import custom.QuickOptionDialog;
import runteset.ObjectFromDiskTest;
import threadpool.ThreadPool;
import ui.MainTab;
import widget.MyFragmentTabHost;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TabHost.OnTabChangeListener,
        View.OnTouchListener, NavigationView.OnNavigationItemSelectedListener {

    private MyFragmentTabHost mTabHost;
    private ImageView mQuickDialog;
    private NavigationView mNavigationView;
    public static DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getUserInfo();
    }

    private void getUserInfo() {
        ThreadPool threadPool = new ThreadPool();
        Runnable runnable = new ObjectFromDiskTest(AppConfig.user);
        threadPool.execute(runnable);
    }

    private void initView() {
        mTabHost = (MyFragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.content);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mNavigationView = (NavigationView) findViewById(R.id.main_drawer);
        mNavigationView.setNavigationItemSelectedListener(this);

        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        mQuickDialog = (ImageView) findViewById(R.id.quick_option_iv);
        mQuickDialog.setOnClickListener(this);
        initTab();

        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);
    }

    private void initTab() {
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            ImageView imageView = (ImageView) indicator.findViewById(R.id.tab_imageview);
            Drawable drawable = this.getResources().getDrawable(
                    mainTab.getResIcon());
            imageView.setImageDrawable(drawable);
            if (i == 2) {
                indicator.setVisibility(View.INVISIBLE);
            }
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });

            Bundle bundle = new Bundle();
            bundle.putString("key", "content: " + getString(mainTab.getResName()));
            mTabHost.addTab(tab, mainTab.getClz(), bundle);
            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.quick_option_iv:
                final QuickOptionDialog dialog = new QuickOptionDialog(
                        MainActivity.this);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                break;
        }

    }

    @Override
    public void onTabChanged(String tabId) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_answer:
                break;
            case R.id.drawer_opensoft:
                break;
            case R.id.drawer_git:
                break;
            case R.id.drawer_blog:
                break;
            case R.id.drawer_settings:
                break;
            case R.id.drawer_exit:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
