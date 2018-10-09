package com.qcap.core.warpper;

import java.util.List;
import java.util.Map;

import com.qcap.core.factory.ConstantFactory;
import com.qcap.core.model.Dict;

import cn.hutool.core.util.StrUtil;

/**
 * 字典列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class DictWarpper extends BaseControllerWarpper {

	public DictWarpper(Object list) {
		super(list);
	}

	@Override
	public void warpTheMap(Map<String, Object> map) {
		StringBuffer detail = new StringBuffer();
		String id = map.get("id").toString();
		List<Dict> dicts = ConstantFactory.me().findInDict(id);
		if (dicts != null) {
			for (Dict dict : dicts) {
				detail.append(dict.getNum() + ":" + dict.getName() + ",");
			}
			map.put("detail", StrUtil.removeSuffix(detail.toString(), ","));
		}
	}

}
