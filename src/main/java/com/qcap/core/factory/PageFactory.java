package com.qcap.core.factory;


import com.github.pagehelper.PageHelper;
import com.qcap.core.kit.HttpKit;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @Description: BootStrap Table默认的分页参数创建
 *
 * @ClassName: PageFactory
 *
 * @author huangxiang
 * @date 2017/12/26 14:43
 */
public class PageFactory<T> {

    public void defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));     //每页多少条数据
        int offset = Integer.valueOf(request.getParameter("page"));   //每页的偏移量(本页当前有多少条)

        PageHelper.startPage(offset, limit);
    }

}
