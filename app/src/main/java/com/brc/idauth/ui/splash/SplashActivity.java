package com.brc.idauth.ui.splash;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.brc.idauth.R;
import com.brc.idauth.base.BaseActivity;
import com.brc.idauth.ui.MainActivity;
import com.daolion.base.anotation.BsActivityAnnotation;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;



@BsActivityAnnotation(isFullScreen = true, layoutId = R.layout.activity_splash)

public class SplashActivity extends BaseActivity implements SplashContract.View {


    private Runnable switchRunnable;

    public static long SPLASH_LOADING_TIME = 1 * 1800;
    private final int REQUEST_PERMISSION_CODE = 7;

    /**
     * 需要获得权限的List
     */
    private List<String> permissionList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAllDatas();
        new SplashPresenter(this, new SplashModel());
    }

    private void initAllDatas() {

        boolean hasPermissions = getHasPermissions(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA}
        );

        switchRunnable = () -> {
            MainActivity.launch(getApplicationContext());
            finish();
        };

        if (hasPermissions) {
            getHandler().postDelayed(switchRunnable, SPLASH_LOADING_TIME);
        }
    }

    /**
     * 6.0及以上版本获取动态权限
     * 判断是否有所有的权限 ，没有去申请，有的话返回True;
     */
    private boolean getHasPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            permissionList.clear();
            for (int i = 0; i < permissions.length; i++) {
                if (checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permissions[i]);
                }
            }
            if (permissionList != null && permissionList.size() > 0) {
                //请求权限
                if (permissionList.size() > 0) {
                    String[] perStrs = new String[permissionList.size()];
                    permissionList.toArray(perStrs);
                    requestPermissions(perStrs, REQUEST_PERMISSION_CODE);
                }
                return false;
            } else {
                return true;
            }
        }
        return true;
    }


    public void registerDeviceResult(boolean success) {

    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length == permissionList.size()) {
                Log.i("", "请求权限成功");
                getHandler().postDelayed(switchRunnable, SPLASH_LOADING_TIME);
            } else {
                Log.i("", "请求权限失败");
                Toast.makeText(getApplicationContext(), "请在设置中打开相应权限", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
