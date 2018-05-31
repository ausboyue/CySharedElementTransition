package cn.icheny.transition;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用于转场动画所需的View属性
 *
 * @author www.icheny.cn
 * @date 2018.05.31
 */
public class ViewAttrs implements Parcelable {
    private int id; //View id
    private float alpha;//透明度值
    private int screenX;//View左上角在屏幕中的X坐标
    private int screenY;//View左上角在屏幕中的Y坐标
    private int width;//View可视宽度
    private int height;//View可视高度


    public ViewAttrs(int id, float alpha, int screenX, int screenY, int width, int height) {
        this.id = id;
        this.alpha = alpha;
        this.screenX = screenX;
        this.screenY = screenY;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeFloat(this.alpha);
        dest.writeInt(this.screenX);
        dest.writeInt(this.screenY);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    protected ViewAttrs(Parcel in) {
        this.id = in.readInt();
        this.alpha = in.readFloat();
        this.screenX = in.readInt();
        this.screenY = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Creator<ViewAttrs> CREATOR = new Creator<ViewAttrs>() {
        @Override
        public ViewAttrs createFromParcel(Parcel source) {
            return new ViewAttrs(source);
        }

        @Override
        public ViewAttrs[] newArray(int size) {
            return new ViewAttrs[size];
        }
    };
}
