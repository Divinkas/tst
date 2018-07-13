package com.example.divinkas.ntc.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divinkas.ntc.R;

public class LastPurchasesFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dialog_filtr, container, false);
        return view;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }
}
