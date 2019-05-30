package com.example.shareplatform.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareplatform.Entity.Article;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.InstanceEntityHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleShowFragment extends Fragment {
    Article article;
    private static final String  ARTICLE_ID = "article_id";

    ImageView imageView;
    TextView titleView, contextView;

    public static ArticleShowFragment newInstance(int articleID) {
        ArticleShowFragment fragment = new ArticleShowFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARTICLE_ID, articleID);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        article = InstanceEntityHelper.getArticleByID(getArguments().getInt(ARTICLE_ID));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_show, container, false);
        imageView = view.findViewById(R.id.ArticleShow_img);
        titleView = view.findViewById(R.id.ArticleShow_title);
        contextView = view.findViewById(R.id.ArticleShow_context);

        imageView.setImageResource(article.getImg_id());
        titleView.setText(article.getTitle());
        contextView.setText(article.getContext());

        return view;
    }

}
