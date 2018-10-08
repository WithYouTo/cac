package com.whxx.core.factory;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whxx.core.dao.DictMapper;
import com.whxx.core.dao.TbManagerMapper;
import com.whxx.core.dao.TbMenuMapper;
import com.whxx.core.dao.TbRoleMapper;
import com.whxx.core.entity.TbManager;
import com.whxx.core.entity.TbMenu;
import com.whxx.core.entity.TbRole;
import com.whxx.core.model.Dict;
import com.whxx.core.utils.SpringContextHolder;
import com.whxx.core.utils.cache.Cache;
import com.whxx.core.utils.cache.CacheKey;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


/**
 * 常量的生产工厂
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:55:21
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {


    private TbRoleMapper roleMapper = SpringContextHolder.getBean(TbRoleMapper.class);
    private TbManagerMapper managerMapper = SpringContextHolder.getBean(TbManagerMapper.class);
    private TbMenuMapper menuMapper = SpringContextHolder.getBean(TbMenuMapper.class);
    private DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);


    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    /**
     *
     * 根据用户id获取用户名称
     *
     * @author huangxiang
     * @date 2017/12/26 16:06
     */
    @Override
    public Object getUserNameById(String userid) {
    	return "";
    }

    /**
     * 通过角色id获取角色名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(String roleId) {
        if (("0").equals(roleId)) {
            return "--";
        }
        TbRole roleObj = roleMapper.selectById(roleId);
        if (ObjectUtil.isNotNull(roleObj) && StrUtil.isNotEmpty(roleObj.getName()))
        {
            return roleObj.getName();
        }
        return "";
    }


    /**
     * 通过角色num获取角色名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleNameByRoleNum(Integer rolenum) {
        if (Objects.equals(0, rolenum))
        {
            return "--";
        }
        TbRole roleObj = roleMapper.selectOne(new QueryWrapper<TbRole>().lambda().eq(TbRole::getNum, rolenum));
        if (ObjectUtil.isNotNull(roleObj) && StrUtil.isNotEmpty(roleObj.getName()))
        {
            return roleObj.getName();
        }
        return "";
    }

    /**
     * 获取菜单的名称们(多个)
     */
    @Override
    public String getMenuNames(String menuIds) {

        StringBuilder sb = new StringBuilder();
        for (String menuid : menuIds.split(StrUtil.COMMA))
        {
            TbMenu menuObj = menuMapper.selectById(menuid);
            if (ObjectUtil.isNotNull(menuObj) && StrUtil.isNotEmpty(menuObj.getName()))
            {
                sb.append(menuObj.getName()).append(StrUtil.COMMA);
            }
        }
        return StrUtil.removeSuffix(sb.toString(), ",");
    }

    /**
     * 获取菜单的名称们(多个)
     */
    @Override
    public String getMenuNamesByNum(String nums) {

        StringBuilder sb = new StringBuilder();
        for (String num : nums.split(StrUtil.COMMA))
        {
            TbMenu menuObj = menuMapper.selectOne(new QueryWrapper<TbMenu>().lambda().eq(TbMenu::getCode, num));
            if (ObjectUtil.isNotNull(menuObj) && StrUtil.isNotEmpty(menuObj.getName()))
            {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(sb.toString(), StrUtil.COMMA);
    }

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    @Override
    public String getUserAccountById(String userId) {
        TbManager manager = managerMapper.selectById(userId);
        if (manager != null) {
            return manager.getAccount();
        } else {
            return "--";
        }
    }

    /**
     * 通过角色ids获取角色名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
    public String getRoleName(String roleIds) {
        StringBuilder sb = new StringBuilder();
        for (String role : roleIds.split(StrUtil.COMMA))
        {
            TbRole roleObj = roleMapper.selectById(role);
            if (ObjectUtil.isNotNull(roleObj) && StrUtil.isNotEmpty(roleObj.getName()))
            {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(sb.toString(), ",");
    }

    /**
     * 获取菜单名称
     */
    @Override
    public String getMenuName(String menuId) {
        if (StrUtil.isEmpty(menuId))
        {
            return "";
        } else {
            TbMenu menu = menuMapper.selectById(menuId);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    @Override
    public List<Dict> findInDict(String id) {
        if (StrUtil.isEmpty(id))
        {
            return null;
        } else {
            List<Dict> dicts = dictMapper.selectListByPid(id);
            if (dicts == null || dicts.size() == 0) {
                return null;
            } else {
                return dicts;
            }
        }
    }

    @Override
    public Object getDictName(String dictId) {
        if (StrUtil.isEmpty(dictId))
        {
            return "";
        } else {
            Dict dict = dictMapper.selectById(dictId);
            if (dict == null) {
                return "";
            } else {
                return dict.getName();
            }
        }
    }
}
