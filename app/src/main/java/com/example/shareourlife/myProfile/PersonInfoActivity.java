package com.example.shareourlife.myProfile;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.shareourlife.R;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_EDIT_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_IMG_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_SECURITY_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_radioButton_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem_radioButton.RadioButton_MAN;

public class PersonInfoActivity extends AppCompatActivity {
    private static final String TAG = "PersonInfoActivity";
    RecyclerView recyclerView_INFO, recyclerView_SECURITY;
    PersonInfo_RecyclerViewAdapter info_adapter, security_adapter;

    ArrayList<PersonInfo_RecyclerViewItem> info_items = new ArrayList<>();
    ArrayList<PersonInfo_RecyclerViewItem> security_items = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
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
        info_adapter.setListener(new PersonInfo_RecyclerViewAdapter.PersonInfo_RecyclerViewAdapter_Listener() {
            @Override
            public void RadioGroupCheckChange(PersonInfo_RecyclerViewItem_radioButton item_radioButton) {

            }
        });

        security_adapter = new PersonInfo_RecyclerViewAdapter(security_items, PersonInfoActivity.this);


        recyclerView_INFO.setAdapter(info_adapter);
        recyclerView_SECURITY.setAdapter(security_adapter);
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

}
