package com.example.shareourlife.myProfile;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shareourlife.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.Observable;

import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_EDIT_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_IMG_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_SECURITY_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_radioButton_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem_radioButton.RadioButton_MAN;

public class PersonInfoActivity extends AppCompatActivity {
    private static final String TAG = "PersonInfoActivity";
    private static final int CHOOSE_PHOTO = 1;

    Observable<Bitmap> Crop_picture_bitmap = null;

    RecyclerView recyclerView_INFO, recyclerView_SECURITY;
    PersonInfo_RecyclerViewAdapter info_adapter, security_adapter;

    // 感觉这样不是正确的做法，但是先这么用吧。
    ImageView crop_imageView;
    String crop_item_tag;
    int crop_height, crop_width;

    ArrayList<PersonInfo_RecyclerViewItem> info_items = new ArrayList<>();
    ArrayList<PersonInfo_RecyclerViewItem> security_items = new ArrayList<>();
    private String setNickName = "";

    //这个应该是指存储目标名？好像是这样，但是实际上在MT管理器中发现存储名字前面还有一串数字，这个数字是从哪里来的呢？
    private static final String SAMPLE_CROPPED_IMAGE_NAME = "SampleCropImage";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_Design_NoActionBar);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        InitItems();
        Log.d(TAG, "info_items size " + info_items.size());
        Log.d(TAG, "security_item size " + security_items.size());
        //关联View
        recyclerView_INFO = findViewById(R.id.PersonInfo_recyclerView_Info);
        recyclerView_SECURITY = findViewById(R.id.PersonInfo_recyclerView_security);

        //设置分割线
        recyclerView_INFO.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView_SECURITY.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //布局
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_INFO.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_SECURITY.setLayoutManager(linearLayoutManager2);

        //适配器
        info_adapter = new PersonInfo_RecyclerViewAdapter(info_items, PersonInfoActivity.this);
        setApapterListener();

        security_adapter = new PersonInfo_RecyclerViewAdapter(security_items, PersonInfoActivity.this);


        recyclerView_INFO.setAdapter(info_adapter);
        recyclerView_SECURITY.setAdapter(security_adapter);
    }

    private void setApapterListener() {
        info_adapter.setListener(new PersonInfo_RecyclerViewAdapter.PersonInfo_RecyclerViewAdapter_Listener() {
            @Override
            public void RadioGroupCheckChange(PersonInfo_RecyclerViewItem_radioButton item_radioButton) {

            }

            @Override
            public void selectBirthday(TextView textView) {
                showDatePickDialog(textView);
            }

            @Override
            public void setNickName(TextView textView) {
                final EditText input = new EditText(PersonInfoActivity.this);
                new AlertDialog.Builder(PersonInfoActivity.this).setTitle("昵称")
                        .setIcon(android.R.drawable.ic_menu_edit)
                        .setView(input)
                        .setPositiveButton("确定", (dialog, which) -> {
                            setNickName = input.getText().toString();
                            textView.setText(setNickName);
                        })
                        .setNegativeButton("取消", (dialog, which) -> dialog.cancel())
                        .show();
                Toast.makeText(PersonInfoActivity.this, "setNickName", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void ChangeSexPrivacy(PersonInfo_RecyclerViewItem_radioButton item, MaterialButton materialButton) {
                //本来是保密的，设为公开
                if (item.isPrivate()) {
                    materialButton.setIconResource(R.drawable.ic_unlock);
                    materialButton.setText("  公 开");
                    item.setPrivate(false);
                } else {
                    materialButton.setIconResource(R.drawable.ic_lock);
                    materialButton.setText("已保密");
                    item.setPrivate(true);
                }

            }

            /**
             * 切割图片
             * @param img_item 更新item
             * @param height 高度比例
             * @param width 宽度比例
             */
            @Override
            public void CropPicture(PersonInfo_RecyclerViewItem_img img_item, int height, int width, ImageView imageView) {
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                crop_imageView = imageView;
                crop_height = height;
                crop_width = width;
                crop_item_tag = img_item.getImg_tag();
                intent.setType("image/*");
//                intent.putExtra("ITEM", (Parcelable) img_item);
                startActivityForResult(intent, CHOOSE_PHOTO);
                Log.d(TAG, "CropPicture: img_item tag" + img_item.getImg_tag());

                //startActivityForResult 上一行代码调用之后，只是设置了一个监听罢了，就继续执行了哦，所以下面的代码是还没有返回Crop_picture_bitmap的时候设置的呀
//                Log.d(TAG, "CropPicture: ");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case CHOOSE_PHOTO:
                    Uri uri = data.getData();
                    //开始裁剪
                    startCrop(uri, crop_height, crop_width);
                    break;
                case UCrop.REQUEST_CROP:
                    final Uri resultUri = UCrop.getOutput(data);
                    //成功取得bitmap
                    try {
                        if(resultUri!=null) {
                            Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUri));
                            //设置头像
                            if (crop_item_tag.equals("头像")) {
                                RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(getResources(), bit);
                                circleDrawable.setCircular(true);
                                crop_imageView.setImageDrawable(circleDrawable);
                            }else {
                                crop_imageView.setImageBitmap(bit);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    /**
     * 创建items
     */
    private void InitItems() {
        PersonInfo_RecyclerViewItem_img head_img = new PersonInfo_RecyclerViewItem_img("头像", R.drawable.sakura);
        PersonInfo_RecyclerViewItem_img background_img = new PersonInfo_RecyclerViewItem_img("个人主页背景", R.drawable.img1);

        PersonInfo_RecyclerViewItem_edit nickName = new PersonInfo_RecyclerViewItem_edit("昵称", "东山绫音");

        PersonInfo_RecyclerViewItem_radioButton sexSelcet = new PersonInfo_RecyclerViewItem_radioButton(RadioButton_MAN, true, R.drawable.ic_lock, R.drawable.ic_unlock);
        PersonInfo_RecyclerViewItem_edit birthday = new PersonInfo_RecyclerViewItem_edit("生日", "1994-1-29");

        info_items.add(new PersonInfo_RecyclerViewItem(head_img, PERSON_INFO_IMG_ITEM));
        info_items.add(new PersonInfo_RecyclerViewItem(background_img, PERSON_INFO_IMG_ITEM));

        info_items.add(new PersonInfo_RecyclerViewItem(nickName, PERSON_INFO_EDIT_ITEM));

        info_items.add(new PersonInfo_RecyclerViewItem(sexSelcet, PERSON_INFO_radioButton_ITEM));

        info_items.add(new PersonInfo_RecyclerViewItem(birthday, PERSON_INFO_EDIT_ITEM));
        //********************

        PersonInfo_RecyclerViewItem_security userName = new PersonInfo_RecyclerViewItem_security(R.drawable.ic_userid, "用户ID", "sakura_ayane",
                "已注册", R.drawable.ic_check, R.drawable.ic_add);
        PersonInfo_RecyclerViewItem_security PhoneNumber = new PersonInfo_RecyclerViewItem_security(R.drawable.ic_smartphone, "手机号",
                "151****3671", "已绑定", R.drawable.ic_check, R.drawable.ic_add);
        PersonInfo_RecyclerViewItem_security EMail = new PersonInfo_RecyclerViewItem_security(R.drawable.ic_email, "电子邮箱",
                "wubiyu97@gmail.com", "已绑定", R.drawable.ic_check, R.drawable.ic_add);
        PersonInfo_RecyclerViewItem_security password = new PersonInfo_RecyclerViewItem_security(R.drawable.ic_password, "修改密码",
                "提示：身份证号", "修改密码", R.drawable.ic_edit, R.drawable.ic_edit);

        security_items.add(new PersonInfo_RecyclerViewItem(userName, PERSON_INFO_SECURITY_ITEM));
        security_items.add(new PersonInfo_RecyclerViewItem(PhoneNumber, PERSON_INFO_SECURITY_ITEM));
        security_items.add(new PersonInfo_RecyclerViewItem(EMail, PERSON_INFO_SECURITY_ITEM));
        security_items.add(new PersonInfo_RecyclerViewItem(password, PERSON_INFO_SECURITY_ITEM));
    }


    //选择日期
    private void showDatePickDialog(TextView textView_date) {
        final StringBuffer time = new StringBuffer();
        //获取Calendar对象，用于获取当前时间
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //实例化DatePickerDialog对象
        DatePickerDialog datePickerDialog = new DatePickerDialog(PersonInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
            //选择完日期后会调用该回调函数
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //因为monthOfYear会比实际月份少一月所以这边要加1
                time.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                textView_date.setText(time);
            }
        }, year, month, day);
        //弹出选择日期对话框
        datePickerDialog.show();
    }

    //裁剪
    private void startCrop(@NonNull Uri uri,int height, int width) {
        String destinationFileName = SAMPLE_CROPPED_IMAGE_NAME;
        //保存格式为png格式
        destinationFileName += ".png";

        //使用构造者模式来创建 uCrop  ： UCrop.of(sourceUri, destinationUri)
        //而开始裁剪的方法是：start(context);
        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));

        //这个方法它应该是用来设置裁剪的大小的。
//        uCrop = basisConfig(uCrop);
        uCrop = uCrop.withAspectRatio(width, height);

        //裁剪界面和裁剪结构的设置
//        uCrop = advancedConfig(uCrop);
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.PNG);
//        options.setHideBottomControls(true);  //是否隐藏操作栏
        uCrop.withOptions(options);

        //开始，然后在onActivityResult中处理
        uCrop.start(PersonInfoActivity.this);

    }

}
