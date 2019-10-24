package com.example.sub3eca;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sub3eca.ViewModel.ShowVM;

import java.util.ArrayList;

import static com.example.sub3eca.DetailActivity.EXTRA_DETAIL;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment implements Adapter.OnItemClickListener{

    Adapter adapter;
    ShowVM showVM;
    ProgressBar mProgressBar;
    public TvFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv, container, false);
        mProgressBar = v.findViewById(R.id.loading_show);

        adapter = new Adapter(getContext());
        adapter.setOnItemClickListener(TvFragment.this);
        adapter.notifyDataSetChanged();

        showVM= ViewModelProviders.of(getActivity()).get(ShowVM.class);
        showVM.getShow().observe(TvFragment.this,getShow);
        showVM.getAPI();

        RecyclerView mRecyclerView = v.findViewById(R.id.show);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(adapter);

        return v;
    }
    private Observer<ArrayList<Items>> getShow = new Observer<ArrayList<Items>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Items> movieItems) {
            if(movieItems != null){
                adapter.setmItems(movieItems);
                showLoading(false);

            }
        }
    };
    private void showLoading(Boolean state) {
        if (state) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }



    @Override
    public void onItemClick(int i) {
        Items items = new Items(showVM.mitems.get(i).getInfo_film(),showVM.mitems.get(i).getTitle_film(),showVM.mitems.get(i).getDesc_film(),showVM.mitems.get(i).getPhoto());

        Intent detail = new Intent(getContext(), DetailActivity.class);

        detail.putExtra(EXTRA_DETAIL,items);
        startActivity(detail);
    }
}
