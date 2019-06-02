package com.example.shareplatform.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shareplatform.Adapter.Explore_ListAdapter;
import com.example.shareplatform.Entity.ExploreArticle;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.CardLayoutManager;
import com.example.shareplatform.utils.ExploreCardItemTouchHelperCallback;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {
    ArrayList<ExploreArticle> items;
    private static final String KEY_EXPLORE = "explore_items";

    RecyclerView recyclerView;
    Explore_ListAdapter adapter;

    ImageView collectView, cancelView;


    public ExploreFragment() {
        // Required empty public constructor
    }

    public static ExploreFragment newInstance(ArrayList<ExploreArticle> items) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_EXPLORE, items);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.items = getArguments().getParcelableArrayList(KEY_EXPLORE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        recyclerView = view.findViewById(R.id.ExploreFragment_RecyclerView);
        adapter = new Explore_ListAdapter(items);
        ExploreCardItemTouchHelperCallback callback = new ExploreCardItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);

        RecyclerView.LayoutManager layoutManager = new CardLayoutManager(recyclerView, touchHelper);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        touchHelper.attachToRecyclerView(recyclerView);

        collectView = view.findViewById(R.id.ExploreFragment_collect);
        cancelView = view.findViewById(R.id.ExploreFragment_cancel);
        collectView.setOnClickListener(v -> adapter.onSwipe(0));
        cancelView.setOnClickListener(v -> adapter.onSwipe(0));
        return view;
    }

}

