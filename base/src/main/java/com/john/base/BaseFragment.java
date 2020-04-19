package com.john.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Copyright：河北省通信建设有限公司网络分公司<p>
 * Author：John<p>
 * Description：<p>
 * Date：2020/2/11 15:16<p>
 * Updater：
 */
public abstract class BaseFragment extends Fragment implements Serializable {
    protected View mContentView;
    protected Unbinder mUnbinder;
    protected Context mContext;
    protected TextView tvHeadTitle;
    protected ProgressDialog progressDialog;
    protected ImageView iv_back;
    protected boolean isCreated = false;
    /**
     * 是否完成view的初始化
     */
    protected boolean isCreatedView = false;
    /**
     * fragment是否可见状态
     */
    protected boolean isVisible = false;
    /**
     * 是否加载过数据了
     */
    protected boolean isLoad = false;
    /**
     * <p>是否可以多次加载数据</p>
     * true-多次调用setData false-只在fragment初始化完成后,调用一次
     */
    protected boolean loadMoreTimes = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = LayoutInflater.from(getActivity()).inflate(setLayout(), null);
        mUnbinder = ButterKnife.bind(this, mContentView);
        mContext = getContext();
        initBaseView();
        initView();
        //把所有view都准备完毕了
        isCreatedView = true;
        initFinish();

        return mContentView;
    }

    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    /**
     * 这里去检查是否可见，并且是否完成createView
     */
    public void initFinish() {
        //该fragment已经创建了
        //该fragment的view已经创建
        //该fragment对用户可见
        if (isCreated && isCreatedView && isVisible) {
            //没有加载过数据,则直接加载数据,
            // 已经加载过,则判断是否为多次更新数据模式
            if (!isLoad || isLoad && loadMoreTimes) {
                isLoad = true;
                setData();
            }
        }
    }

    protected abstract int setLayout();

    protected abstract void initView();

    /**
     * 在该方法中去设置view上的数据
     */
    protected abstract void setData();

    protected void initBaseView() {
        mContext = getContext();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("加载中");
        progressDialog.setCancelable(false);
    }

    /**
     * 设置头部标题
     *
     * @param title
     */
    protected void setTitle(String title) {
        tvHeadTitle = mContentView.findViewById(R.id.tv_title);
        if (tvHeadTitle != null) {
            tvHeadTitle.setText(title);
        }
        //        iv_back = mContentView.findViewById(R.id.iv_back);
        ////        iv_back.setVisibility(View.GONE);
    }

    protected void goTarget(Class tClass) {
        Intent intent = new Intent(mContext, tClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void goTarget(Class tClass, Bundle bundle) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).goTarget(tClass, bundle);
        } else {
            Intent intent = new Intent(mContext, tClass);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        }
    }

    /**
     * 调用给方法,请不要注释activity的super.onActivityResult()
     *
     * @param tClass
     * @param bundle
     * @param code
     */
    protected void goTarget(Class tClass, Bundle bundle, int code) {
        Intent intent = new Intent(mContext, tClass);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, code);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mContentView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegisterEventBus();
        isCreated = false;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initFinish();
    }

    protected abstract String childName();

    /**
     * 此方法目前仅适用于标示ViewPager中的Fragment是否真实可见
     * For 友盟统计的页面线性不交叉统计需求
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        initFinish();

        if (isCreated) {
            if (isVisibleToUser) {
                //                MobclickAgent.onPageStart(childName()); //统计页面("MainScreen"为页面名称，可自定义)
            } else {
                //                MobclickAgent.onPageEnd(childName());
            }
        }
    }


}
