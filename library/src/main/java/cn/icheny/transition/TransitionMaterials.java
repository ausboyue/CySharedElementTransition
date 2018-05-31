package cn.icheny.transition;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;

/**
 * 转场动画所需原材料
 *
 * @author www.icheny.cn
 * @date 2018.05.31
 */
public class TransitionMaterials {
    private ArrayList<ViewAttrs> mViewAttrs;

    public ArrayList<ViewAttrs> getViewAttrs() {
        return mViewAttrs;
    }

    /**
     * 构建材料
     *
     * @param views
     */
    private TransitionMaterials(@NonNull View[] views) {
        mViewAttrs = new ArrayList<>();
        for (View view : views) {
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            mViewAttrs.add(new ViewAttrs(view.getId(), view.getAlpha(), location[0], location[1], view.getWidth(), view.getHeight()));
        }
    }

    public static TransitionMaterials createMaterials(@NonNull View... views) {
        return new TransitionMaterials(views);
    }
}
