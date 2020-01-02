package com.huazai.freemarker.dto;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 员工实体
 *              </ul>
 * @className Employee
 * @package com.huazai.freemarker.pojo
 * @createdTime 2017年06月18日
 *
 * @version V1.0.0
 */
public class EmployeeDTO
{

	/**
	 * 员工ID
	 */
	private String empId;

	/**
	 * 员工姓名
	 */
	private String empName;

	public String getEmpId()
	{
		return empId;
	}

	public void setEmpId(String empId)
	{
		this.empId = empId;
	}

	public String getEmpName()
	{
		return empName;
	}

	public void setEmpName(String empName)
	{
		this.empName = empName;
	}

	@Override
	public String toString()
	{
		return "Employee [empId=" + empId + ", empName=" + empName + "]";
	}

	public EmployeeDTO()
	{
		super();
	}

	public EmployeeDTO(String empId, String empName)
	{
		super();
		this.empId = empId;
		this.empName = empName;
	}

}
