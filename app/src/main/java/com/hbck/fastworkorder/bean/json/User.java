package com.hbck.fastworkorder.bean.json;

import android.text.TextUtils;

import com.hbck.fastworkorder.util.HanziToPinyin;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 用户信息
 * @author liu
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String EmpID;// 员工id
	private String EmpNumber;// 员工编号
	private String EmpName;// 员工姓名
	private String BelongDepartmentID;// 所属部门ID
	private String BelongServeStationSCID;// 所属市场组ID
	private String BelongServeStationZWID;// 所属装维组ID
	private String EmployeePhone;// 电话
	private String EmployeePhone2;// 电话2
	private String CityID;// 所属地市
	private String Code;// 手机识别码
	private String password;// 密码
	private String isPhone;// 是否是手机用户是否是手机端用户,默认值0,1-手机端可用),0不可用
	private String DID;// 部门ID
	private String PID;// 部门父编号
	private String DepName;// 部门名称
	private String DepPhone;// 部门电话
	private String CityCodeName;// 地市名称
	private String SortKey;// 字母
	
	
	public String getSortKey() {
		return SortKey;
	}
	public void setSortKey(String sortKey) {
		SortKey = sortKey;
	}
	public String getEmpID() {
		return EmpID;
	}
	public void setEmpID(String empID) {
		EmpID = empID;
	}
	public String getEmpNumber() {
		return EmpNumber;
	}
	public void setEmpNumber(String empNumber) {
		EmpNumber = empNumber;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public String getBelongDepartmentID() {
		return BelongDepartmentID;
	}
	public void setBelongDepartmentID(String belongDepartmentID) {
		BelongDepartmentID = belongDepartmentID;
	}
	public String getBelongServeStationSCID() {
		return BelongServeStationSCID;
	}
	public void setBelongServeStationSCID(String belongServeStationSCID) {
		BelongServeStationSCID = belongServeStationSCID;
	}
	public String getBelongServeStationZWID() {
		return BelongServeStationZWID;
	}
	public void setBelongServeStationZWID(String belongServeStationZWID) {
		BelongServeStationZWID = belongServeStationZWID;
	}
	public String getEmployeePhone() {
		return EmployeePhone;
	}
	public void setEmployeePhone(String employeePhone) {
		EmployeePhone = employeePhone;
	}
	public String getEmployeePhone2() {
		return EmployeePhone2;
	}
	public void setEmployeePhone2(String employeePhone2) {
		EmployeePhone2 = employeePhone2;
	}
	public String getCityID() {
		return CityID;
	}
	public void setCityID(String cityID) {
		CityID = cityID;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsPhone() {
		return isPhone;
	}
	public void setIsPhone(String isPhone) {
		this.isPhone = isPhone;
	}
	public String getDID() {
		return DID;
	}
	public void setDID(String dID) {
		DID = dID;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getDepName() {
		return DepName;
	}
	public void setDepName(String depName) {
		DepName = depName;
	}
	public String getDepPhone() {
		return DepPhone;
	}
	public void setDepPhone(String depPhone) {
		DepPhone = depPhone;
	}
	public String getCityCodeName() {
		return CityCodeName;
	}
	public void setCityCodeName(String cityCodeName) {
		CityCodeName = cityCodeName;
	}
	@Override
	public String toString() {
		return "User [EmpID=" + EmpID + ", EmpNumber=" + EmpNumber
				+ ", EmpName=" + EmpName + ", BelongDepartmentID="
				+ BelongDepartmentID + ", BelongServeStationSCID="
				+ BelongServeStationSCID + ", BelongServeStationZWID="
				+ BelongServeStationZWID + ", EmployeePhone=" + EmployeePhone
				+ ", EmployeePhone2=" + EmployeePhone2 + ", CityID=" + CityID
				+ ", Code=" + Code + ", password=" + password + ", isPhone="
				+ isPhone + ", DID=" + DID + ", PID=" + PID + ", DepName="
				+ DepName + ", DepPhone=" + DepPhone + ", CityCodeName="
				+ CityCodeName + ", SortKey=" + SortKey + "]";
	}

	/**
	 * initial letter for nickname
	 */
	protected String initialLetter;
	public String getInitialLetter() {
		if(initialLetter == null){
			setUserInitialLetter(this);
		}
		return initialLetter;
	}

	/**
	 * set initial letter of according user's nickname( username if no nickname)
	 *
	 * @param user
	 */
	public  void setUserInitialLetter(User user) {
		final String DefaultLetter = "#";
		String letter = DefaultLetter;

		final class GetInitialLetter {
			String getLetter(String name) {
				if (TextUtils.isEmpty(name)) {
					return DefaultLetter;
				}
				char char0 = name.toLowerCase().charAt(0);
				if (Character.isDigit(char0)) {
					return DefaultLetter;
				}
				ArrayList<HanziToPinyin.Token> l = HanziToPinyin.getInstance().get(name.substring(0, 1));
				if (l != null && l.size() > 0 && l.get(0).target.length() > 0)
				{
					HanziToPinyin.Token token = l.get(0);
					String letter = token.target.substring(0, 1).toUpperCase();
					char c = letter.charAt(0);
					if (c < 'A' || c > 'Z') {
						return DefaultLetter;
					}
					return letter;
				}
				return DefaultLetter;
			}
		}

		if ( !TextUtils.isEmpty(user.getEmpName()) ) {
			letter = new GetInitialLetter().getLetter(user.getEmpName());
			user.setInitialLetter(letter);
			return;
		}
		if (letter.equals(DefaultLetter) && !TextUtils.isEmpty(user.getEmpNumber())) {
			letter = new GetInitialLetter().getLetter(user.getEmpNumber());
		}
		user.setInitialLetter(letter);
	}

	public void setInitialLetter(String initialLetter) {
		this.initialLetter = initialLetter;
	}
}
