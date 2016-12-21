package com.nuoman.westernele.contacts.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.nuoman.westernele.common.BaseRecyclerAdapter;
import com.nuoman.westernele.common.ViewHolder;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.components.AvatarImageView;
import com.nuoman.westernele.contacts.model.Customer;
import com.nuoman.westernelectric.R;

import java.util.List;

/**
 * Created by Alex on 2016/1/11.
 */
public class ClientRecordAdapter extends BaseRecyclerAdapter<Customer> implements View.OnClickListener {

    public ClientRecordAdapter(Context context, int layoutResId, List<Customer> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder holder, Customer model, int position) {

        ImageView clue_avatar_iv = holder.getView(R.id.clue_avatar_iv);
        ImageView sms = holder.getView(R.id.message_iv);
        ImageView phone = holder.getView(R.id.phone_iv);

        AvatarImageView clue_avatar_no_url_iv = holder.getView(R.id.clue_avatar_no_url_iv);

        if (!TextUtils.isEmpty(model.getFirstName())) {
            clue_avatar_no_url_iv.setVisibility(View.VISIBLE);
            clue_avatar_iv.setVisibility(View.GONE);
            clue_avatar_no_url_iv.setContentText(model.getFirstName());
        }
        holder.setText(R.id.clue_name_tv, model.getFullName());
        holder.setText(R.id.position_tv, model.getJob());
        holder.setText(R.id.clue_create_time_tv, model.getCompany());

        sms.setOnClickListener(this);
        sms.setTag(model);
        phone.setOnClickListener(this);
        phone.setTag(model);

    }

    @Override
    public void onClick(View v) {
        Customer item = (Customer) v.getTag();
        switch (v.getId()) {
            case R.id.message_iv:
                if (!TextUtils.isEmpty(item.getTel())) {
                    AppTools.sendSMS(context, item.getTel());
                } else {
                    AppTools.getToast("暂无电话号码");
                }

                break;
            case R.id.phone_iv:
                if (!TextUtils.isEmpty(item.getTel())) {
                    AppTools.callPhone(context, item.getTel());
                } else {
                    AppTools.getToast("暂无电话号码");
                }

                break;
        }

    }


}
