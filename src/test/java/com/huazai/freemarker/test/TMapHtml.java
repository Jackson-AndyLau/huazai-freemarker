package com.huazai.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.huazai.freemarker.dto.EmployeeDTO;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 通过模板技术实现静态网页的输出<访问map集合中的数据>
 * 				<li>-- 访问map集合中的数据<br>
 * 				<li><#list map?keys as key>
 * 				<li>	${map[key].empId}
 * 				<li>	${map[key].empName}<br>
 * 				<li></#list>
 *              </ul>
 * @className TMapHtml
 * @package com.huazai.freemarker.test
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class TMapHtml
{

	@Test
	public void GenHtml() throws Exception
	{
		// 1、创建个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		String diretoryp_ath = "D:\\fremarker";
		// 2、设置模板文件所在的路径的目录
		configuration.setDirectoryForTemplateLoading(new File(diretoryp_ath));
		// 3、设置模板文件的字符集
		configuration.setDefaultEncoding("UTF-8");
		// 4、首先创建模板文件，再加载模板文件 模板文件的后缀官方统一的标准是.ftl 其实任何类型都行。
		Template template = configuration.getTemplate("template.ftl");// 可以是<相对路径>，也可以是<绝对路径>
		// 5、创建模板文件需要展示数据的数据集对象，可以使用POJO，也可以使用map 一般是使用map
		Map<String, Object> model = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		map.put("m1", new EmployeeDTO("1001", "冰心"));
		map.put("m2", new EmployeeDTO("1002", "季羡林"));
		map.put("m3", new EmployeeDTO("1003", "汪曾祺"));
		model.put("map", map);
		String pre_file_path = "D:\\fremarker";
		// 6、创建一个FileWriter对象 指定生成的静态文件的文件路径及文件名
		// 拼接一个前缀和后缀
		FileWriter writer = new FileWriter(new File(pre_file_path + "/result.html"));
		// 7、调用模板对象的process方法，执行输出文件。
		template.process(model, writer);
		// 8、关闭流
		writer.close();
	}
}
