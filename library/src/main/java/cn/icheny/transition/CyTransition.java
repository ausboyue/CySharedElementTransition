package cn.icheny.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Element transition tool
 *
 * @author www.icheny.cn
 * @date 2018.05.31
 */
public class CyTransition {

    private static final String TRANSITION_MATERIALS = "CY_TRANSITION_TRANSITION_MATERIALS";//Extra key
    private static final long DEFAULT_TRANSITION_DURATION = 600;// default 600 milliseconds
    private static final TimeInterpolator DEFAULT_TIME_INTERPOLATOR = new LinearInterpolator();// default uniform motion


    public static void startActivity(@NonNull Intent intent, @NonNull Activity act, @NonNull View... views) {
        startActivityForResult(intent, -1, act, views);
    }

    public static void startActivity(@NonNull Intent intent, @NonNull Fragment frag, @NonNull View... views) {
        startActivityForResult(intent, -1, frag, views);
    }

    public static void startActivity(@NonNull Intent intent, @NonNull android.app.Fragment frag, @NonNull View... views) {
        startActivityForResult(intent, -1, frag, views);
    }

    public static void startActivityForResult(@NonNull Intent intent, int requestCode, @NonNull Activity activity, @NonNull View... views) {
        Materials materials = Materials.createMaterials(views);
        intent.putParcelableArrayListExtra(TRANSITION_MATERIALS, materials.getViewAttrs());
        activity.startActivityForResult(intent, requestCode);
        // Disable system default transition animation
        activity.overridePendingTransition(0, 0);
    }

    public static void startActivityForResult(@NonNull Intent intent, int requestCode, @NonNull Fragment frag, @NonNull View... views) {
        Materials materials = Materials.createMaterials(views);
        intent.putParcelableArrayListExtra(TRANSITION_MATERIALS, materials.getViewAttrs());
        frag.startActivityForResult(intent, requestCode);
        final Activity activity = frag.getActivity();
        if (activity != null) {
            // Disable system default transition animation
            activity.overridePendingTransition(0, 0);
        }
    }
    public static void startActivityForResult(@NonNull Intent intent, int requestCode, @NonNull android.app.Fragment frag, @NonNull View... views) {
        Materials materials = Materials.createMaterials(views);
        intent.putParcelableArrayListExtra(TRANSITION_MATERIALS, materials.getViewAttrs());
        frag.startActivityForResult(intent, requestCode);
        final Activity activity = frag.getActivity();
        if (activity != null) {
            // Disable system default transition animation
            activity.overridePendingTransition(0, 0);
        }
    }


    public static void runEnterAnim(Activity activity) {
        runEnterAnim(activity, DEFAULT_TRANSITION_DURATION);
    }

    public static void runEnterAnim(Activity activity, long duration) {
        runEnterAnim(activity, duration, DEFAULT_TIME_INTERPOLATOR);
    }

    public static void runEnterAnim(Activity activity, Animator.AnimatorListener listener) {
        runEnterAnim(activity, DEFAULT_TRANSITION_DURATION, DEFAULT_TIME_INTERPOLATOR, listener);
    }

    public static void runEnterAnim(Activity activity, long duration, TimeInterpolator interpolator) {
        runEnterAnim(activity, duration, interpolator, null);
    }

    public static void runEnterAnim(Activity activity, long duration, Animator.AnimatorListener listener) {
        runEnterAnim(activity, duration, DEFAULT_TIME_INTERPOLATOR, listener);
    }

    /**
     * 界面进入过渡动画
     *
     * @param activity
     * @param duration
     * @param interpolator
     * @param listener
     */
    public static void runEnterAnim(Activity activity, final long duration, final TimeInterpolator interpolator, final Animator.AnimatorListener listener) {

        final ArrayList<ViewAttrs> attrs = activity.getIntent().getParcelableArrayListExtra(TRANSITION_MATERIALS);
        if (null == attrs || attrs.isEmpty()) {
            return;
        }

        for (final ViewAttrs attr : attrs) {
            final View view = activity.findViewById(attr.getId());
            if (null == view) {
                continue;
            }
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);

                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    view.setPivotX(0);
                    view.setPivotY(0);
                    view.setTranslationX(attr.getScreenX() - location[0]);
                    view.setTranslationY(attr.getScreenY() - location[1]);
                    view.setScaleX(attr.getWidth() * 1.0f / view.getWidth());
                    view.setScaleY(attr.getHeight() * 1.0f / view.getHeight());
                    view.setAlpha(attr.getAlpha());

                    float srcAlpha = view.getAlpha();
                    view.animate().alpha(srcAlpha)
                            .translationX(0)
                            .translationY(0)
                            .scaleX(1)
                            .scaleY(1)
                            .setDuration(duration)
                            .setInterpolator(interpolator)
                            .setListener(listener)
                            .start();
                    return true;
                }
            });
        }
    }

    public static void runExitAnim(Activity activity) {
        runExitAnim(activity, DEFAULT_TRANSITION_DURATION);
    }

    public static void runExitAnim(Activity activity, long duration) {
        runExitAnim(activity, duration, DEFAULT_TIME_INTERPOLATOR);
    }

    public static void runExitAnim(Activity activity, long duration, Animator.AnimatorListener listener) {
        runExitAnim(activity, duration, DEFAULT_TIME_INTERPOLATOR, listener);
    }

    public static void runExitAnim(Activity activity, long duration, TimeInterpolator interpolator) {
        runExitAnim(activity, duration, interpolator, null);
    }

    /**
     * 界面退出还原动画
     *
     * @param activity
     * @param duration
     * @param interpolator
     * @param listener
     */
    public static void runExitAnim(final Activity activity, final long duration,
                                   final TimeInterpolator interpolator, final Animator.AnimatorListener listener) {

        final ArrayList<ViewAttrs> attrs = activity.getIntent().getParcelableArrayListExtra(TRANSITION_MATERIALS);
        if (null == attrs || attrs.isEmpty()) {
            activity.finish();//v1.0.1版本添加，防止没有过度元素当前Activity关闭不了，开放系统自带过度动画
            return;
        }

        for (final ViewAttrs attr : attrs) {

            final View view = activity.findViewById(attr.getId());
            if (null == view) {
                continue;
            }

            final int[] location = new int[2];
            view.getLocationOnScreen(location);
            view.setPivotX(0);
            view.setPivotY(0);

            view.animate().alpha(attr.getAlpha())
                    .translationX(attr.getScreenX() - location[0])
                    .translationY(attr.getScreenY() - location[1])
                    .scaleX(attr.getWidth() * 1.00f / view.getWidth())
                    .scaleY(attr.getHeight() * 1.00f / view.getHeight())
                    .setDuration(duration)
                    .setInterpolator(interpolator)
                    .setListener(listener)
                    .start();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.finish();
                activity.overridePendingTransition(0, 0);//Disable system default transition animation
            }
        }, duration);
    }
}
