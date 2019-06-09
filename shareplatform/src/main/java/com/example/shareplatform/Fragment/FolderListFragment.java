package com.example.shareplatform.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shareplatform.Adapter.Folder_ListAdapter;
import com.example.shareplatform.Entity.Folder;
import com.example.shareplatform.MainActivity;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.InstanceEntityHelper;
import com.example.shareplatform.utils.SpaceItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FolderListFragment extends Fragment {
    private static final String KEY_FOLDER_LIST = "explore_items";
    private final int CHOOSE_PHOTO = 1;
    ArrayList<Folder> items;

    Folder_ListAdapter adapter;
    RecyclerView recyclerView;
    FolderFragment fragment;
    String imagePath;


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
        items.add(null); //添加一个位置，用于显示+1行为
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

            @Override
            public void addFolder() {
                Toast.makeText(getActivity(), "添加文件夹", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder customizeDialog =
                        new AlertDialog.Builder(getActivity());
                final View dialogView = LayoutInflater.from(getActivity())
                        .inflate(R.layout.dialog_add_folder, null);
                customizeDialog.setTitle("创建新的文件夹").setView(dialogView)
                        .setPositiveButton("新建", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText = view.findViewById(R.id.dialog_add_folder_editText);
                                ImageView imageView = view.findViewById(R.id.dialog_add_folder_img);

                                imageView.setOnClickListener(v -> openAlbum());
                                String title = String.valueOf(editText.getText());
                                InstanceEntityHelper.newFolder(0, imagePath , title); //新建文件夹
                                /**
                                 * 记得修改创建Folder列表的逻辑，关于path
                                 */
                            }
                        }).show();
            }
        });
        return view;
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    /**
     * 获取返回
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void notifyDataChange() {
        adapter.notifyDataSetChanged();
        if (fragment != null) {
            fragment.notifyDataChange();
        }
    }
}
