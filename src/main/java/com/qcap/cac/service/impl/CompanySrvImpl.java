package com.qcap.cac.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qcap.cac.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.dao.CompanyMapper;
import com.qcap.cac.model.Department;
import com.qcap.cac.service.CompanySrv;

@Service
@Transactional
public class CompanySrvImpl implements CompanySrv {

	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public Map selectDetailByPk(String tbbb08Id){
		// TODO Auto-generated method stub
		return this.companyMapper.selectDetailByPk(tbbb08Id);
	}

	@Override
	public String selectExistNum(Map map){
		// TODO Auto-generated method stub
		return this.companyMapper.selectExistNum(map);
	}

	@Override
	public String selectCodeNum(Map map){
		// TODO Auto-generated method stub
		return this.companyMapper.selectCodeNum(map);
	}

	@Override
	public String selectNewCompCode(String newId){
		// TODO Auto-generated method stub
		return this.companyMapper.selectNewCompCode(newId);
	}

	@Override
	public String selectCompNum(String layer){
		// TODO Auto-generated method stub
		return this.companyMapper.selectCompNum(layer);
	}

	@Override
	public void insertNewComp(Company tbbb08){
		Department tbbb04 = new Department();
		tbbb04.setDepartmentId(tbbb08.getCompanyCode());
		tbbb04.setDeptName(tbbb08.getFullName());
		tbbb04.setLayer(0);
		tbbb04.setParentDeptId("0");
		tbbb04.setDeptCode(tbbb08.getCompanyCode());
		tbbb04.setDeleteFlag("0");
		tbbb04.setCompanyCode(tbbb08.getCompanyCode());
		tbbb04.setCreateEmp(tbbb08.getCreateEmp());
		tbbb04.setCreateDate(tbbb08.getCreateDate());
		tbbb04.setUpdateDate(tbbb08.getCreateDate());
		tbbb04.setVersion(0);
		this.companyMapper.insert(tbbb04);
		this.companyMapper.insertNewComp(tbbb08);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updateCompanyInfo(Company tbbb08){
		this.companyMapper.updateCompanyInfo(tbbb08);
		
		String companyCode = this.companyMapper.selectCompCodeByBb08Id(tbbb08.getCompanyId());
		String name = tbbb08.getFullName();
		
		Map param = new HashMap<>();
		param.put("deptName", name);
		param.put("companyCode", companyCode);
		param.put("layer", "0");
		this.companyMapper.updateCompanyName(param);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String deleteInfoByPk(String[] code){
		for (int i = 0; i < code.length; i++) {
			//查询tb_sys_department表的deptCode是否被使用
			Map param12 = new HashMap<>();
			param12.put("parentTbbb04Id", code[i]);
			String n = this.companyMapper.selectCodeNum(param12);		
			if(!"0".equals(n)){
				//该公司已经被使用，无法删除
				return "-2";
			}
			this.companyMapper.deleteByPrimaryKey(code[i]);
			this.companyMapper.deleteInfoByPk(code[i]);
		}
		return "1";
	}

	@Override
	public List<Map> selectSubCompanies(Map param){
		// TODO Auto-generated method stub
		return this.companyMapper.selectSubCompanies(param);
	}

	/** (non Javadoc) 
	 * @Title: selectCompaniesByPage
	 * @Description: TODO
	 * @param param
	 * @return
	 * @see com.whxx.esp.service.CompanySrv#selectCompaniesByPage(java.util.Map)
	 */
	@Override
	public List<Map<String,Object>> getCompaniesByPage(Map param) {
		// TODO Auto-generated method stub
		return this.companyMapper.getCompaniesByPage(param);
	}

	@Override
	public String selectNewCompanyCode() {
		// TODO Auto-generated method stub
		return companyMapper.selectNewCompanyCode();
	}

}
