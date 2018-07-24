package com.example.divinkas.ntc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.adapter.TovarListAdapter;
import com.example.divinkas.ntc.dataTypes.FiltrListTov;
import com.example.divinkas.ntc.dataTypes.ItemTovar;
import com.example.divinkas.ntc.dto.ConnecterDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CavaFragment extends AbstractTabFragment {
    private static final int LAYOUT_FRAGMENT = R.layout.fragment_cava;
    RecyclerView recyclerViewTovars;
    List<ItemTovar> listTovars;
    List<String> filtList;
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

        recyclerViewTovars = view.findViewById(R.id.recycleView);

        recyclerViewTovars.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        ConnecterDTO connecterDTO = new ConnecterDTO();
        connecterDTO.execute();

        listTovars = null;
        try {
            listTovars = connecterDTO.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        TovarListAdapter tovarListAdapter = new TovarListAdapter(
                context,
                listTovars
        );

        recyclerViewTovars.setAdapter(tovarListAdapter);

        filtList = new ArrayList<>();
        filtList.addAll(FiltrListTov.getFiltrList());
        Collections.sort(filtList);

        bottomFragmentSetting = new BottomFragmentSetting(view, getContext(), filtList);

        return view;
    }
    public void filtration(boolean[] show) {
        for(int  i = 0; i < show.length; i++){

            for(int j = 0; j < listTovars.size(); i++){
                if(!show[i]){
                    if(listTovars.get(j).getBrand_name().equals(filtList.get(i))){
                        listTovars.get(j).showable = false;
                    }
                }
                if(i == 0 && !show[i]){
                    if(listTovars.get(j).getType_weight() == 1){
                        listTovars.get(j).showable = false;
                    }
                }
                if(i == 1 && !show[i]){
                    if(listTovars.get(j).getType_weight() == 2){
                        listTovars.get(j).showable = false;
                    }
                }
                if(i == 2 && !show[i]){
                    if(listTovars.get(j).getType_weight() == 3){
                        listTovars.get(j).showable = false;
                    }
                }
            }

        }

    }
}
