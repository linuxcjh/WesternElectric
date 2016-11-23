package com.nuoman.westernele.set;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nuoman.westernelectric.R;
import com.nuoman.westernele.common.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 管理
 * 2016/1/13
 */
public class SetFragment extends BaseFragment {


    @Bind(R.id.pwd_layout)
    RelativeLayout pwdLayout;
    @Bind(R.id.connect_layout)
    RelativeLayout connectLayout;
    @Bind(R.id.about_layout)
    RelativeLayout aboutLayout;
    @Bind(R.id.exit_layout)
    RelativeLayout exitLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_layout, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.pwd_layout, R.id.connect_layout, R.id.about_layout, R.id.exit_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pwd_layout:
                startActivity(new Intent(getActivity(), PwdActivity.class));
                break;
            case R.id.connect_layout:
                break;
            case R.id.about_layout:
                break;
            case R.id.exit_layout:
                getActivity().finish();
                break;
        }
    }
}
