package util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import androidx.core.app.ActivityCompat;

import com.sirius.permission.manger.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imp.OnCallBackPermission;

public class P implements PermissionDialogUtils.OnPermissionCallBack {

    public static final String TAG="P[权限管理器]";

    /* 当前Activity */
    private Activity mActivity;
    /* 权限列表 */
    private String[] mPermissions;
    /* 返回码 */
    private int mCode;
    /* 权限说明 */
    private String mNote;
    /* 图标显示 */
    private int drawable;
    /* 需要申请的权限 */
    public List<String> mDeprecated=new ArrayList<>();
    /* Dialog */
    private PermissionDialogUtils dialog;
    /* 回调事件 */
    private OnCallBackPermission callback;

    /* 外部构建 */
    public P(AwardedBuilder awardedBuilder) {
        mActivity=awardedBuilder.mActivity;
        mNote=awardedBuilder.mNote;
        mPermissions=awardedBuilder.mPermissions;
        drawable=awardedBuilder.drawable;
        mCode=awardedBuilder.mCode;
        callback=awardedBuilder.callback;
    }

    /* 遍历所有需要申请的权限 */
    public void interPermission(){
        if(mPermissions  != null ){
            for (String item : mPermissions) {
                int i = ActivityCompat.checkSelfPermission(mActivity, item);
                if(i == PackageManager.PERMISSION_DENIED){
                    mDeprecated.add(item);
                }
            }
        }
    }

    /* 内部构建 */
    public static class AwardedBuilder{
        private Activity mActivity;
        private String[] mPermissions;
        private String mNote="暂无说明";
        private int mCode = 2 << 4;
        private int drawable=R.drawable.icon_permission_error;
        private OnCallBackPermission callback;

        /* 权限This */
        public AwardedBuilder with(Activity mActivity){
            this.mActivity=mActivity;
            return this;
        }

        /* 需要授予的权限列表 */
        public AwardedBuilder permission(String[] mPermissions){
            this.mPermissions=mPermissions;
            return this;
        }

        /* 返回的请求码 */
        public AwardedBuilder code(int mCode){
            this.mCode=mCode;
            return this;
        }

        /* 权限说明 */
        public AwardedBuilder note(String mNote){
            this.mNote=mNote;
            return this;
        }

        /* Drawable */
        public AwardedBuilder drawable(int drawable){
            this.drawable=drawable;
            return this;
        }

        /* 回调事件 */
        public AwardedBuilder setOnPermissionCallBack(OnCallBackPermission callback){
            this.callback=callback;
            return this;
        }

        public P builder(){
            return new P(AwardedBuilder.this);
        }
    }

    /* 开始授权 */
    public void awarded(){
        /* 先判断 */
        interPermission();
        /* 申请权限 */
        if(mActivity == null || !(mActivity instanceof Activity) || mDeprecated == null)
            return;
        String[] array = mDeprecated.toArray(new String[mDeprecated.size()]);
        if(array != null && array.length != 0){
            ActivityCompat.requestPermissions(mActivity,array,mCode);
        }
    }

    /* 用户拒绝之后的处理结果 */
    public void onRequestPermissionDialog(int requestCode,String[] permissions,int[] grantResults){
        /* 存储拒绝的权限 */
        List<String> mDeniedPermission=new ArrayList<>();
        Map<String,Boolean> maps=new HashMap<>();
        if(requestCode == mCode){
            Log.e(TAG,permissions.length +"?"+grantResults.length);
            for (int i = 0; i < grantResults.length; i++) {
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    mDeniedPermission.add(permissions[i]);
                    //决绝的权限判断是否勾选了不在提示
                    boolean isShow = ActivityCompat
                            .shouldShowRequestPermissionRationale(mActivity, permissions[i]);
                    if(isShow){
                        //添加拒绝且不显示的权限
                        maps.put(permissions[i],true);
                    }else {
                        //拒绝的权限下次显示
                        maps.put(permissions[i],false);
                    }
                }
            }

            /* 有权限没有通过提示用户授权 */
            if(mDeniedPermission.size() != 0){
                dialogShow(mDeniedPermission,maps);
                /* 回调给Activity的权限结果(不考虑引导用户去系统权限去设置的结果) */
                if(callback != null){
                    callback.failure(mDeniedPermission,maps);
                }
            }else {
                Log.e(TAG,"已授权！！！！");
                // TO Activity Page
                if(callback != null){
                    callback.successful();
                }
            }
        }
    }

    /* dialog提示权限要求 */
    public void dialogShow(List<String> mDeniedPermission,Map<String,Boolean> maps){
        PermissionDialogUtils dialog = new PermissionDialogUtils(mActivity,
                R.style.permission_dialog,
                mNote,
                drawable,
                mDeniedPermission,
                maps);
        dialog.setOnPermissionCallBack(this);
        dialog.create();
        dialog.show();

        //4.0过时
        WindowManager windowManager = mActivity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int)(display.getWidth() * 0.8); //设置宽度
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    public void open() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT >= 9){
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", mActivity.getPackageName(), null));
        } else if(Build.VERSION.SDK_INT <= 8){
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", mActivity.getPackageName());
        }
        mActivity.startActivity(intent);
    }

    /* 回调给Activity处理 */
    public void setOnCallBackPermission(OnCallBackPermission callback){
        this.callback=callback;
    }
}
