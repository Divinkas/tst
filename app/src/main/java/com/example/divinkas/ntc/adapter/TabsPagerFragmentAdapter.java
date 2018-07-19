package com.example.divinkas.ntc.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.fragment.AbstractTabFragment;
import com.example.divinkas.ntc.fragment.BasketFragment;
import com.example.divinkas.ntc.fragment.CavaFragment;
import com.example.divinkas.ntc.fragment.ProfileFragment;

import java.util.HashMap;
import java.util.Map;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context ctx;

    public TabsPagerFragmentAdapter(Context context, FragmentManager fm){
        super(fm);
        ctx = context;
        initFragments(context);
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int[] imageResId = {
                R.drawable.format_align_justify,
                R.drawable.account_circle,
                R.drawable.cart_x};
        Drawable image = ctx.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString("   " + tabs.get(position).getTITLE());
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
        //return tabs.get(position).getTITLE();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initFragments(Context context){
        tabs = new HashMap<>();

        tabs.put(0, CavaFragment.getInstance(context));
        tabs.put(1, ProfileFragment.getInstance(context));
        tabs.put(2, BasketFragment.getInstance(context));
    }
}
