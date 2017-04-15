package com.hbck.fastworkorder.ui.iview;


import com.hbck.fastworkorder.bean.json.User;

import java.util.List;

/**
 * @author 丁建强
 * @time 2017-04-07 14:04
 * @类描述：通讯录
 * @变更记录:
 */

public interface IContactView {
    /**
     * 获取联系人
     *
     * @param users
     */
    void onContactResult(List<User> users);
}
