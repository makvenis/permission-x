package com.sirius.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import imp.OnCallBackPermission;
import util.P;
import util.PermissionGroup;

public class MainActivity extends AppCompatActivity {

    private P p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] ps=new String[]{Manifest.permission.READ_CALL_LOG};
        String note="\u3000\u3000为保证App的正常运行，需要部分权限，我们承诺不会保存获取用户的私有信息。";

        p = new P.AwardedBuilder()
                .with(this) //上下文
                //.permission(ps)
                .drawable(R.drawable.qx) //拒绝后的图标
                .permission(PermissionGroup.PHONE) //权限
                .note(note) //提示语句
                .setOnPermissionCallBack(new OnCallBackPermission() { //回调权限结果
                    @Override
                    public void successful() {
                        Toast.makeText(MainActivity.this,"权限通过",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(List<String> mDeniedPermission, Map<String, Boolean> maps) {
                        Toast.makeText(MainActivity.this,"拒绝了一些权限",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .code(300) //请求码
                .builder();
        p.awarded();//开始申请
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        p.onRequestPermissionDialog(requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
