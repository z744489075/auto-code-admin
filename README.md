# auto-code-admin
欢迎使用auto-code-admin代码自动生成模板 [演示地址](http://www.zengtengpeng.com/login/gotoLogin) 账号 `ztp`  密码 `111111`

### 项目介绍
#### 为何会发起该项目?

    1.权限,角色,用户对于绝大多数系统来说都是一成不变的.而我们每开发一个后台都需要重新一次.每次都在重复造轮子
    2.对于业务绝大多数也是围绕这增删改查来进行操作.每次创建一张表.然后我们需要重新写一次增删改查,写虽然简单,不过极度耗时(view,controller,server,serverImpl,dao,xml) 
    所以才有了该项目,改项目能帮助你减少60%的工作量,让你专注于业务的实现.

### 软件架构

    1.基于 springboot 2.0.7+mybatis+mysql
    2.代码生成使用 auto-code [源码地址](https://gitee.com/ztp/auto-code)
    3.模板引擎-> thymeleaf (spring boot不推荐使用jsp jsp在打包的时候会遇到各种问题)
    4.前端主要使用layui(真的做的很漂亮)
    5.前端后台框架使用 h-ui (layui要收钱)

### 项目模块介绍

    auto-code-admin(根节点)
      -auto-code-common ->一些公共的类,以及配置
      -auto-code-sys ->系统设置模块
      -auto-code-charts ->报表模块
      -auto-code-web ->发布模块(该模块聚合以上模块进行发布,如果报表模块不想使用直接在pom.xml将该模块注释掉就好.)


### 安装教程

    1.安装jdk1.8+, mysql5.0+,maven,git 
    2.下载源码
    3. 导入 auto-code-admin 下的 auto_code.sql 初始化数据库
    4. auto-code-admin 打开cmd密令 执行 mvn clean package
    5. auto-code-admin/auto-code-web/target 找到jar包 执行 java -jar auto-code-web-1.0-SNAPSHOT.jar
    6. 访问 http://localhost:8080

### 使用说明 [源码地址](https://gitee.com/ztp/auto-code)

    auto-code-common 模块里有个 auto-code-1.0.6.jar 的本地jar.
    执行成功后将生成 controller,server,serverImpl,dao,xml,list.html,list_detail.html.如果仅仅是单表的增删改查.你将不需要任何改动.直接就可以提交代码.
    执行代码如下:
    
    
```
import com.zengtengpeng.generator.bean.StartCode;
import com.zengtengpeng.generator.utils.AutoCodeUtils;

import java.util.Arrays;
import java.util.List;

public class AutoCodeTest {
	public static void main(String[] args) {
		List dataNames= Arrays.asList("test_code");
		StartCode startCode=new StartCode();
		//jdbc连接
		startCode.setJdbc("jdbc:mysql://localhost:3306/auto_code");
		//数据库用户名
		startCode.setUser("root");
		//数据库密码
		startCode.setPassword("111111");
		//需要生成的表明
		startCode.setDataNames(dataNames);
		//生成代码的绝对路径
		startCode.setParentPath("E:\\resource\\workspaceJDB\\auto-code-admin\\auto-code-web");
		//生成代码的父包
		startCode.setParentPack("com.zengtengpeng.test");
		//开始生成
		AutoCodeUtils.startByWebAdmin(startCode);
	}
}
			  
```

    权限SQL也帮助你生成了.将打印在控制台上:
    请注意第一句sql的parent_auth_id的值, 默认我是放在 ->代码生成(id=3) 栏目下的
    如果你需要更改请将这个值改成对应的栏目就好
    
    
```
11:40:51.262 [main] INFO com.zengtengpeng.generator.create.impl.WebAdminCreateCode - 权限SQL:请注意表名注释一定要唯一,权限会找不到父ID

INSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES  ('3','测试生成代码','1','testCode/gotoList','layui-icon layui-icon-file-b','0',NOW());

INSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES  ((SELECT id FROM `sys_auth` a WHERE a.name='测试生成代码'),'查询','1','testCode/selectAll','layui-icon layui-icon-search','1',NOW());

INSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES  ((SELECT id FROM `sys_auth` a WHERE a.name='测试生成代码'),'导出','2','testCode/export','layui-icon layui-icon-next','1',NOW());

INSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES  ((SELECT id FROM `sys_auth` a WHERE a.name='测试生成代码'),'编辑','3','testCode/save','layui-icon layui-icon-edit','1',NOW());

INSERT INTO `sys_auth` (`parent_auth_id`,`name`,`sort`,`href`,`icon`,`shows`,`create_time`) VALUES  ((SELECT id FROM `sys_auth` a WHERE a.name='测试生成代码'),'删除','4','testCode/deleteByPrimaryKey','layui-icon layui-icon-fonts-del','1',NOW());
```



### 生成代码注意事项

    1.创建表结构时一定要写注释!!!,不写注释生成的代码将会没有列名等等一系列问题.写注释这个是约定!
    2.获取表名的注释有些版本的mysql获取不了,所以请在主键id上写上表名注释,到时候如果获取不了表名注释我将会从Id上获取
    3.目前支持生成 text,date,datetime,textarea,select五种类型的输入框
      3.1 text,date,datetime,textarea 映射机制我是根据数据库的数据类型进行映射的
 
数据库类型 | 输入框类型
---|---
date | date
datetime | datetime
text | textarea
其他 | text

      3.2:select类型比较特殊,由于数据库没有键值类型. 所以我采取的是在字段注释书写json格式的注释 列: {"name":"状态","1":"启用","0":"禁用"} name为该字段的中文注释,然后在输入框将会生成如下的下拉框
      
![select](http://images.zengtengpeng.com/auto-code-web/select.png)

完整的数据库实列:


```
CREATE TABLE `test_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '测试生成代码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(2) DEFAULT NULL COMMENT '{"name":"状态","1":"启用","0":"禁用"}',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `remarks` text COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='测试生成代码'
			
```

## 进阶篇

### 如何进行项目集群?

    集群非常简单只需要简单的3部就能完成
    1.安装redis 下载地址 redis官方是不支持windows的不过有社区版 下载地址
    2.使用spring-session将session放入redis中,修改 auto-code-admin/pom.xml
    
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.session</groupId>
  <artifactId>spring-session-data-redis</artifactId>
</dependency>
```

    3. 修改 auto-code-web/application-dev.properties 配置文件 增加redis配置


```
#redis
spring.redis.host=127.0.0.1
spring.redis.port=6379
```

    4.到此为止,集群成功


## 项目部分截图


![登录](http://images.zengtengpeng.com/auto-code-web/login.png)

![主页](http://images.zengtengpeng.com/auto-code-web/welcome.png)

![权限](http://images.zengtengpeng.com/auto-code-web/auth.png)

![用户](http://images.zengtengpeng.com/auto-code-web/user.png)

![用户详情](http://images.zengtengpeng.com/auto-code-web/user_detail.png)