package com.qcap.core.service.impl;

import static com.qcap.core.common.CoreConstant.ORG_FULLCODES_SEPARATE;
import static com.qcap.core.common.CoreConstant.ORG_FULLNAMES_SEPARATE;
import static com.qcap.core.utils.AppUtils.buildZTreeNodeByRecursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.dao.TbManagerOrgMapper;
import com.qcap.core.dao.TbOrgMapper;
import com.qcap.core.entity.TbOrg;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.service.ITbOrgService;

import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
@Service
public class TbOrgServiceImpl implements ITbOrgService {
	@Resource
	private TbOrgMapper tbOrgMapper;
	@Resource
	private TbManagerOrgMapper tbManagerOrgMapper;

	@Override
	public void getOrgList(IPage<Map<String,String>> page, TbOrg org) {
//		QueryWrapper<TbOrg> wrapper = new QueryWrapper<>();
//		wrapper.eq("status", "1");
//		if (StringUtils.isNotEmpty(org.getName())) {
//			wrapper.lambda().like(TbOrg::getName, org.getName() + "%");
//		}
//		if (StringUtils.isNotEmpty(org.getCode())) {
//			wrapper.lambda().eq(TbOrg::getCode, org.getCode());
//		}
		List<Map<String,String>> orgList = tbOrgMapper.selectOrgByOrgCode(page, org);
		page.setRecords(orgList);
	}

	@Override
	public List<ZTreeNode> getOrgTreeList() {
		List<ZTreeNode> orgTreeList = tbOrgMapper.getOrgTreeList();
		ZTreeNode parent = ZTreeNode.createParent();
		parent.setId("1");
		orgTreeList.add(parent);
		return orgTreeList;
	}

	@Override
	public List<ZTreeNode> getOrgTreeListByManagerId(String managerId) {
		List<ZTreeNode> target = new ArrayList<>();
		List<ZTreeNode> list = tbOrgMapper.getOrgTreeListByManagerId(managerId);
		return buildZTreeNodeByRecursive(list, target, e -> Objects.equals("1", e.getPid()));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertItem(TbOrg org) {
		int max = tbOrgMapper.selectMaxNum();
		int maxNum = max == 0 ? 1000 : max + 1;
		org.setNum(String.valueOf(maxNum));
		org.setStatus("1");
		buildTbOrgByParentCode(org, org.getParentCode());
		org.setId(UUIDUtils.getUUID());
		tbOrgMapper.insert(org);
	}

	@Override
	public void updateItem(TbOrg org) throws Exception {
		// 通过Id获取修改前的组织信息
		TbOrg oldOrg = tbOrgMapper.selectById(org.getId());
		final String oldName = oldOrg.getName();
		oldOrg.setProgramCode(org.getProgramCode());

		// 判断是否修改父级组织
		if (Objects.equals(oldOrg.getParentCode(), org.getParentCode())) {
			oldOrg.setName(org.getName());
			oldOrg.setFullnames(StrUtil.replace(oldOrg.getFullnames(),
					StringUtils.lastIndexOf(oldOrg.getFullnames(), oldName), "oldName", org.getName(), false));
			tbOrgMapper.updateById(oldOrg);
		}
		// 若修改了父节点
		else {
			if (StringUtils.isEmpty(org.getParentCode())) {
				throw new Exception(CoreConstant.ORG_IS_ROOT_MSG);
			}
			// 修改前的父级组织
			TbOrg frontOrg = tbOrgMapper.selectOne(new QueryWrapper<TbOrg>().eq("code", oldOrg.getParentCode()));
			// 修改后的父级组织
			TbOrg behindOrg = tbOrgMapper.selectOne(new QueryWrapper<TbOrg>().eq("code", org.getParentCode()));
			if (behindOrg != null) {
				if (behindOrg.getFullcodes().contains(oldOrg.getCode())) {
					throw new Exception(CoreConstant.ORG_PAR_TO_CHIL_ERR);
				}
				String oldCode = oldOrg.getCode();
				buildCodeAndSeqByParentCode(oldOrg, behindOrg.getCode());
				oldOrg.setParentCode(behindOrg.getCode());
				oldOrg.setParentNum(behindOrg.getNum());
				oldOrg.setFullcodes(behindOrg.getFullcodes() + oldOrg.getCode() + ORG_FULLCODES_SEPARATE);
				oldOrg.setFullnames(behindOrg.getFullnames() + org.getName() + ORG_FULLCODES_SEPARATE);
				// 查询出所有的关联的菜单
				List<TbOrg> children = tbOrgMapper
						.selectList(new QueryWrapper<TbOrg>().lambda().like(TbOrg::getFullcodes, "%" + oldCode + "%"));
				if (!children.isEmpty()) {
					updateMenuRecursive(children, oldCode, oldOrg);
				}
				tbOrgMapper.updateById(oldOrg);
			}
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteItemById(String OrgId) throws Exception {
		TbOrg org = tbOrgMapper.selectById(OrgId);
		int count = tbOrgMapper.selectCount(new QueryWrapper<TbOrg>().lambda().eq(TbOrg::getParentCode, org.getCode()));
		if (count > 0) {
			throw new Exception(org.getFullnames() + ":" + CoreConstant.ORG_HAS_CHILD_MSG);
		} else {
			// 判断组织是否已分配人员
			List<String> list = tbManagerOrgMapper.getManagerIdByOrgId(OrgId);
			if (list.isEmpty()) {
				tbOrgMapper.deleteById(OrgId);
			} else {
				throw new Exception(org.getFullnames() + ":" + CoreConstant.ORG_HAS_PERSON_MSG);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String upSeq(String orgId) {
		TbOrg org = tbOrgMapper.selectById(orgId);
		TbOrg fontOrg = tbOrgMapper.selectOne(new QueryWrapper<TbOrg>().lambda().lt(TbOrg::getSeq, org.getSeq())
				.eq(TbOrg::getParentCode, org.getParentCode()).last("order by seq desc limit 1"));
		if (fontOrg == null) {
			return "已经是第一个节点了！";
		} else {
			exchangeSeq(org, fontOrg);
			tbOrgMapper.updateById(org);
			tbOrgMapper.updateById(fontOrg);
			return "上移成功！";
		}

	}

	@Override
	public String downSeq(String orgId) {
		TbOrg org = tbOrgMapper.selectById(orgId);
		TbOrg behindOrg = tbOrgMapper.selectOne(new QueryWrapper<TbOrg>().lambda().gt(TbOrg::getSeq, org.getSeq())
				.eq(TbOrg::getParentCode, org.getParentCode()).last("order by seq asc limit 1"));
		if (behindOrg == null) {

			return "已经是最后一个节点了！";
		} else {
			exchangeSeq(org, behindOrg);
			tbOrgMapper.updateById(org);
			tbOrgMapper.updateById(behindOrg);
			return "下移移成功！";
		}
	}

	private static void exchangeSeq(TbOrg org1, TbOrg org2) {
		int one = org1.getSeq();
		int two = org2.getSeq();
		// 交换one，two的值
		one ^= two;
		two ^= one;
		one ^= two;
		org1.setSeq(one);
		org2.setSeq(two);
	}

	/**
	 * 根据新的父节点设置org的属性
	 *
	 * @param org
	 *            组织对象
	 * @param parentCode
	 *            父编码
	 */
	private void buildTbOrgByParentCode(TbOrg org, String parentCode) {
		if (StringUtils.isEmpty(org.getParentCode()) || "1".equals(parentCode)) {
			org.setParentCode("1");
			org.setParentNum("1");
			org.setSeq(1);
			buildCodeAndSeqByParentCode(org, "1");
			org.setFullcodes("1" + ORG_FULLCODES_SEPARATE + org.getCode() + ORG_FULLCODES_SEPARATE);
			org.setFullnames("" + org.getName() + ORG_FULLNAMES_SEPARATE);
		} else {
			TbOrg parent = tbOrgMapper.selectOne(new QueryWrapper<TbOrg>().lambda().eq(TbOrg::getCode, parentCode));
			buildCodeAndSeqByParentCode(org, parentCode);
			org.setParentCode(parent.getCode());
			org.setParentNum(parent.getNum());
			if (org.getCode().equals(org.getParentCode())) {
				throw new BussinessException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
			}
			org.setFullcodes(parent.getFullcodes() + org.getCode() + ORG_FULLCODES_SEPARATE);
			org.setFullnames(parent.getFullnames() + org.getName() + ORG_FULLNAMES_SEPARATE);
		}
	}

	/**
	 * 根据parentCode设置代码和序号
	 *
	 * @param org
	 *            对象
	 * @param parentCode
	 *            父节点代码
	 */
	private synchronized void buildCodeAndSeqByParentCode(TbOrg org, String parentCode) {
		String maxCode = tbOrgMapper.getMaxCodeByParentCode(parentCode);
		if (StringUtils.isEmpty(maxCode)) {
			org.setCode(parentCode + "1000");
			org.setSeq(0);
		} else {
			int seq = tbOrgMapper.getMaxSeqByParentCode(parentCode);
			String partOne = maxCode.substring(0, maxCode.length() - 4);
			String partTwo = maxCode.substring(maxCode.length() - 4);
			int num = NumberUtils.toInt(partTwo) + 1;
			org.setCode(partOne + num);
			org.setSeq(seq);
		}
	}

	private void updateMenuRecursive(List<TbOrg> source, String oldParentCode, TbOrg newParent) {
		source.stream().filter(e -> Objects.equals(oldParentCode, e.getParentCode())).forEach(e -> {
			String parentCode = e.getCode();
			String code = newParent.getCode() + e.getCode().substring(e.getCode().length() - 4);
			e.setParentCode(newParent.getCode());
			e.setCode(code);
			e.setFullcodes(newParent.getFullcodes() + code + ORG_FULLCODES_SEPARATE);
			e.setFullnames(newParent.getFullnames() + e.getName() + ORG_FULLNAMES_SEPARATE);
			updateMenuRecursive(source, parentCode, e);
			tbOrgMapper.updateById(e);
		});
	}

}
