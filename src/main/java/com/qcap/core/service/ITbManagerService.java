package com.qcap.core.service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.entity.UserInsertDto;
import com.qcap.core.entity.TbManager;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author PH
 * @since 2018-07-29
 */
public interface ITbManagerService {
	String login(String account, String password) throws Exception;

	Optional<TbManager> getTbManagerFromToken(String token);

	void getUserList(IPage<Map<String, Object>> page, Map<String, Object> params);

	/**
	 * 添加用户
	 *
     * @param manager
     *            参数对象
     */
	void insertItem(UserInsertDto manager) throws Exception;

	/**
	 * 添加用户
	 *
     * @param manager
     *            参数对象
     */
	void updateItem(UserInsertDto manager);

	/**
	 * 根据Id逻辑删除用户
	 *
	 * @param id
	 *            主键Id
	 */
	void deleteManagerById(String id);

	/**
	 * 为用户分配角色
	 *
	 * @param managerId
	 *            用户表主键Id
	 * @param roleNumbers
	 *            角色表num集合
	 */
	void buildRoleForManager(String managerId, Collection<String> roleNumbers);

	/**
	 * 为用户分配组织
	 *
	 * @param managerId
	 *            用户表主键id
	 * @param orgId
	 *            组织代码
	 */
	void buildOrfForManager(String managerId, String orgId);

	/**
	 *
	 * @Description: 修改密码
	 *
	 *
	 * @MethodName: resetPassword
	 * @Parameters: [resetPasswordDto] 
	 * @ReturnType: void
	 *
	 * @author huangxiang
	 * @date 2018/10/23 9:25
	 */
	void changePassword(TbManager mgr, String newPwd, String oldPwd);

	/**
	 *
	 * @Description: 重置用户密码
	 *
	 *
	 * @MethodName: resetPassword
	 * @Parameters: [userId]
	 * @ReturnType: void
	 *
	 * @author huangxiang
	 * @date 2018/11/6 9:54
	 */
    void resetPassword(String userId);

    Map<String,Object> getLoginUserInfo();
}
