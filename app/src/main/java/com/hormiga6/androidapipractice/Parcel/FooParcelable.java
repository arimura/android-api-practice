package com.hormiga6.androidapipractice.Parcel;

import android.os.Parcel;
import android.os.Parcelable;

public class FooParcelable implements Parcelable {
    private long mId;
    private int mCount;
    private String mName;
    private boolean mFlag;

    public FooParcelable(long id, int count, String name, boolean flag) {
        this.mId = id;
        this.mCount = count;
        this.mName = name;
        this.mFlag = flag;
    }

    protected FooParcelable(Parcel in) {
        this.mId = in.readLong();
        this.mCount = in.readInt();
        this.mName = in.readString();
        this.mFlag = in.readInt() == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(mCount);
        dest.writeString(mName);
        dest.writeInt(mFlag ? 1 : 0);
    }

    public static final Creator<FooParcelable> CREATOR = new Creator<FooParcelable>() {
        @Override
        public FooParcelable createFromParcel(Parcel in) {
            return new FooParcelable(in);
        }

        @Override
        public FooParcelable[] newArray(int size) {
            return new FooParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return mId + " " + mCount + " " + mName + " " + mFlag;
    }
}
