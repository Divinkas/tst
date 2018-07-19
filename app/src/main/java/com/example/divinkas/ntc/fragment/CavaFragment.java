package com.example.divinkas.ntc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.adapter.TovarListAdapter;
import com.example.divinkas.ntc.dto.ConnecterDTO;

public class CavaFragment extends AbstractTabFragment {
    private static final int LAYOUT_FRAGMENT = R.layout.fragment_cava;

    public static CavaFragment getInstance(Context ctx){

        Bundle bundle = new Bundle();
        CavaFragment cavaFragment = new CavaFragment();
        cavaFragment.setArguments(bundle);
        cavaFragment.setContext(ctx);
        cavaFragment.setTitle(ctx.getString(R.string.navigation_item_cava));
        return cavaFragment;
    }

    public void setContext(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT_FRAGMENT, container, false);

        ConnecterDTO connecterDTO = new ConnecterDTO();
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);

            /*StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;

            staggeredGridLayoutManagerVertical = new StaggeredGridLayoutManager(2,
                    LinearLayoutManager.VERTICAL);
              */

            // error - recyclerView.addView(new LastPurchasesFragment().getView());
            // no result - getLayoutInflater().inflate(R.layout.fragment_dialog_filtr, container, false);
            //recyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);


        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        TovarListAdapter tovarListAdapter = new TovarListAdapter(getContext(), connecterDTO.getItemTovars());
        recyclerView.setAdapter(tovarListAdapter);
        
        return view;
    }
}
