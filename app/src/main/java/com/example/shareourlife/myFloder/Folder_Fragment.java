package com.example.shareourlife.myFloder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shareourlife.R;
import com.example.shareourlife.Trans_Fragment;

import java.io.ByteArrayOutputStream;

//用户的收藏
public class Folder_Fragment extends Trans_Fragment {
    private static final String TAG = "Folder_Fragment";
    //我的你妈，这个地方设置为private final static String 的时候，居然会报错，getArguments为null。。我的尼玛
    public final static String FOLDER_NAME = "FOLDER_NAME";
    public final static String FOLDER_DESCRIPTION = "FOLDER_DESCRIPTION";
    public final static String FOLDER_IMG = "FOLDER_IMG";
    CardView cardView;
    ImageView imageView;
    TextView nameView, descriptionView, showView, shareView;

    public static Folder_Fragment newInstance(String name, String description, Drawable drawable) {
        Folder_Fragment f = new Folder_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(FOLDER_NAME, name);
        bundle.putString(FOLDER_DESCRIPTION, description);
        byte[] img_byte = bitmap2Bytes(drawableToBitamp(drawable));
        bundle.putByteArray(FOLDER_IMG, img_byte);

        f.setArguments(bundle);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folder, container, false);
        cardView = view.findViewById(R.id.Folder_CardView);
        imageView = view.findViewById(R.id.Folder_Img);
        nameView = view.findViewById(R.id.Folder_name);
        descriptionView = view.findViewById(R.id.Folder_description);
        showView = view.findViewById(R.id.Folder_Show);
        shareView = view.findViewById(R.id.Folder_Share);

        if (getArguments() == null) {
            Log.e(TAG, "onCreateView: getArguments is null");
            return view;
        }
        String name = getArguments().getString(FOLDER_NAME);
        String description = getArguments().getString(FOLDER_DESCRIPTION);
        if (!name.isEmpty()) nameView.setText(name);
        if (!description.isEmpty()) descriptionView.setText(description);

        byte[] img_byte = getArguments().getByteArray(FOLDER_IMG);
        if (img_byte != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img_byte, 0, img_byte.length);
            imageView.setImageBitmap(bitmap);
        }

        cardView.setMaxCardElevation(24);

        setListener();
        return view;
    }

    /**
     * 设置响应事件
     */
    private void setListener() {
        shareView.setOnClickListener(v -> Toast.makeText(getActivity(), "click share", Toast.LENGTH_SHORT).show());
        showView.setOnClickListener(v -> Toast.makeText(getActivity(), "click show", Toast.LENGTH_SHORT).show());
        cardView.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "click card", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), Folder_Activity.class);
            getActivity().startActivity(intent);
        });
    }

    @Override
    public CardView getCardView() {
        return cardView;
    }


    private static Bitmap drawableToBitamp(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w,h,config);
        //注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * bitmap转化成byte数组：
     * @param bm
     * @return
     */
    private static byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
