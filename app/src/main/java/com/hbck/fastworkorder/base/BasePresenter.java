package com.hbck.fastworkorder.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017-04-02.
 */

public abstract class BasePresenter<V> {
    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
