package com.example.shareplatform.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shareplatform.Adapter.Folder_ListAdapter;
import com.example.shareplatform.Entity.Folder;
import com.example.shareplatform.MainActivity;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.SpaceItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FolderListFragment extends Fragment {
    private static final String KEY_FOLDER_LIST = "explore_items";
    ArrayList<Folder> items;

    Folder_ListAdapter adapter;
    RecyclerView recyclerView;
    FolderFragment fragment;

    public static FolderListFragment newInstance(ArrayList<Folder> folders) {
        FolderListFragment fragment = new FolderListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_FOLDER_LIST, folders);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.items = getArguments().getParcelableArrayList(KEY_FOLDER_LIST);
        adapter = new Folder_ListAdapter(items, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_folder_list, container, false);

        recyclerView = view.findViewById(R.id.FolderListFragment_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));


        //设置点击事件
        adapter.setListener(new Folder_ListAdapter.OnClickListener() {
            @Override
            public void onCardClick(int position, ImageView imageView, int folderID) {
                fragment = FolderFragment.newInstance("transition" + position, folderID);

                ((MainActivity) getActivity()).showFragmentWithTransition(FolderListFragment.this, fragment, imageView, "transition" + position);
            }

            @Override
            public void onArticleClick(int ArticleID) {
                ((MainActivity) getActivity()).showArticleWithTransition(FolderListFragment.this, ArticleID);

            }

            @Override
            public void onAddArticle(int FolderID) {
                ((MainActivity)getActivity()).addArticle(FolderID);
            }
        });
        return view;
    }


    public void notifyDataChange() {
        adapter.notifyDataSetChanged();
        if (fragment != null) {
            fragment.notifyDataChange();
        }
    }
}
