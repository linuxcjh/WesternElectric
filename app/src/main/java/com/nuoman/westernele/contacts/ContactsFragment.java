package com.nuoman.westernele.contacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nuoman.westernelectric.R;
import com.nuoman.westernele.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 管理
 * 2016/1/13
 */
public class ContactsFragment extends BaseFragment {

    public static final int CLIENT_LABEL_INDEX = 0;
    public static final int CLIENT_BUSINESS_INDEX = 1;
    public static final int CLIENT_SLIDE_ALL_COUNT = 2;//Total page count

    @Bind(R.id.client_bt)
    Button clientBt;
    @Bind(R.id.staff_bt)
    Button staffBt;
    @Bind(R.id.content_viewPager)
    ViewPager contentViewPager;


    private ContactsLeaderFragment leaderFragment = new ContactsLeaderFragment();
    private ContactsStaffFragment staffFragment = new ContactsStaffFragment();

    private List<Fragment> fragments = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    //当前页
    public int changeStatus = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_layout, null);
        ButterKnife.bind(this, view);

        initViewPage();
        return view;
    }

    private void initViewPage() {

        fragments.add(leaderFragment);
        fragments.add(staffFragment);
        contentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case CLIENT_LABEL_INDEX:
                        changeStatus = CLIENT_LABEL_INDEX;
                        setLayoutStatus(clientBt, false);

                        break;
                    case CLIENT_BUSINESS_INDEX:
                        changeStatus = CLIENT_BUSINESS_INDEX;
                        setLayoutStatus(staffBt, true);

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public Fragment getItem(int position) {

                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return CLIENT_SLIDE_ALL_COUNT;
            }
        };

        contentViewPager.setOffscreenPageLimit(2);
        contentViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.client_bt, R.id.staff_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.client_bt:
                contentViewPager.setCurrentItem(CLIENT_LABEL_INDEX, true);

                break;
            case R.id.staff_bt:
                contentViewPager.setCurrentItem(CLIENT_BUSINESS_INDEX, true);

                break;
        }
    }


    /**
     * 设置View状态
     *
     * @param button
     */
    private void setLayoutStatus(Button button, boolean isRight) {
        resetLayout();
        button.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlue));
        if (isRight) {
            button.setBackgroundResource(R.drawable.client_top_right_fouces);
        } else {
            button.setBackgroundResource(R.drawable.client_top_left_fouces);

        }
    }

    private void resetLayout() {


        clientBt.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
        staffBt.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));

        clientBt.setBackgroundResource(R.drawable.client_top_left_normal);
        staffBt.setBackgroundResource(R.drawable.client_top_right_normal);

    }
}
