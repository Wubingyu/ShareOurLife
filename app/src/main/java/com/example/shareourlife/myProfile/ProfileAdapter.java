package com.example.shareourlife.myProfile;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.shareourlife.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    ArrayList<ProfileItem> profileItems = new ArrayList<>();

    //响应事件.在这里用interface定义需要的响应事件，在activity中把它实现后，用一个set方法设置。
    public interface OnClickListener {
        void onItemClick(int respondID);

        void onSwitchChanged(int respondID, Boolean isChecked);
    }

    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public ProfileAdapter() {
        initProfileList();
    }

    /**
     * 使用这个方法来提供ViewHolder的子布局,获得正确的viewHolder
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_profile, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 这个地方来进行对子项布局的具体定义，最简单的就是绑定一下，当然还可以加上响应事件、根据某些属性自定义布局（比如置顶） 等等
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProfileItem item = profileItems.get(i);
        viewHolder.imageView.setImageResource(item.getItem_imaId());
        viewHolder.textView.setText(item.getItem_text());
        if (!item.isShowSwitch()) {
            viewHolder.aSwitch.setVisibility(View.INVISIBLE);
        } else {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            viewHolder.aSwitch.setChecked(true);
        }
        }

        //设置响应事件
        if (mOnClickListener != null) {
            viewHolder.linearLayout.setOnClickListener(v -> mOnClickListener.onItemClick(i));
            viewHolder.aSwitch.setOnCheckedChangeListener((v, isChecked) -> mOnClickListener.onSwitchChanged(i, isChecked));
        }

    }

    @Override
    public int getItemCount() {
        return profileItems.size();
    }

    /**
     * 绑定一下，然后在adapter中的复写方法中使用它
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Switch aSwitch;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycler_item_profile_img);
            textView = itemView.findViewById(R.id.recycler_item_profile_text);
            aSwitch = itemView.findViewById(R.id.recycler_item_profile_switch);
            linearLayout = itemView.findViewById(R.id.recycler_item_profile_LinearLayout);
        }
    }


    //初始化Profile的list布局
    private void initProfileList() {
        ProfileItem person_info = new ProfileItem(R.drawable.ic_account, "编辑个人资料", false);
        ProfileItem message = new ProfileItem(R.drawable.ic_message, "消息", false);
        ProfileItem share = new ProfileItem(R.drawable.ic_share, "分享本软件",false);
        ProfileItem night_mode = new ProfileItem(R.drawable.ic_brightness, "夜间模式",true);
        profileItems.add(person_info);
        profileItems.add(message);
        profileItems.add(share);
        profileItems.add(night_mode);
    }
}
