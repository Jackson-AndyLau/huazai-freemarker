> # Java模板引擎Freemarker2.x进阶详情

## 一、关于静态化页面
&#8195;&#8195;什么是网页静态化：通过一些技术手段 Freemarker 或者 Thymeleaf 将动态页面JSP、asp.net、php 等转换成静态的页面，通过浏览器直接访问现成的静态页面。


## 二、网页静态化特点
1、通过浏览器直接访问静态的页面,不需要经过程序处理，它的访问速度高；  
2、稳定性好；  
3、更有效的防止安全漏洞问题，比如不易遭受黑客攻击；  
4、静态的页面更容易被搜索引擎收录；  


## 三、关于Freemarker

&#8195;&#8195;FreeMarker 是一个用Java语言编写的模板引擎，它基于模板输出文本。**FreeMarker 与 Web 容器无关**，即在Web运行时，它并不知道 Servlet 或 HTTP。它不仅可以用作表现层的实现技术，而且还可以用于生成XML，JSP或Java 等。

&#8195;&#8195;**FreeMarker Template Language (FTL)** 。 在模板中，你可以专注于如何展现数据， 而在模板之外可以专注于要展示什么数据。 这种方式通常被称为 MVC (模型 视图 控制器) 模式，对于动态网页来说，是一种特别流行的模式。

&#8195;&#8195;FreeMarker 的设计实际上是被用来生成 HTML 网页，尤其是通过基于实现了 MVC(Model View Controller，模型-视图-控制器)模式的 Servlet 应用程序。使用 MVC 模式的动态网页的构思使得你可以将前端设计者(编写 HTML)从程序员中分离出来。所有人各司其职，发挥其擅长的一面。网页设计师可以改写页面的显示效果而不受程序员编译代码的影响，因为应用程序的逻辑(Java 程序)和页面设计(FreeMarker 模板)已经分开了。页面模板代码不会受到复杂的程序代码影响。这种分离的思想即便对一个程序员和页面设计师是同一个人的项目来说都是非常有用的，因为分离使得代码保持简洁而且便于维护。

&#8195;&#8195;尽管 FreeMarker 也有编程能力，但它也不是像 PHP 那样的一种全面的编程语言。反而，Java 程序准备的数据来显示(比如 SQL 查询)，FreeMarker 仅仅使用模板生成文本页面来呈现已经准备好的数据。

![Freemarker数据组合图](https://github.com/Jackson-AndyLau/pictures-storage/blob/master/002/202002/20200102171848.png 'Freemarker数据组合图')

目前企业中主要用Freemarker做静态页面或是页面展示，所以在本篇博客，博主主要使用Freemarker来做静态化示例。


## 四、Freemarker 的使用步骤
```
1）、创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号;
2）、设置模板文件所在的路径;
3）、设置模板文件使用的字符集。一般就是UTF-8；
4）、加载一个模板，创建一个模板对象；
5）、创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map；
6）、创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名；
7）、调用模板对象的process方法输出文件；
8）、关闭流；
```


代码示例如下：

```java
package com.huazai.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 通过模板技术实现静态网页的输出
 *              </ul>
 * @className TGenerateHtml
 * @package com.huazai.freemarker.test
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class TGenerateHtml
{

	@Test
	public void GenHtml() throws Exception
	{
		// 1、创建个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		String diretoryp_ath = "模板文件所在的路径的目录";
		// 2、设置模板文件所在的路径的目录
		configuration.setDirectoryForTemplateLoading(new File(diretoryp_ath));
		// 3、设置模板文件的字符集
		configuration.setDefaultEncoding("UTF-8");
		// 4、首先创建模板文件，再加载模板文件 模板文件的后缀官方统一的标准是.ftl 其实任何类型都行。
		Template template = configuration.getTemplate("template.htm");// 可以是<相对路径>，也可以是<绝对路径>
		// 5、创建模板文件需要展示数据的数据集对象，可以使用POJO，也可以使用map 一般是使用map
		Map<String, String> model = new HashMap<>();
		model.put("hello", "hello");
		String pre_file_path = "生成的静态文件的文件路径";
		// 6、创建一个FileWriter对象 指定生成的静态文件的文件路径及文件名
		// 拼接一个前缀和后缀
		FileWriter writer = new FileWriter(new File(pre_file_path + "/result.html"));
		// 7、调用模板对象的process方法，执行输出文件。
		template.process(model, writer);
		// 8、关闭流
		writer.close();
	}
}
```


## 五、Freemarker 的使用

1）、新建Maven工程

![Freemarker Maven工程](https://github.com/Jackson-AndyLau/pictures-storage/blob/master/002/202002/20200103110906.png 'Freemarker Maven工程')

2）、引入 Maven 依赖，在项目的pom.xml配置文件中添加 freemarker 依赖包
```xml
			<dependency>
				<groupId>org.freemarker</groupId>
				<artifactId>freemarker</artifactId>
				<version>2.3.28</version>
			</dependency>
```
[点击并拖拽以移动]

3）、新建一个数据载体 EmployeeDTO

```java
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
```


## 六、Freemarker 常用语法

1）、通过模板技术实现静态网页的输出<访问Map中的key属性>

```java
package com.huazai.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 通过模板技术实现静态网页的输出<访问Map中的key属性>
 *              <li>--访问Map中的key属性<br>
 *              <li>${hello}
 *              </ul>
 * @className TGenerateHtml
 * @package com.huazai.freemarker.test
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class TKeyHtml
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
		Map<String, String> model = new HashMap<>();
		model.put("hello", "hello world!");
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
```


模板内容：
```html
--访问Map中的key属性<br>
${hello}
```

效果图如下：
![Freemarker Maven工程](https://github.com/Jackson-AndyLau/pictures-storage/blob/master/002/202002/20200205154549.png 'Freemarker Maven工程')


2）、通过模板技术实现静态网页的输出<访问POJO中的属性>

```java
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
 * @description 通过模板技术实现静态网页的输出<访问POJO中的属性>
 * 				<li>-- 访问POJO中的属性<br>
 * 				<li>${employee.empId}
 * 				<li>${employee.empName}
 *              </ul>
 * @className TPojoHtml
 * @package com.huazai.freemarker.test
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class TPojoHtml
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
		model.put("employee", new EmployeeDTO("001", "huazai"));
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
```


模板内容：
```html
-- 访问POJO中的属性<br>
${employee.empId}
${employee.empName}
```

效果图如下：
![Freemarker Maven工程](https://github.com/Jackson-AndyLau/pictures-storage/blob/master/002/202002/20200205155212.png 'Freemarker Maven工程')

3）、通过模板技术实现静态网页的输出<访问集合中的数据>
```java
package com.huazai.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * @description 通过模板技术实现静态网页的输出<访问集合中的数据>
 * 				<li>-- 访问集合中的数据<br>
 * 				<li><#list list as item>
 * 				<li>	${item_index}
 * 				<li>	${item.empId}
 * 				<li>	${item.empName}<br>
 * 				<li></#list>
 * 				<li>
 * 				<li>-- 逻辑判断语句<br>
 * 				<li><#list list as item>
 * 				<li>	<#if item_index%2==0>
 * 				<li>		这是偶数行》
 * 				<li>	<#else>
 * 				<li>		这是奇数行》
 * 				<li>	</#if>
 * 				<li>	${item_index}
 * 				<li>	${item.empId}
 * 				<li>	${item.empName}<br>
 * 				<li></#list>
 *              </ul>
 * @className TArrayHtml
 * @package com.huazai.freemarker.test
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class TArrayHtml
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
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		employeeDTOs.add(new EmployeeDTO("1001", "冰心"));
		employeeDTOs.add(new EmployeeDTO("1002", "季羡林"));
		employeeDTOs.add(new EmployeeDTO("1003", "汪曾祺"));
		model.put("list", employeeDTOs);
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
```

模板内容：
```html
-- 访问集合中的数据<br>
<#list list as item>
	${item_index}
	${item.empId}
	${item.empName}<br>
</#list>
```

效果图如下：
![Freemarker Maven工程](https://github.com/Jackson-AndyLau/pictures-storage/blob/master/002/202002/20200205161748.png 'Freemarker Maven工程')

## 4）、通过模板技术实现静态网页的输出<访问map集合中的数据>
```java
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
```

模板内容：
```html
-- 访问map集合中的数据<br>
<#list map?keys as key>
	${map[key].empId}
	${map[key].empName}<br>
</#list>
```

效果图如下：
![Freemarker Maven工程](https://github.com/Jackson-AndyLau/pictures-storage/blob/master/002/202002/20200205215224.png 'Freemarker Maven工程')



5）、通过模板技术实现静态网页的输出<日期类型格式化>

```java
package com.huazai.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 通过模板技术实现静态网页的输出<日期类型格式化>
 * 				<li>-- 日期类型格式化<br>
 * 				<li>当前日期：${date?date}<br>
 * 				<li>当前时间：${date?time}<br>
 * 				<li>当前日期和时间：${date?datetime}<br>
 * 				<li>自定义日期格式：${date?string("yyyy年MM月dd日 HH时mm分ss秒")}<br>
 *              </ul>
 * @className TDateHtml
 * @package com.huazai.freemarker.test
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class TDateHtml
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
		model.put("date", new Date());
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
```	


模板内容：
```html
-- 日期类型格式化<br>
当前日期：${date?date}<br>
当前时间：${date?time}<br>
当前日期和时间：${date?datetime}<br>
自定义日期格式：${date?string("yyyy年MM月dd日 HH时mm分ss秒")}<br>
```

效果图如下：
![Freemarker Maven工程](https://github.com/Jackson-AndyLau/pictures-storage/blob/master/002/202002/20200205162938.png 'Freemarker Maven工程')

6）、通过模板技术实现静态网页的输出<非空判断 + 框架标签>
```java
package com.huazai.freemarker.test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 通过模板技术实现静态网页的输出<非空判断 + 框架标签>
 * 				<li>-- 非空判断 + 框架标签<br>
 * 				<li>空值处理：${list!"这个是空值"}<br>
 * 				<li>引用页面01：<#include "page_01.htm"><br>
 * 				<li>引用页面02：<#include "page_02.htm"><br>
 * 				<li>引用页面03：<#include "page_03.htm"><br>
 *              </ul>
 * @className TRestHtml
 * @package com.huazai.freemarker.test
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class TRestHtml
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
		model.put("list", null);
		model.put("page_01_name", "这是01号页面");
		model.put("page_02_name", "这是02号页面");
		model.put("page_03_name", "这是03号页面");
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
```


模板内容：
```html
-- 非空判断 + 框架标签<br>
空值处理：${list!"这个是空值"}<br>
引用页面01：<#include "page_01.ftl"><br>
引用页面02：<#include "page_02.ftl"><br>
引用页面03：<#include "page_03.ftl"><br>
```

效果图如下：
涉及多页面，省略此图！！！





其它文档：

Freemarker 中文版本文档：[【Freemarker使用指南_中文版文档】](https://download.csdn.net/download/Hello_World_QWP/12136434 'Freemarker使用指南_中文版文档')

GitHub示例源码地址：[【Java模板引擎Freemarker2.x进阶指南】](https://github.com/Jackson-AndyLau/huazai-freemarker 'Java模板引擎Freemarker2.x进阶指南')
