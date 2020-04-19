package com.john.common.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Copyright: 河北省通信建设有限公司网络分公司<p>
 * Author: John<p>
 * Description: <p>
 * Date: 2019/6/18 15:12<p>
 * Updater：<p>
 */
public class FragmentUtil {
    Fragment curFragment;
    Fragment[] fragments;
    FragmentManager manager;
    private int container;

    private FragmentUtil(FragmentActivity activity, int container) {
        this.manager = activity.getSupportFragmentManager();
        this.container = container;
    }

    /**
     * 新建一个util
     *
     * @param activity  activity
     * @param container container的id
     * @return
     */
    public static FragmentUtil newInstance(FragmentActivity activity, int container) {
        return new FragmentUtil(activity, container);
    }

    /**
     * 设置container id
     *
     * @param container
     */
    public void setContainer(int container) {
        this.container = container;
    }

    public Fragment getCurFragment() {
        return curFragment;
    }

    public Fragment[] getFragments() {
        return fragments;
    }

    public void setFragments(Fragment[] fragments) {
        this.fragments = fragments;
    }

    public void switchFragment(int index) {
        if (fragments == null || fragments.length <= 0 || index >= fragments.length) return;
        switchFragment(fragments[index]);
    }

    public void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下    
            if (curFragment != null) {
                transaction.hide(curFragment);
            }
            transaction.add(container, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(curFragment).show(targetFragment);
        }
        transaction.commit();
        curFragment = targetFragment;
    }

}
