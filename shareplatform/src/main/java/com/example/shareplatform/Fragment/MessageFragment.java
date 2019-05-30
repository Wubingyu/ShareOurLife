package com.example.shareplatform.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shareplatform.Adapter.Message_ListAdapter;
import com.example.shareplatform.Entity.Message;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.MessageItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private static String KEY_MESSAGE = "message fragment arrayList items";
    ArrayList<Message> items;
    RecyclerView recyclerView;
    Message_ListAdapter adapter;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance(ArrayList<Message> items) {
        MessageFragment fragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_MESSAGE, items);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = getArguments().getParcelableArrayList(KEY_MESSAGE);
        adapter = new Message_ListAdapter(items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = view.findViewById(R.id.MessageFragment_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MessageItemDecoration());

        adapter.setListener(() -> Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show());

        return view;
    }

}

