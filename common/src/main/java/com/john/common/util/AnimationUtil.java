package com.john.common.util;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: 河北省通信建设有限公司网络分公司<p>
 * Author: John<p>
 * Description: <p>
 * Date: 2019/8/5 18:09<p>
 * Updater：<p>
 */
public class AnimationUtil {
    public static List<View> animateViews = new ArrayList<>();

    public static void show(final View view) {
        if (animateViews.contains(view)) {
            return;
        }
        if (view.getVisibility() == View.VISIBLE) return;
        animateViews.add(view);
        view.setVisibility(View.VISIBLE);
        ValueAnimator va = ValueAnimator.ofInt(0, view.getLayoutParams().height); //显示view，高度从0变到height值
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int h = (Integer) valueAnimator.getAnimatedValue();
                //动态更新view的高度
                view.getLayoutParams().height = h;
                view.requestLayout();
            }
        });
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animateViews.remove(view);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        va.setDuration(1000);
        //开始动画
        va.start();
    }

    public static void hide(final View view) {
        if (animateViews.contains(view)) return;
        if (view.getVisibility() != View.VISIBLE) return;
        final int heght = view.getHeight();
        ValueAnimator va = ValueAnimator.ofInt(heght, 0);
        ;//隐藏view，高度从height变为0
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int h = (Integer) valueAnimator.getAnimatedValue();
                //动态更新view的高度
                view.getLayoutParams().height = h;
                view.requestLayout();
            }
        });
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                view.getLayoutParams().height = heght;
                animateViews.remove(view);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        va.setDuration(1000);
        //开始动画
        va.start();
    }
}
