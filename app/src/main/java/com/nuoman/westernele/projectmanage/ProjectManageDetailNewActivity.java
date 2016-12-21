package com.nuoman.westernele.projectmanage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BaseActivity;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.CustomProgressDialog;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.common.utils.DateUtil;
import com.nuoman.westernele.mine.model.BaseDataModel;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernele.projectmanage.model.NodePic;
import com.nuoman.westernele.projectmanage.model.NodePicActivity;
import com.nuoman.westernele.projectmanage.model.NodePicParameter;
import com.nuoman.westernele.projectmanage.model.ProjectDetailItemModel;
import com.nuoman.westernele.projectmanage.model.ProjectDetailModel;
import com.nuoman.westernele.projectmanage.model.SaveNodeParameter;
import com.nuoman.westernelectric.R;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.nuoman.westernele.common.utils.AppConfig.getStringConfig;

/**
 * 详情
 */
public class ProjectManageDetailNewActivity extends BaseActivity implements ICommonAction {


    @Bind(R.id.title_left_tv)
    TextView titleLeftTv;
    @Bind(R.id.title_mid_tv)
    TextView titleMidTv;
    @Bind(R.id.title_right_tv)
    TextView titleRightTv;
    @Bind(R.id.name_1_tv)
    TextView name1Tv;
    @Bind(R.id.status_1_iv)
    ImageView status1Iv;
    @Bind(R.id.root_1_layout)
    RelativeLayout root1Layout;
    @Bind(R.id.gridLayout_1_tect)
    GridLayout gridLayout1Tect;
    @Bind(R.id.name_2_tv)
    TextView name2Tv;
    @Bind(R.id.status_2_iv)
    ImageView status2Iv;
    @Bind(R.id.root_2_layout)
    RelativeLayout root2Layout;
    @Bind(R.id.gridLayout_2_tect)
    GridLayout gridLayout2Tect;
    @Bind(R.id.name_3_tv)
    TextView name3Tv;
    @Bind(R.id.status_3_iv)
    ImageView status3Iv;
    @Bind(R.id.root_3_layout)
    RelativeLayout root3Layout;
    @Bind(R.id.gridLayout_3_tect)
    GridLayout gridLayout3Tect;
    @Bind(R.id.name_4_tv)
    TextView name4Tv;
    @Bind(R.id.status_4_iv)
    ImageView status4Iv;
    @Bind(R.id.root_4_layout)
    RelativeLayout root4Layout;
    @Bind(R.id.gridLayout_4_tect)
    GridLayout gridLayout4Tect;
    private ProjectDetailItemModel model = new ProjectDetailItemModel();

    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();
    private File file;

    public CustomProgressDialog progressDialog;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case NuoManConstant.SINGLESELECTION:
                    BaseDataModel picModel = (BaseDataModel) msg.obj;

                    if (picModel.getDictionaryName().equals(getString(R.string.camera_picture))) {//拍照
                        AppTools.getCapturePathNoWater(ProjectManageDetailNewActivity.this, null);
                    } else if (picModel.getDictionaryName().equals(getString(R.string.photo_picture))) {//相册
                        AppTools.getSystemImage(ProjectManageDetailNewActivity.this);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details_new_layout);
        ButterKnife.bind(this);
        initView();
        invoke();
        invokeQiNiuToken();
    }


    private void initView() {
        progressDialog = new CustomProgressDialog(this);
        titleMidTv.setText("详情");

    }

    /**
     * 调用方法获取数据
     */
    public void invoke() {
        transModel.setUserId(AppTools.getUser().getUserId());
        transModel.setOrderId(getIntent().getStringExtra("id"));
        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.GETNODELISTBYORDERID, transModel, new TypeToken<ProjectDetailItemModel>() {
        });
        progressDialog.show();
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.GETNODELISTBYORDERID:
                progressDialog.dismiss();
                if (data != null) {
                    model = (ProjectDetailItemModel) data;

                    if (AppTools.getUser().getRoleId().equals("8")) {
                        root1Layout.performClick();
                    } else if (AppTools.getUser().getRoleId().equals("9")) {
                        root2Layout.performClick();
                    } else if (AppTools.getUser().getRoleId().equals("10")) {
                        root3Layout.performClick();
                    } else {
                        root1Layout.performClick();
                    }
                }
                break;
            case NuoManService.NODE_PIC:
                NodePic nodePic = (NodePic) data;
                Intent intent = new Intent(this, NodePicActivity.class);
                intent.putExtra("nodePic", nodePic);
                startActivity(intent);
                break;

            case NuoManService.GETTOKEN:
                if (data != null) {
                    BaseTransModel model = (BaseTransModel) data;
                    transModel.setToken(model.getToken());
                }
                break;

            case NuoManService.SAVE_NODE_INFO:
                progressDialog.dismiss();
                if (status == 1) {
                    file = null;
                    invoke();
                    AppTools.getToast("保存成功");
                } else {
                    AppTools.getToast("保存失败");
                }
                break;
        }
    }


    @OnClick(R.id.title_left_tv)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.root_1_layout, R.id.root_2_layout, R.id.root_3_layout, R.id.root_4_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.root_1_layout:
                setView(gridLayout1Tect, status1Iv, model.getTech());
                break;
            case R.id.root_2_layout:
                setView(gridLayout2Tect, status2Iv, model.getPurchase());
                break;
            case R.id.root_3_layout:
                setView(gridLayout3Tect, status3Iv, model.getProduction());
                break;
            case R.id.root_4_layout:
                setView(gridLayout4Tect, status4Iv, model.getTransport());
                break;
        }
    }


    private void setView(GridLayout gridLayout1Tect, ImageView status1Iv, List<ProjectDetailModel> itemModels) {
        if (gridLayout1Tect.getVisibility() == View.VISIBLE) {
            gridLayout1Tect.setVisibility(View.GONE);
            status1Iv.setBackgroundResource(R.drawable.btn_dropdown01_03);
        } else {
            gridLayout1Tect.setVisibility(View.VISIBLE);
            status1Iv.setBackgroundResource(R.drawable.btn_dropdown02_03);
        }
        setApprovalFlowData(gridLayout1Tect, itemModels);
    }

    /**
     * 设置审批流程列表
     *
     * @param transport
     */
    public void setApprovalFlowData(GridLayout flowGridlayout, final List<ProjectDetailModel> transport) {
/*
        if (flowGridlayout.getChildCount() > 0) {
            return;
        }*/
        if (transport != null && transport.size() > 0) {

            flowGridlayout.removeAllViews();
            int size = transport.size();

            if (size > 0) {
                final int column = 1; //列数
                int rows = size;//行数

                flowGridlayout.setRowCount(rows);
                flowGridlayout.setColumnCount(column);

                int count = 0;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < column; j++) {
                        if (size > count) {
                            final ProjectDetailModel model = transport.get(count);
                            View view = LayoutInflater.from(this).inflate(R.layout.project_details_inner_item_layout, null);
                            TextView title_name_tv = (TextView) view.findViewById(R.id.title_name_tv);
                            final TextView item_one_tv = (TextView) view.findViewById(R.id.item_one_tv);
                            final TextView item_two_tv = (TextView) view.findViewById(R.id.item_two_tv);
                            final TextView item_three_tv = (TextView) view.findViewById(R.id.item_three_tv);
                            final TextView item_four_tv = (TextView) view.findViewById(R.id.item_four_tv);
                            final ImageView imageView = (ImageView) view.findViewById(R.id.image_iv);


                            final ImageView edit_iv = (ImageView) view.findViewById(R.id.edit_iv);
                            final ImageView commit_iv = (ImageView) view.findViewById(R.id.commit_iv);
                            final ImageView camera_03 = (ImageView) view.findViewById(R.id.camera_03);
                            final LinearLayout edit_layout = (LinearLayout) view.findViewById(R.id.edit_layout);

                            //控件值初始化
                            edit_layout.setVisibility(View.GONE);
                            item_one_tv.setEnabled(false);
                            item_two_tv.setEnabled(false);
                            item_three_tv.setEnabled(false);
                            item_four_tv.setEnabled(false);
                            title_name_tv.setText(model.getNodeName());
                            String planStart = model.getPlanStartDate();
                            String planEnd = model.getPlanEndDate();
                            String realStart = model.getActualStartDate();
                            String realEnd = model.getActualEndDate();
                            item_one_tv.setText(String.format("计划开始    %s", planStart));
                            item_two_tv.setText(String.format("计划完工    %s", planEnd));
                            Date planStartDate = null, planEndDate = null,
                                    realStartDate = null, realEndDate = null;
                            if (!TextUtils.isEmpty(planStart)) {
                                planStartDate = DateUtil.convertStringToDate("yyyy.MM.dd", planStart);
                            }
                            if (!TextUtils.isEmpty(planEnd)) {
                                planEndDate = DateUtil.convertStringToDate("yyyy.MM.dd", planEnd);
                            }
                            if (!TextUtils.isEmpty(realStart)) {
                                realStartDate = DateUtil.convertStringToDate("yyyy.MM.dd", realStart);
                            }
                            if (!TextUtils.isEmpty(realEnd)) {
                                realEndDate = DateUtil.convertStringToDate("yyyy.MM.dd", realEnd);
                            }
                            if (realStartDate != null && planStartDate != null &&
                                    realStartDate.getTime() > planStartDate.getTime()) {
                                item_three_tv.setTextColor(Color.RED);
                            } else {
                                item_three_tv.setTextColor(Color.BLACK);
                            }
                            if (realEndDate != null && planEndDate != null &&
                                    realEndDate.getTime() > planEndDate.getTime()) {
                                item_four_tv.setTextColor(Color.RED);
                            } else {
                                item_four_tv.setTextColor(Color.BLACK);
                            }
                            item_three_tv.setText(String.format("实际开始    %s", realStart));
                            item_four_tv.setText(String.format("实际完工    %s", realEnd));

                            if (model.getIsEditable().equals("0")) {
                                edit_layout.setVisibility(View.GONE);
                            } else {
                                edit_layout.setVisibility(View.VISIBLE);
                            }

                            if (model.getHasPhoto().equals("0")) {
                                imageView.setImageResource(R.drawable.photo_01_03);
                            } else {
                                imageView.setImageResource(R.drawable.photo_02_03);
                            }

                            //图片按钮
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (model.getHasPhoto().equals("0")) {
                                        Toast.makeText(AppConfig.getContext(), "当前没有节点照片", Toast.LENGTH_SHORT).show();
                                    } else {
                                        NodePicParameter nodePicParameter = new NodePicParameter();
                                        nodePicParameter.setNodeId(model.getNodeId());
                                        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.NODE_PIC,
                                                nodePicParameter, new TypeToken<NodePic>() {
                                                });
                                    }

                                }
                            });


                            //编辑按钮
                            edit_iv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    imageView.setVisibility(View.GONE);
                                    edit_iv.setVisibility(View.GONE);

                                    commit_iv.setVisibility(View.VISIBLE);
                                    if (model.getIsPhotoable().equals("0")) {
                                        camera_03.setVisibility(View.GONE);
                                    } else {
                                        camera_03.setVisibility(View.VISIBLE);
                                    }

                                    item_one_tv.setEnabled(true);
                                    item_two_tv.setEnabled(true);
                                    item_three_tv.setEnabled(true);
                                    item_four_tv.setEnabled(true);

                                }
                            });

                            //提交按钮
                            commit_iv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    imageView.setVisibility(View.VISIBLE);
                                    edit_iv.setVisibility(View.VISIBLE);

                                    commit_iv.setVisibility(View.GONE);
                                    camera_03.setVisibility(View.GONE);
                                    item_one_tv.setEnabled(false);
                                    item_two_tv.setEnabled(false);
                                    item_three_tv.setEnabled(false);
                                    item_four_tv.setEnabled(false);


                                    SaveNodeParameter saveNodeParameter = new SaveNodeParameter();
                                    String planStartTime = item_one_tv.getText().toString();
                                    String planEndTime = item_two_tv.getText().toString();
                                    String realStartTime = item_three_tv.getText().toString();
                                    String realEndTime = item_four_tv.getText().toString();
                                    if (planStartTime.length() > 4) {
                                        saveNodeParameter.setPlanStartTime(planStartTime.
                                                substring(4, planStartTime.length()).trim());
                                    } else {
                                        saveNodeParameter.setPlanStartTime("");
                                    }
                                    if (planEndTime.length() > 4) {
                                        saveNodeParameter.setPlanEndTime(planEndTime.
                                                substring(4, planEndTime.length()).trim());
                                    } else {
                                        saveNodeParameter.setPlanEndTime("");

                                    }
                                    if (realStartTime.length() > 4) {
                                        saveNodeParameter.setActureStartTime(realStartTime.
                                                substring(4, realStartTime.length()).trim());
                                    } else {
                                        saveNodeParameter.setActureStartTime("");
                                    }
                                    if (realEndTime.length() > 4) {
                                        saveNodeParameter.setActureEndTime(realEndTime.
                                                substring(4, realEndTime.length()).trim());
                                    } else {
                                        saveNodeParameter.setActureEndTime("");
                                    }

                                    saveNodeParameter.setSaleOrderId(transModel.getOrderId());
                                    saveNodeParameter.setOperId(AppTools.getUser().getUserId());
                                    saveNodeParameter.setNodeId(model.getNodeId());
                                    if (file != null && !TextUtils.isEmpty(file.getPath())) {
                                        if (!TextUtils.isEmpty(transModel.getToken())) {
                                            String uploadFileName = AppTools.getUser().getUserId() + System.currentTimeMillis();
                                            saveNodeParameter.setNodePic(uploadFileName);
                                            uploadImageToQiNiu(file.getPath(), uploadFileName, transModel.getToken(), saveNodeParameter);
                                        }

                                    } else {
                                        saveNodeParameter.setNodePic("");
                                        invokeSaveNode(saveNodeParameter);
                                    }

                                }
                            });


                            //拍照按钮
                            camera_03.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AppTools.selectPhotoShow(ProjectManageDetailNewActivity.this, mHandler, NuoManConstant.SINGLESELECTION);
                                }
                            });

                            //选择日期
                            item_one_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    AppTools.obtainData(ProjectManageDetailNewActivity.this, item_one_tv, "计划开始    ");


                                }
                            });
                            item_two_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    AppTools.obtainData(ProjectManageDetailNewActivity.this, item_two_tv, "计划完工    ");

                                }
                            });
                            item_three_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AppTools.obtainData(ProjectManageDetailNewActivity.this, item_three_tv, "实际开始    ");


                                }
                            });
                            item_four_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AppTools.obtainData(ProjectManageDetailNewActivity.this, item_four_tv, "实际完工    ");

                                }
                            });

                            item_one_tv.setEnabled(false);
                            item_two_tv.setEnabled(false);
                            item_three_tv.setEnabled(false);
                            item_four_tv.setEnabled(false);

                            count++;

                            GridLayout.Spec rowSpec = GridLayout.spec(i);
                            GridLayout.Spec columnSpec = GridLayout.spec(j);
                            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                            params.setGravity(Gravity.FILL);
                            flowGridlayout.addView(view, params);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case NuoManConstant.SELECT_PICTURE: //图片
                    String imageP = AppTools.getAbsolutePath(ProjectManageDetailNewActivity.this, data.getData());
                    file = new File(imageP);
                    startPhotoZoom(Uri.fromFile(file));
                    break;
                case NuoManConstant.CAMERA_REQUEST_CODE: //拍照
                    String cmPath = getStringConfig("cameraPath", "");
                    file = new File(cmPath);
                    startPhotoZoom(Uri.fromFile(file));
                    break;
                case CUT:
                    try {
                        Bundle extras = data.getExtras();
                        Bitmap photo = extras.getParcelable("data");
                        FileOutputStream outputStream = new FileOutputStream(file);
                        photo.compress(Bitmap.CompressFormat.JPEG, 100,
                                outputStream);
                        outputStream.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    /**
     * 获取七牛token
     */
    public void invokeQiNiuToken() {
        commonPresenter.invokeInterfaceObtainData(true, "qiniuTokenCtrl", NuoManService.GETTOKEN, null, new TypeToken<BaseTransModel>() {
        });
    }

    /**
     * 保存节点信息
     */
    public void invokeSaveNode(SaveNodeParameter saveNodeParameter) {
        commonPresenter.invokeInterfaceObtainData(true, "appNodeCtrl", NuoManService.SAVE_NODE_INFO,
                saveNodeParameter, null);
        progressDialog.show();
    }

    /**
     * 上传图片到七牛成功后上传信息
     *
     * @param filePath 要上传的图片路径
     * @param token    在七牛官网上注册的token
     */

    private void uploadImageToQiNiu(final String filePath, String key, String token, final SaveNodeParameter saveNodeParameter) {
        UploadManager uploadManager = new UploadManager();
        File file = new File(filePath);

        if (file.exists()) {
            uploadManager.put(filePath, key, token, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject res) {
                    if (info.isOK()) {
                        invokeSaveNode(saveNodeParameter);
                    }
                }
            }, null);
        }

    }


    private final int CUT = 103;

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CUT);
    }


}
