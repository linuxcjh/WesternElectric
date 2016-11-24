package com.nuoman.westernele.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.common.BaseFragment;
import com.nuoman.westernele.common.CommonPresenter;
import com.nuoman.westernele.common.ICommonAction;
import com.nuoman.westernele.common.NuoManConstant;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.mine.model.BaseDataModel;
import com.nuoman.westernele.mine.model.UserInfo;
import com.nuoman.westernele.model.BaseTransModel;
import com.nuoman.westernelectric.R;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.nuoman.westernele.common.utils.AppConfig.getStringConfig;

/**
 * 管理
 * 2016/1/13
 */
public class MineFragment extends BaseFragment implements ICommonAction {

    @Bind(R.id.photo_iv)
    ImageView photoIv;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.position_tv)
    TextView positionTv;
    @Bind(R.id.edit_bt)
    ImageView editBt;
    @Bind(R.id.company_name_tv)
    TextView companyNameTv;
    @Bind(R.id.dep_name_tv)
    TextView depNameTv;
    @Bind(R.id.phone_tv)
    TextView phoneTv;
    @Bind(R.id.email_tv)
    TextView emailTv;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case NuoManConstant.SINGLESELECTION:
                    BaseDataModel picModel = (BaseDataModel) msg.obj;

                    if (picModel.getDictionaryName().equals(getString(R.string.camera_picture))) {//拍照
                        AppTools.getCapturePathNoWater(getActivity(), MineFragment.this);
                    } else if (picModel.getDictionaryName().equals(getString(R.string.photo_picture))) {//相册
                        AppTools.getSystemImage(MineFragment.this);
                    }
                    break;
            }
        }
    };


    private CommonPresenter commonPresenter = new CommonPresenter(this);
    private BaseTransModel transModel = new BaseTransModel();
    private UserInfo info = new UserInfo();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_layout, null);
        ButterKnife.bind(this, view);

        invoke();
        return view;
    }

    /**
     * 调用方法获取数据
     */
    public void invoke() {
        transModel.setUserId(AppTools.getUser().getUserId());
        commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.GETACCOUNTINFODETAIL, transModel, new TypeToken<UserInfo>() {
        });
    }

    /**
     * 获取七牛token
     */
    public void invokeQiNiuToken() {
        commonPresenter.invokeInterfaceObtainData(true, "qiniuTokenCtrl", NuoManService.GETTOKEN, null, new TypeToken<BaseTransModel>() {
        });
    }

    /**
     * 上传图片
     */
    public void invokeUpLoadPic(BaseTransModel transModel) {
        transModel.setUserId(AppTools.getUser().getUserId());
        commonPresenter.invokeInterfaceObtainData(true, "appUserCtrl", NuoManService.SAVEUSERHEADPIC, transModel, new TypeToken<BaseTransModel>() {
        });
    }

    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {

        switch (methodIndex) {
            case NuoManService.GETACCOUNTINFODETAIL:
                if (data != null) {
                    info = (UserInfo) data;
                    AppTools.setImageViewPicture(getActivity(), info.getHeadPicUrl(), photoIv);
                    nameTv.setText(info.getUserName());
                    positionTv.setText(info.getJob());
                    companyNameTv.setText(info.getCompany());
                    depNameTv.setText(info.getDepartment());
                    phoneTv.setText(info.getUserTel());
                    emailTv.setText(info.getMailBox());

                }
                break;
            case NuoManService.GETTOKEN:
                if (data != null) {
                    BaseTransModel model = (BaseTransModel) data;
                    transModel.setToken(model.getToken());
                }
                break;

            case NuoManService.SAVEUSERHEADPIC:
                if (status == 1) {
                    AppTools.getToast("上传成功");
                } else {
                    AppTools.getToast("上传失败");
                }
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.photo_iv, R.id.edit_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo_iv:
                invokeQiNiuToken();
                AppTools.selectPhotoShow(getActivity(), mHandler, NuoManConstant.SINGLESELECTION);
                break;
            case R.id.edit_bt:
                startActivityForResult(new Intent(getActivity(), ChangeInfoActivity.class).putExtra("model", info), 0x11);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 0x11:
                    invoke();
                    break;
                case NuoManConstant.SELECT_PICTURE: //图片
                    String imageP = AppTools.getAbsolutePath(getActivity(), data.getData());
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
                        if (!TextUtils.isEmpty(file.getPath())) {

                            if (!TextUtils.isEmpty(transModel.getToken())) {
                                uploadImageToQiNiu(file.getPath(), transModel.getToken());
                            }
                            AppTools.setImageViewPicture(getActivity(), file.getPath(), photoIv);
//                            this.imagePath = file.getPath();
//                            upLoadHeadPhotoPresenter.paths.clear();
//                            upLoadHeadPhotoPresenter.paths.add(file.getPath());
//                            upLoadHeadPhotoPresenter.uploadFile(upLoadHeadPhotoPresenter.paths);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    }

    private final int CUT = 103;
    private File file;

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

    /**
     * 上传图片到七牛成功后上传打卡信息
     *
     * @param filePath 要上传的图片路径
     * @param token    在七牛官网上注册的token
     */

    private void uploadImageToQiNiu(final String filePath, String token) {
        UploadManager uploadManager = new UploadManager();
        File file = new File(filePath);

        if (file.exists()) {
            uploadManager.put(filePath, file.getName(), token, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject res) {
                    if (info.statusCode == 200) {
                        BaseTransModel transModel = new BaseTransModel();
                        transModel.setUserId(AppTools.getUser().getUserId());
                        transModel.setPicUrl(key);
                        invokeUpLoadPic(transModel);
                    }
                }
            }, null);
        }

    }
}
