package com.nuoman.westernele.projectmanage.model;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernelectric.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NodePicActivity extends BaseActivity {

    @Bind(R.id.iv_node_pic)
    ImageView iv_node_pic;
    @Bind(R.id.tv_node_pic_plan_time)
    TextView tv_node_pic_plan_time;
    @Bind(R.id.tv_node_pic_real_time)
    TextView tv_node_pic_real_time;
    @Bind(R.id.tv_node_pic_tip)
    TextView tv_node_pic_tip;
    @Bind(R.id.ll_info)
    LinearLayout ll_info;

    private NodePic nodePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pic_layout);
        ButterKnife.bind(this);
        initVariable();
        initInterface();
    }

    private void initVariable() {
        nodePic = (NodePic) getIntent().getSerializableExtra("nodePic");
    }

    private void initInterface() {
        ll_info.getBackground().setAlpha(60);
        tv_node_pic_plan_time.setText("计划完工时间" + System.getProperty("line.separator") + nodePic.getPlanEndDate());
        tv_node_pic_real_time.setText("实际完工时间" + System.getProperty("line.separator") + nodePic.getActualEndDate());
        switch (nodePic.getStatus()) {
            case "1":
                tv_node_pic_tip.setTextColor(Color.BLACK);
                tv_node_pic_tip.setText("完成");
                break;
            case "2":
                tv_node_pic_tip.setTextColor(Color.RED);
                tv_node_pic_tip.setText("超时");
                break;
        }
        AppTools.setImageViewClub(this, nodePic.getPicUrl(),
                iv_node_pic);
    }

    @OnClick({R.id.iv_node_pic_close})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_node_pic_close:
                finish();
                break;
        }
    }


}
