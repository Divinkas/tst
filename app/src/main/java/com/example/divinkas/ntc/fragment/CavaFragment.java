package com.example.divinkas.ntc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.adapter.FiltrListAdapter;
import com.example.divinkas.ntc.adapter.TovarListAdapter;
import com.example.divinkas.ntc.dataTypes.FiltrListTov;
import com.example.divinkas.ntc.dataTypes.ItemTovar;
import com.example.divinkas.ntc.dto.ConnecterDTO;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CavaFragment extends AbstractTabFragment {
    private static final int LAYOUT_FRAGMENT = R.layout.fragment_cava;

    private static BottomFragmentSetting bottomFragmentSetting;

    public static CavaFragment getInstance(Context ctx){

        Bundle bundle = new Bundle();
        CavaFragment cavaFragment = new CavaFragment();
        cavaFragment.setArguments(bundle);
        cavaFragment.setContext(ctx);
        cavaFragment.setTitle(ctx.getString(R.string.navigation_item_cava));
        return cavaFragment;
    }
    public static void showBottomBehavior(){
        bottomFragmentSetting.show();

    }
    public void setContext(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT_FRAGMENT, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycleView);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        ConnecterDTO connecterDTO = new ConnecterDTO();
        connecterDTO.execute();

        List<ItemTovar> list = null;
        try {
            list = connecterDTO.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        TovarListAdapter tovarListAdapter = new TovarListAdapter(
                context,
                list
        );

        recyclerView.setAdapter(tovarListAdapter);

        List<String> filtrItems = new ArrayList<>();
        filtrItems.addAll(FiltrListTov.getFiltrList());
        Collections.sort(filtrItems);

        bottomFragmentSetting = new BottomFragmentSetting(view);

        RecyclerView recyclerView1 = view.findViewById(R.id.filtrContainer);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);
        recyclerView1.setLayoutManager(flexboxLayoutManager);

        FiltrListAdapter filtrListAdapter = new FiltrListAdapter(getContext(), filtrItems);
        recyclerView1.setAdapter(filtrListAdapter);

        return view;
    }

}
