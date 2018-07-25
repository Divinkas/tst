package com.example.divinkas.ntc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
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
    static RecyclerView recyclerViewTovars;
    static List<ItemTovar> listTovars;
    static List<String> filtList;
    static Context contextStatic;
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

        contextStatic = getContext();
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
    public static void filtration(boolean[] show) {
        for(int i = 0; i<listTovars.size(); i++){
            int flag = 1;
            for (int j = 0; j<show.length; j++){
                if(!show[j] && listTovars.get(i)!=null){
                    if(filtList.get(j).equals(listTovars.get(i).getBrand_name())){
                        flag = removeItem(i, false, false);
                        continue;
                    }
                    if(j == 0){
                        if(listTovars.get(i).getType_weight() == 1){
                            flag = removeItem(i, false, false);
                            continue;
                        }
                    }
                    if(j == 1){
                        if(listTovars.get(i).getType_weight() == 2){
                            flag = removeItem(i, false, false);
                            break;
                        }
                    }
                    if(j == 2){
                        if(listTovars.get(i).getType_weight() == 3){
                            flag = removeItem(i, false, false);
                        }
                    }
                }
            }
            if(listTovars.get(i) != null) {
                if (flag == 1 && !listTovars.get(i).showable) {
                    flag = removeItem(i, true, true);
                }
            }
        }

    }
    public static int removeItem(int id, boolean isVisible, boolean isAdd){
        listTovars.get(id).showable = isVisible;
        if(!isAdd){
            recyclerViewTovars.getAdapter().notifyItemRemoved(id);
            //recyclerViewTovars.getAdapter().notifyItemRangeChanged(id, recyclerViewTovars.getChildCount() );
            return 0;
        }
        else{
            recyclerViewTovars.getAdapter().notifyItemInserted(id);
            //recyclerViewTovars.getAdapter().notifyItemRangeChanged(id, recyclerViewTovars.getChildCount());
        }
        return 1;
    }
}
