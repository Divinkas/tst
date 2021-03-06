package com.example.divinkas.ntc;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divinkas.ntc.adapter.TabsPagerFragmentAdapter;
import com.example.divinkas.ntc.fragment.BottomFragmentSetting;
import com.example.divinkas.ntc.fragment.CavaFragment;

import org.w3c.dom.Text;

import java.util.Objects;

import static com.example.divinkas.ntc.R.color.colorGray_;
import static com.example.divinkas.ntc.R.color.colorOranges;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private static final int THEME_LAYOUT = R.style.AppDefault;



    Toolbar toolbar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(THEME_LAYOUT);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

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
                        CavaFragment.showBottomBehavior();
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }
    @SuppressLint("ResourceAsColor")
    private void initTabLayout() {
        viewPager = findViewById(R.id.viewPager);

        TabsPagerFragmentAdapter tabsPagerFragmentAdapter = new TabsPagerFragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(tabsPagerFragmentAdapter);
        viewPager.setCurrentItem(Constants.TAB_ONE);



        final TabLayout tabLayout  = findViewById(R.id.tabLayoutMain);
        tabLayout.setupWithViewPager(viewPager);


        int[] imageResId = {
                R.drawable.selector_cava_list,
                R.drawable.selector_icon_account,
                R.drawable.selector_basket_icon};





        for(int i = 0; i< tabLayout.getTabCount(); i++) {

            //Objects.requireNonNull(tabLayout.getTabAt(i)).setIcon(imageResId[i]);

                TextView textView = (TextView) LayoutInflater.from(getBaseContext()).inflate(R.layout.custom_tab_item, null);
                textView.setText(tabsPagerFragmentAdapter.getPageTitle(i));
                textView.setCompoundDrawablesWithIntrinsicBounds(imageResId[i], 0, 0, 0);
                //textView.setTextColor(colorGray_);

            Objects.requireNonNull(tabLayout.getTabAt(i)).setCustomView(textView);


        }
        tabLayout.setTabTextColors(R.color.colorGray_, R.color.colorOranges);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                assert tab.getCustomView() != null;
                TextView  textView = tab.getCustomView().findViewById(R.id.text);
                textView.setTextColor(R.color.colorOranges);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                assert tab.getCustomView() != null;
                TextView  textView = tab.getCustomView().findViewById(R.id.text);
                textView.setTextColor(R.color.colorGray_);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
