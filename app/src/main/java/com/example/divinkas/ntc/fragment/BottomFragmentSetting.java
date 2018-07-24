package com.example.divinkas.ntc.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.adapter.FiltrListAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

public class BottomFragmentSetting implements View.OnClickListener{
    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView1;
    private Context context;
    private List<String> listFilters;
    private Button btnOk;

    public  void show(){
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    public BottomFragmentSetting(View view, Context context, List<String> list){
        linearLayout = view.findViewById(R.id.bottom_sheet);
        this.context = context;
        listFilters = list;

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        recyclerView1 = view.findViewById(R.id.filtrContainer);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);
        recyclerView1.setLayoutManager(flexboxLayoutManager);

        FiltrListAdapter filtrListAdapter = new FiltrListAdapter(context, listFilters);
        recyclerView1.setAdapter(filtrListAdapter);


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        btnOk = view.findViewById(R.id.btnOk_setFiltr);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOk_setFiltr:
                show();
                break;
        }
    }
}
