package com.example.shareplatform.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shareplatform.Adapter.Article_ListAdapter;
import com.example.shareplatform.Entity.Article;
import com.example.shareplatform.MainActivity;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.InstanceEntityHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FolderFragment extends Fragment {
    ArrayList<Article> items;
    String transName;
    RecyclerView recyclerView;
    Article_ListAdapter adapter;
    private static final String KEY_TRANS = "trans";
    private static final String FOLDER_ID = "folderID";

    public static FolderFragment newInstance(String transitionName, int FolderID) {
        FolderFragment fragment = new FolderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TRANS, transitionName);
        bundle.putInt(FOLDER_ID, FolderID);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transName = getArguments().getString(KEY_TRANS);
        items = InstanceEntityHelper.getArticleByFolderID(getArguments().getInt(FOLDER_ID));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_folder, container, false);


        ImageView imageView = view.findViewById(R.id.Folder_Fragment_img);
        imageView.setImageResource(InstanceEntityHelper.getFolderByID(getArguments().getInt(FOLDER_ID)).getImg_id());
        TextView textView = view.findViewById(R.id.Folder_Fragment_title);
        textView.setText(InstanceEntityHelper.getFolderByID(getArguments().getInt(FOLDER_ID)).getTitle());

        imageView.setTransitionName(transName);

        //创建自己的RecyclerView，并设置流式布局
        recyclerView = view.findViewById(R.id.Folder_Fragment_RecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Article_ListAdapter(items);
        recyclerView.setAdapter(adapter);

        adapter.setListener(ArticleID -> ((MainActivity) getActivity()).showArticleWithTransition(FolderFragment.this, ArticleID));

        return view;
    }

}
