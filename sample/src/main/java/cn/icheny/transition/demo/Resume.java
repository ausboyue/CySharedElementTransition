package cn.icheny.transition.demo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 简历Bean类
 *
 * @author www.icheny.cn
 * @date 2018.05.31
 */
public class Resume implements Parcelable {
    private int userId;//用户id
    private int headId;//头像资源id
    private String name;//用户姓名
    private int age;//年龄
    private int workYears;//工作时间
    private String desc;//简介
    private String experience;//履历

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }


    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public int getWorkYears() {
        return workYears;
    }

    public void setWorkYears(int workYears) {
        this.workYears = workYears;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeInt(this.headId);
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeInt(this.workYears);
        dest.writeString(this.desc);
        dest.writeString(this.experience);
    }

    public Resume() {
    }

    protected Resume(Parcel in) {
        this.userId = in.readInt();
        this.headId = in.readInt();
        this.name = in.readString();
        this.age = in.readInt();
        this.workYears = in.readInt();
        this.desc = in.readString();
        this.experience = in.readString();
    }

    public static final Creator<Resume> CREATOR = new Creator<Resume>() {
        @Override
        public Resume createFromParcel(Parcel source) {
            return new Resume(source);
        }

        @Override
        public Resume[] newArray(int size) {
            return new Resume[size];
        }
    };
}