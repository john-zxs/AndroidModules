package com.john.base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：<p>
 * Date：2020/2/10 16:42<p>
 * Updater：
 */
public abstract class BaseActivity extends MPermissionsActivity {
    protected ImmersionBar immersionBar;
    protected TextView tvHeadTitle;
    protected ImageView iv_back;
    protected Context mContext;
    protected String[] needPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,

    };

    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout(savedInstanceState));
        immersionBar = ImmersionBar.with(this);

        setImmersionBar();
        ButterKnife.bind(this);
        mContext = this;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中");
        progressDialog.setCancelable(false);
        initViews();
        initData();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * 设置状态栏
     */
    protected void setImmersionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            immersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorTitle)
                    .statusBarDarkFont(true)
                    .flymeOSStatusBarFontColor(R.color.colorBlack)
                    .init();
        }
    }

    protected abstract int setLayout(Bundle savedInstanceState);

    protected abstract void initViews();
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (immersionBar != null) {
            immersionBar.destroy();
        }

        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 设置头部标题
     *
     * @param title
     */
    protected void setTitle(String title) {
        tvHeadTitle = findViewById(R.id.tv_title);
        tvHeadTitle.setText(title);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFinish();

            }
        });
    }

    protected void doFinish() {
        finish();
    }


    protected void goTarget(Class tClass) {
        goTarget(tClass, null);
    }

    protected void goTarget(Class tClass, Bundle bundle) {
        Intent intent = new Intent(this, tClass);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    protected void goTarget(Class tClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, tClass);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, requestCode);
    }

    protected void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    protected void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //        MobclickAgent.onPause(this);
    }
}
