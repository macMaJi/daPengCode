package com.example.free.mymvpdemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;
import com.example.free.mymvpdemo.manager.MiracleConstant;
import com.example.free.mymvpdemo.manager.MiracleTime;
import com.example.free.mymvpdemo.util.MiraclePicture;
import com.example.free.mymvpdemo.util.ULog;

import java.io.File;

import butterknife.BindView;

public class UserDialogActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.select_pic_camera)
    Button selectPicCamera;
    @BindView(R.id.select_pic_photo)
    Button selectPicPhoto;
    @BindView(R.id.content_view_parent)
    LinearLayout contentViewParent;
    @BindView(R.id.select_pic_cancel)
    Button selectPicCancel;
    @BindView(R.id.select_image_layout)
    LinearLayout selectImageLayout;
    @BindView(R.id.root_view)
    LinearLayout rootView;

    private Uri output;
    private String filename;
    private String filepath;
    private boolean isClip;
    private static final int PHOTO_CLIP = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_dialog;
    }

    @Override
    protected void initView() {
        rootView.setOnClickListener(this);
        selectPicPhoto.setOnClickListener(this);
        selectPicCamera.setOnClickListener(this);
        selectPicCancel.setOnClickListener(this);
//        TranslateAnimation animation = new TranslateAnimation(0, 0, 500, 0);
//        animation.setDuration(1000);//设置动画持续时间
//        animation.setRepeatCount(0);//设置重复次数
//        selectImageLayout.startAnimation(animation);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }



    /**
     * 接受返回的图片
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case MiracleConstant.REQUEST_CAMERA :
                switch (resultCode) {
                    case -1://-1表示拍照成功
                        File file = new File(MiraclePicture.FILE_PATH_WITHOUT_NAME + MiraclePicture.FILE_NAME);
                        if (file.exists()) {
                            String fileCompress = MiraclePicture.getSmallBitmap(file);
                            if (!TextUtils.isEmpty(fileCompress)) {
                                Intent intent = new Intent();
                                String name = fileCompress.substring(fileCompress.indexOf("/") + 1, fileCompress.length());
                                intent.putExtra("userIcon", Uri.fromFile(new File(fileCompress)).toString());
                                intent.putExtra("filename", name);
                                intent.putExtra("filepath", fileCompress);
                                setResult(RESULT_OK, intent);
                                setFinishAndCancelSystemResult();

                            } else {
                                ULog.e("PicChooseActivity", "拍照失败");
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            case MiracleConstant.REQUEST_PHOTO :
                if (data != null) {
                    if (isClip) {
                        photoClip(data.getData());
                    } else {
                        Uri uri = data.getData();
                        String imgPath = MiraclePicture.getImageAbsolutePath(UserDialogActivity.this, uri);
                        String imagePath = MiraclePicture.takeSizeAndQualityCompress(imgPath);
                        if (!TextUtils.isEmpty(imagePath)) {
                            Intent intent = new Intent();
                            String name = imagePath.substring(imagePath.indexOf("/") + 1, imagePath.length());
                            intent.putExtra("userIcon", data.getData().toString());
                            intent.putExtra("filename", name);
                            intent.putExtra("filepath", imagePath);
                            setResult(RESULT_OK, intent);
                            setFinishAndCancelSystemResult();

                        } else {
                            ULog.e("UserIconDialog", "照片选择失败");
                        }
                    }
                }
                break;
            case MiracleConstant.PHOTO_CLIP:
                if (data != null) {
                    if (output != null) {
                        Intent intent = new Intent();
                        intent.putExtra("userIcon", output.toString());
                        intent.putExtra("filename", filename);
                        intent.putExtra("filepath", filepath);
                        setResult(RESULT_OK, intent);
                        setFinishAndCancelSystemResult();
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 监听Back键按下事件
     * super.onBackPressed()会自动调用finish()方法,关闭当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        setFinishAndCancelSystemResult();
    }




    private void photoClip(Uri uri) {
        filename = MiracleTime.getNow().getTime() + ".jpg";
        File file = new File(MiraclePicture.FILE_PATH_WITHOUT_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
        filepath = MiraclePicture.FILE_PATH_WITHOUT_NAME + filename;
        output = Uri.fromFile(new File(filepath));
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
        startActivityForResult(intent, PHOTO_CLIP);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.root_view:
                setFinishAndCancelSystemResult();
                break;
            case R.id.select_pic_photo:
                MiraclePicture.toMyPhotos(UserDialogActivity.this);
                selectImageLayout.setVisibility(View.GONE);
                break;
            case R.id.select_pic_camera:
                MiraclePicture.toMyCamera(UserDialogActivity.this);
                break;
            case R.id.select_pic_cancel:
                setFinishAndCancelSystemResult();
                break;
        }
    }

    /**
     * 让自己设置的 出场 离场 动画生效
     */
    private void setFinishAndCancelSystemResult() {
        finish();

        //这段代码至关重要， 出场 离场 动画
        overridePendingTransition(0,0);
    }
}
