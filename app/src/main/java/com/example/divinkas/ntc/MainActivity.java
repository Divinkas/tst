package com.example.divinkas.ntc;

import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.divinkas.ntc.adapter.TabsPagerFragmentAdapter;
import com.example.divinkas.ntc.fragment.BottomFragmentSetting;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private static final int THEME_LAYOUT = R.style.AppDefault;

    public BottomFragmentSetting bottomFragmentSetting;

    Toolbar toolbar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(THEME_LAYOUT);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        bottomFragmentSetting = new BottomFragmentSetting(MainActivity.this);
        initToolbar();
        initTabLayout();

    }

    public void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.settingFiltr:
                        bottomFragmentSetting.show();
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }
    private void initTabLayout() {
        viewPager = findViewById(R.id.viewPager);

        TabsPagerFragmentAdapter tabsPagerFragmentAdapter = new TabsPagerFragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(tabsPagerFragmentAdapter);
        viewPager.setCurrentItem(Constants.TAB_ONE);

        TabLayout tabLayout  = findViewById(R.id.tabLayoutMain);
        tabLayout.setupWithViewPager(viewPager);

        /*
        int[] imageResId = {
                R.drawable.format_align_justify,
                R.drawable.account_circle,
                R.drawable.cart_x};
        for(int i=0; i < tabLayout.getTabCount(); i++){
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
            //tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorOranges);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorGray_);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }
        });
        */
    }
}
