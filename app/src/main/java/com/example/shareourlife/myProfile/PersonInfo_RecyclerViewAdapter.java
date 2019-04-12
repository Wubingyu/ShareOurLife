package com.example.shareourlife.myProfile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.shareourlife.R;

import java.util.ArrayList;

import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_radioButton_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_EDIT_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_IMG_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem.PERSON_INFO_SECURITY_ITEM;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem_radioButton.RadioButton_MAN;
import static com.example.shareourlife.myProfile.PersonInfo_RecyclerViewItem_radioButton.RadioButton_WOMAN;

public class PersonInfo_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "PersonInfo_RecyclerView";
    ArrayList<PersonInfo_RecyclerViewItem> items;
    Context context;

    public PersonInfo_RecyclerViewAdapter(ArrayList<PersonInfo_RecyclerViewItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public interface PersonInfo_RecyclerViewAdapter_Listener {
        void RadioGroupCheckChange(PersonInfo_RecyclerViewItem_radioButton item_radioButton);

        void selectBirthday(TextView textView);

        void setNickName(TextView textView);

        void ChangeSexPrivacy(PersonInfo_RecyclerViewItem_radioButton item, MaterialButton materialButton);

        //我觉得这个img_item中可能应该存的是bitmap。因为UCrop切割出来的是bitmap
        void CropPicture(PersonInfo_RecyclerViewItem_img img_item, int height, int width, ImageView imageView);
    }

    PersonInfo_RecyclerViewAdapter_Listener listener = null;

    public void setListener(PersonInfo_RecyclerViewAdapter_Listener listener) {
        this.listener = listener;
    }



    /**
     * 使用这个方法来提供ViewHolder的子布局,获得正确的viewHolder
     *
     * @param viewGroup
     * @param viewType  ItemType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case PERSON_INFO_EDIT_ITEM:
                View edit_view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_personinfo_edit, viewGroup, false);
                Edit_ViewHolder edit_viewHolder = new Edit_ViewHolder(edit_view);
                return edit_viewHolder;
            case PERSON_INFO_radioButton_ITEM:
                View radioButton_view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_personinfo_radiobutton, viewGroup, false);
                RadioButton_ViewHolder radioButton_viewHolder = new RadioButton_ViewHolder(radioButton_view);
                return radioButton_viewHolder;
            case PERSON_INFO_IMG_ITEM:
                View img_view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_personinfo_img, viewGroup, false);
                Img_ViewHolder img_viewHolder = new Img_ViewHolder(img_view);
                return img_viewHolder;
            case PERSON_INFO_SECURITY_ITEM:
                View security_view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_personinfo_security, viewGroup, false);
                Security_ViewHolder security_viewHolder = new Security_ViewHolder(security_view);
                return security_viewHolder;
            default:
                    return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PersonInfo_RecyclerViewItem item = items.get(i);
        int itemType = item.getType();
        //**********************************判断viewHolder是哪种
        if (viewHolder instanceof Edit_ViewHolder && itemType == PERSON_INFO_EDIT_ITEM) {
            //edit
            PersonInfo_RecyclerViewItem_edit edit_item = (PersonInfo_RecyclerViewItem_edit) item.getT();
            ((Edit_ViewHolder) viewHolder).edit_tag.setText(edit_item.getEdit_tag());
            ((Edit_ViewHolder) viewHolder).edit_context.setText(edit_item.getEdit_context());

            if (listener != null) {
                if (edit_item.getEdit_tag().equals("生日")) ((Edit_ViewHolder) viewHolder).edit_linearLayout.setOnClickListener(v -> listener.selectBirthday(((Edit_ViewHolder) viewHolder).edit_context));
                if (edit_item.getEdit_tag().equals("昵称")) ((Edit_ViewHolder) viewHolder).edit_linearLayout.setOnClickListener(v -> listener.setNickName(((Edit_ViewHolder) viewHolder).edit_context));

            }
        }
        //**********************************判断viewHolder是哪种
        else if (viewHolder instanceof RadioButton_ViewHolder && itemType == PERSON_INFO_radioButton_ITEM) {
            //性别
            PersonInfo_RecyclerViewItem_radioButton radioButton_item = (PersonInfo_RecyclerViewItem_radioButton) item.getT();
            if (radioButton_item.getSex_id() == RadioButton_MAN) {
                //设置为男性
                ((RadioButton_ViewHolder) viewHolder).radioGroup.check(R.id.PersonInfo_radioButton_radioButton_man);
            } else if (radioButton_item.getSex_id() == RadioButton_WOMAN) {
                //设置为女性
                ((RadioButton_ViewHolder) viewHolder).radioGroup.check(R.id.PersonInfo_radioButton_radioButton_woman);
            }
            //是否保密
            if (radioButton_item.isPrivate()) {
                ((RadioButton_ViewHolder) viewHolder).radioButton_MB.setIconResource(radioButton_item.lock_icon);
                ((RadioButton_ViewHolder) viewHolder).radioButton_MB.setText("已保密");

            } else{
                ((RadioButton_ViewHolder) viewHolder).radioButton_MB.setIconResource(radioButton_item.unlock_icon);
                ((RadioButton_ViewHolder) viewHolder).radioButton_MB.setText("公   开");

            }
            //监听
            if (listener != null) {
                ((RadioButton_ViewHolder) viewHolder).radioGroup.setOnCheckedChangeListener((arg, id) -> listener.RadioGroupCheckChange(radioButton_item));
                ((RadioButton_ViewHolder) viewHolder).radioButton_MB.setOnClickListener(v -> listener.ChangeSexPrivacy(radioButton_item, ((RadioButton_ViewHolder) viewHolder).radioButton_MB));
            }
        }
        //**********************************判断viewHolder是哪种
        else if (viewHolder instanceof Img_ViewHolder && itemType == PERSON_INFO_IMG_ITEM) {
            //头像和背景
            PersonInfo_RecyclerViewItem_img img_item = (PersonInfo_RecyclerViewItem_img) item.getT();
            if (img_item.getImg_tag().equals("头像")) {
                //设置为圆形头像。莫名形变？？？
                RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), id2Bitmap(img_item.getImg_id()));
                circleDrawable.setCircular(true);
                ((Img_ViewHolder) viewHolder).img.setImageDrawable(circleDrawable);
            }else {
                ((Img_ViewHolder) viewHolder).img.setImageResource(img_item.getImg_id());
            }
            ((Img_ViewHolder) viewHolder).img_tag.setText(img_item.getImg_tag());

            //点击响应，设置图片
            if (listener != null) {
                if (img_item.getImg_tag().equals("头像")) ((Img_ViewHolder) viewHolder).img_linearLayout.setOnClickListener(v -> listener.CropPicture(img_item, 1, 1,((Img_ViewHolder) viewHolder).img));
                if (img_item.getImg_tag().equals("个人主页背景")) ((Img_ViewHolder) viewHolder).img_linearLayout.setOnClickListener(v->listener.CropPicture(img_item, 1,2,((Img_ViewHolder) viewHolder).img));

                //设置修改后的图片
//                if (img_item.getImg_bitmap() != null) {
//                    ((Img_ViewHolder) viewHolder).img.setImageBitmap(img_item.getImg_bitmap());
//                }
            }

        }
        //**********************************判断viewHolder是哪种
        else if (viewHolder instanceof Security_ViewHolder && itemType == PERSON_INFO_SECURITY_ITEM) {
            //安全绑定
            PersonInfo_RecyclerViewItem_security security_item = (PersonInfo_RecyclerViewItem_security) item.getT();
            ((Security_ViewHolder) viewHolder).sercurit_icon.setImageResource(security_item.getSecurity_tag_icon());
            ((Security_ViewHolder) viewHolder).security_tag.setText(security_item.getSecurity_tag());
            ((Security_ViewHolder) viewHolder).security_context.setText(security_item.getSecurity_context());
            if (security_item.getSecurity_tag().equals("修改密码")) {
                ((Security_ViewHolder) viewHolder).security_MB.setIcon(null);
                ((Security_ViewHolder) viewHolder).security_MB.setGravity(Gravity.CENTER);
            }else {
                ((Security_ViewHolder) viewHolder).security_MB.setIconResource(security_item.getSecurity_MB_icon_OK());
            }
            ((Security_ViewHolder) viewHolder).security_MB.setText(security_item.getSecurity_MB_text());
        }else {
            Log.e(TAG, "onBindViewHolder: no match viewHolder");
        }

    }

    /**
     * onCreateViewHolder中的第二个参数
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (items.get(position).getType()) {
            case PERSON_INFO_EDIT_ITEM:
                return PERSON_INFO_EDIT_ITEM;
            case PERSON_INFO_radioButton_ITEM:
                return PERSON_INFO_radioButton_ITEM;
            case PERSON_INFO_IMG_ITEM:
                return PERSON_INFO_IMG_ITEM;
            case PERSON_INFO_SECURITY_ITEM:
                return PERSON_INFO_SECURITY_ITEM;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * 编辑类viewHolder
     */
    public class Edit_ViewHolder extends RecyclerView.ViewHolder {
        TextView edit_tag, edit_context;
        LinearLayout edit_linearLayout;
        public Edit_ViewHolder(@NonNull View itemView) {
            super(itemView);
            edit_tag = itemView.findViewById(R.id.PersonInfo_edit_tag);
            edit_context = itemView.findViewById(R.id.PersonInfo_edit_context);
            edit_linearLayout = itemView.findViewById(R.id.PersonInfo_edit_LinearLayout);
        }
    }

    /**
     * 性别viewHolder
     */
    public class RadioButton_ViewHolder extends RecyclerView.ViewHolder {
        RadioGroup radioGroup;
        RadioButton radioButton_man, radioButton_woman;
        MaterialButton radioButton_MB;
        public RadioButton_ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioGroup = itemView.findViewById(R.id.PersonInfo_radioButton_radioGroup);
            radioButton_MB = itemView.findViewById(R.id.PersonInfo_radioButton_privateMB);
            radioButton_man = itemView.findViewById(R.id.PersonInfo_radioButton_radioButton_man);
            radioButton_woman = itemView.findViewById(R.id.PersonInfo_radioButton_radioButton_woman);
        }
    }

    /**
     * 图片类item
     */
    public class Img_ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout img_linearLayout;
        TextView img_tag;
        ImageView img;
        public Img_ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_tag = itemView.findViewById(R.id.PersonInfo_img_tag);
            img = itemView.findViewById(R.id.PersonInfo_img_img);
            img_linearLayout = itemView.findViewById(R.id.PersonInfo_img_LinearLayout);
        }
    }

    /**
     * 账号与安全item
     */
    public class Security_ViewHolder extends RecyclerView.ViewHolder {
        ImageView sercurit_icon;
        TextView security_tag, security_context;
        MaterialButton security_MB;
        public Security_ViewHolder(@NonNull View itemView) {
            super(itemView);
            sercurit_icon = itemView.findViewById(R.id.PersonInfo_security_icon);
            security_tag = itemView.findViewById(R.id.PersonInfo_security_tag);
            security_context = itemView.findViewById(R.id.PersonInfo_security_context);
            security_MB = itemView.findViewById(R.id.PersonInfo_security_MB);
        }
    }


    private Bitmap id2Bitmap(int id) {
        return BitmapFactory.decodeResource(context.getResources(),id);
    }

}
