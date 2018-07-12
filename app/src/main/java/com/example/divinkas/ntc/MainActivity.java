package com.example.divinkas.ntc;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.divinkas.ntc.adapter.TabsPagerFragmentAdapter;
import com.example.divinkas.ntc.fragment.BottomFragmentSetting;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private static final int THEME_LAYOUT = R.style.AppDefault;

    private BottomFragmentSetting bottomFragmentSetting;

    Toolbar toolbar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(THEME_LAYOUT);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        bottomFragmentSetting = new BottomFragmentSetting();
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
                        //i'm need help in using change bottomSheetBehavior state

                        //bottomFragmentSetting.show();
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
    }
}
