package com.hbck.fastworkorder.presenter.impl;

import com.hbck.fastworkorder.bean.json.VersionBean;

/**
 * Created by Administrator on 2017-04-01.
 */

public interface IVersionPresenter {
    /**
     * 检查版本
     *
     * @param currVersion
     */
    void checkVersion(String currVersion);

    /**
     * 下载apk
     *
     * @param versionBean
     */
    void downLoadApk(VersionBean versionBean);

}
