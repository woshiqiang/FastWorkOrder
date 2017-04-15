package com.hbck.fastworkorder.ui.iview;

import com.hbck.fastworkorder.bean.json.VersionBean;

import java.io.File;

/**
 * Created by Administrator on 2017-03-31.
 */

public interface IVersionView {
    /**
     * 更新
     */
    void onUpdate(VersionBean versionBean);

    /**
     * 不需要更新
     */
    void onNoUpdate();

    /**
     * 下载apk出错
     *
     * @param errerMsg
     */
    void onDownLoadError(String errerMsg);

    /**
     * 下载成功
     *
     * @param response
     */
    void onDownLoadSuccess(File response);

    /**
     * 下载进度
     *
     * @param progress
     * @param total
     */
    void onDownLoadProgress(float progress, long total);


}
