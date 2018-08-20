package cn.icheny.transition;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * View's attributes required for transition animation
 *
 * @author www.icheny.cn
 * @date 2018.05.31
 */
public class ViewAttrs implements Parcelable {
    private int id; //View's id
    private float alpha;//View's alpha value
    private int screenX;//View's X coordinate on screen
    private int screenY;//View's Y coordinate on screen
    private int width;//View's visible width
    private int height;//View's visible height


    public ViewAttrs(int id, float alpha, int screenX, int screenY, int width, int height) {
        this.id = id;
        this.alpha = alpha;
        this.screenX = screenX;
        this.screenY = screenY;
        this.width = width;
        this.height = height;
    }

    /****************** Getter And Setter  Methods ******************/


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
