//package com.example.free.mymvpdemo.util.HttpUtils;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//
//
///**
// * Created by Think on 2017/5/11.
// */
//
//public class PublicControl {
//
//    /**
//     * 结束招聘中的职位dialog，和相应的处理 。
//     *  目前为止用到的地方是三个。
//     * @param activity
//     * @param jobId
//     * @param position ，如果没有点击位置需要传，传-1
//     * @param controlCancelSuccess
//     */
//    public void showCancelJobDialog(final Activity activity, final String jobId, final int position, final ControlCancelSuccess controlCancelSuccess) {
//        final DialogManage dialog = new DialogManage(activity);
//        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_cancel_recommend, null);
//        AutoUtils.auto(dialogView);
//        dialog.setContentView(dialogView);
//        dialog.setPositiveButton("取消", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.setDeleteButton("确定结束", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                ((BaseActivity) activity).showLoadingDialog();
//                RequestManager.cannelRecommendPosition(jobId, activity, new HttpResult() {
//                    @Override
//                    public void onError(int errorCode, String error) {
//                        ((BaseActivity) activity).hideLoadingDialog();
//                        UToast.showToast(error);
//                    }
//
//                    @Override
//                    public void onResponse(Object response) {
//                        ((BaseActivity) activity).hideLoadingDialog();
//                        if (position >= 0) {
//                            controlCancelSuccess.cancelSuccess(position);
//                        } else {
//                            controlCancelSuccess.cancelSuccess();
//                        }
//                    }
//                });
//            }
//        });
//        dialog.show();
//    }
//}
