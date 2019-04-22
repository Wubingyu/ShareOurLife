package com.example.shareourlife.myProfile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shareourlife.R;
import com.example.shareourlife.Trans_Fragment;

public class Introduce_Fragment extends Trans_Fragment {
    public static final String PROFILE_INTRODUCE_CARD_TITLE = "PROFILE_INTRODUCE_CARD_TITLE";
    public static final String PROFILE_INTRODUCE_CARD_CONTEXT = "PROFILE_INTRODUCE_CARD_CONTEXT";
    String title = null;
    String context = null;

    CardView cardView;
    TextView titleView, contextView;


    /**
     * 为什么fragment不能使用构造函数
     * 原因：Fragment会被重新销毁（Activity销毁的时候它里面的Fragment就被销毁了，可能因为内存不足，手机配置发生变化，横竖屏切换)。在重新创建的时候系统调用的是无参构造函数。
     */
    public static Introduce_Fragment newInstance(String title, String context) {
        Introduce_Fragment f = new Introduce_Fragment();
        Bundle bundle = new Bundle(2);
        bundle.putString(PROFILE_INTRODUCE_CARD_TITLE, title);
        bundle.putString(PROFILE_INTRODUCE_CARD_CONTEXT, context);
        f.setArguments(bundle);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduce, container, false);
        cardView = view.findViewById(R.id.introduce_cardView);
        titleView = view.findViewById(R.id.introduce_title);
        contextView = view.findViewById(R.id.introduce_context);

        cardView.setMaxCardElevation(24);

        this.title = getArguments().getString(PROFILE_INTRODUCE_CARD_TITLE);
        this.context = getArguments().getString(PROFILE_INTRODUCE_CARD_CONTEXT);
        if (!title.isEmpty()) {
            titleView.setText(title);
        }
        if (!context.isEmpty()) {
            contextView.setText(context);
        }

        return view;
    }

    @Override
    public CardView getCardView() {
        return cardView;
    }
}
