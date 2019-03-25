# auto-code-admin
欢迎使用auto-code-admin后台代码自动生成引擎.2.1.0再次升级.支持可视化生成`单表`, `一对一`, `一对多` ,`多对多`代码.
不再需要写yaml文件,支持无限级联 [演示地址](http://106.13.119.110:8010/login/gotoLogin) 账号 `ztp`  密码 `111111`
(服务器在美国,有时访问可能比较慢.)
# 目录
1. <a href="#1">项目介绍</a>
    1. <a href="#1.1">项目的优势在哪里</a>
    2. <a href="#1.2">什么情况选择该项目</a>
    3. <a href="#1.3">为何会发起该项目</a>
    4. <a href="#1.4">如果您觉得项目还行.请点赞</a>
    5. <a href="#1.5">软件架构</a>
    5. <a href="#1.6">项目模块介绍</a>
2. <a href="#2">安装教程</a>
    1. <a href="#2.1">代码生成教程</a>
        1. <a href="#2.1.1">介绍</a>
        2. <a href="#2.1.2">单表生成</a>
        3. <a href="#2.1.3">一对一</a>
        4. <a href="#2.1.4">一对多</a>
        5.  <a href="#2.1.5">多对多</a>
    2.  <a href="#2.2">生成代码注意事项</a>
3. <a href="#3">进阶篇</a>
    1. <a href="#3.1">如何进行项目集群</a>
    2. <a href="#3.2">权限机制的实现</a>
    3. <a href="#3.3">配置参数介绍</a>
    4. <a href="#3.4">如何升级</a>
4. <a href="#4">项目部分截图</a>
    1. <a href="#4.1">PC端</a>
    2. <a href="#4.2">移动端</a>
    
## 更新

> 2019-03-22: 版本:2.1.0 生成代码增加可视化视图,不再需要写yaml文件配置了

> 2019-03-18 增加 swagger api支持

    1.增加权限 auto-code-admin/update_sql/2019-03-18.sql
    2. yaml 文件 globalConfig 增加  swagger: true 配置
    
## <a name="1">项目介绍</a>
### <a name="1.1">项目的优势在哪里</a>

> 1.目前市面上的代码生成工具绝大多数仅仅支持生成单表,该项目支持 `单表`, `一对一`, `一对多` ,`多对多` 
以及所对应的页面生成.大大简化了开发的工作量

> 2.该项目仅仅只是帮你生成单表以及多表的`增删改查`,不做任何底层的改动.只要你知道怎么使用 spring boot+mybatis技术.就能看懂代码

### <a name="1.2">什么情况选择该项目</a>
> 1.该项目前后台代码一起生成.如果你是做后台.页面展示比较固定.该项目非常适合你

> 2.如果你们采用前后台分离.或者开发的页面模板不固定.请使用  [auto-code](https://gitee.com/ztp/auto-code) 该项目.
这个项目只生成接口适用性很广.spring boot项目和传统的javaweb项目都适用.不管是一次开发还是二次开发,
只需要简单的集成一个jar包以及少量的配置.就能生成代码

### <a name="1.3">为何会发起该项目? </a>

> 绝大多数时候我们都是在做`增删改查`.每次创建一张表.然后我们需要重新写一次增删改查,
写虽然简单,不过极度耗时(controller,server,serverImpl,dao,xml) 
    所以才有了该项目,该项目能帮助我们减少后台开发的80%的工作量,让你专注于业务的实现.
 

### <a name="1.4">如果您觉得项目还行.请点赞.您的支持是我最大的动力[项目地址](https://gitee.com/ztp/auto-code-admin)</a>

![start](http://106.13.119.110/auto-code-web/start.png)

### <a name="1.5">软件架构</a>

    1.基于 springboot 2.0.7+mybatis+mysql
    2.基于代码生成引擎做了页面的生成扩展 auto-code [源码地址](https://gitee.com/ztp/auto-code)
    3.模板引擎-> thymeleaf 
    4.前端主要使用layui
    5.前端后台框架layuiAdmin  https://github.com/coderyangjie/layuiAdmin

### <a name="1.6">项目模块介绍</a>

    auto-code-admin(根节点)
      -auto-code-generator  代码生成配置
      -auto-code-common ->一些公共的类,以及配置
      -auto-code-sys ->系统设置,权限,日志模块
      -auto-code-charts ->报表模块
      -auto-code-web ->发布模块(该模块聚合以上模块进行发布,如果报表模块不想使用直接在pom.xml将该模块注释掉就好.)

## <a name="2">安装教程</a>

    1.安装jdk1.8+, mysql5.0+,maven,git 
    2.下载源码
    3. 导入 auto-code-admin 下的 auto_code.sql 初始化数据库
    4. 修改 auto-code-web/application-dev.properties 中的数据库连接以及用户名密码
    5. auto-code-admin 打开cmd密令 执行 mvn clean package
    6. auto-code-admin/auto-code-web/target 找到jar包 执行 java -jar auto-code-web.jar
    7. 访问 http://localhost:8080


### <a name="2.1">代码生成教程</a>

#### <a name="2.1.1">auto-code-generator代码生成介绍</a>

> 1.代码生成主要在 auto-code-generator 子模块中. 改模块基于 [auto-code](https://gitee.com/ztp/auto-code)
做的扩展.扩展了怎么生成 `单表` `一对一` `一对多` `多对多` 的页面展示关系.

> 2. 在 `application.properties` 文件中填写参数(不填也没事,可以到页面配置.不过建议填写.当项目重新页面参数会重置的)

    # 代码生成器
    #生成代码的项目根路径
    auto-code.parentPath=E:\\resource\\workspaceJDB\\auto-code-admin\\auto-code-web
    #生成代码的父包 如父包是com.zengtengpeng.test  controller将在com.zengtengpeng.test.controller下 bean 将在com.zengtengpeng.test.bean下 ,service,dao同理
    auto-code.parentPack=com.zengtengpeng.simple
    #添加权限到表的父id.如果为空则需要手动添加权限
    auto-code.authParentId=89
    
> 3.这是代码生成的页面地址  http://localhost:8080/auto-code-ui/ui/index.html

#### <a name="2.1.2">单表生成</a>

>1.界面选择单表生成.选择对应的表,点击`生成预览`. 最后点击`确认生成`. 完毕.

![simple](http://106.13.119.110/auto-code-ui/simple.png)

>2.生成的文件如下:
![select](http://106.13.119.110/auto-code-web/simple.png)

#### <a name="2.1.3">一对一代码生成 对应 auto-code_one-to-one.yaml (代码采用追加的方式.无需担心代码被覆盖) </a>

>1. 打开界面.选择对应的表,一对一比单表多了一个外键id.
比如界面两张表的关系就是通过 user_id来关联的 如下:

    test_one_to_one_user.id=test_one_to_one_class.user_id

![one-to-one](http://106.13.119.110/auto-code-ui/one-to-one1.png)
![one-to-one](http://106.13.119.110/auto-code-ui/one-to-one2.png)

>2.点击生成完毕.生成的文件和单表是一样的.里面会增加新的方法

#### <a name="2.1.4">一对多 代码生成 auto-code_one-to-many.yaml (代码采用追加的方式.无需担心代码被覆盖)</a>

>  `一对多`与`一对一`一样不再描述

#### <a name="2.1.5">多对多 代码生成 auto-code_many-to-many.yaml (代码采用追加的方式.无需担心代码被覆盖)</a>


> 1.打开界面.选择对应的表,`多对多`比`一对一`多了一个关系描述表
    比如图片三张表的关系是通过 test_many_to_many_user_role来表述的.如下:
    
    test_many_to_many_user_role.user_id=test_many_to_many_user.id and test_many_to_many_user_role.role_id=test_many_to_many_role.id

![many-to-many](http://106.13.119.110/auto-code-ui/many-to-many1.png)
![many-to-many](http://106.13.119.110/auto-code-ui/many-to-many2.png)
![many-to-many](http://106.13.119.110/auto-code-ui/many-to-many3.png)

>2.点击生成完毕.生成的文件和单表是一样的.里面会增加新的方法

### <a name="2.2">生成代码注意事项</a>

> 1.多表支持无限级联生成. 比如 用户和收货地址对应,用户表和班级对应...

> 2.创建表结构时如果写上表与字段的注释将大大增加程序的可读性.我会将注释写到bean上面.
以及页面上.没有写注释还需要页面修改对应的值.会加大工作量.举例:
```sql

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

> 3.如果注释为json键值对字符串我将会在实体类生成一个字典方法
    如:  {"name":"状态","1":"启用","0":"禁用"} 将会在实体类里面生成如下,同时页面展示的名称将是 "状态"
    
```
public String getStatus_(){
    if(MyStringUtils.isEmpty(status)){
         return "";
    }else if(status.equals("1")){
        return "启用";
    }else if(status.equals("0")){
        return "禁用";
    }
    return "";
}
```
页面展示
![select](http://106.13.119.110/auto-code-web/select.png)


## <a name="3">进阶篇</a>

### <a name="3.1">如何进行项目集群</a>

#### 集群非常简单只需要简单的3部就能完成

>1.安装redis 下载地址 redis官方是不支持windows的不过有社区版 下载地址

>2.使用spring-session将session放入redis中,修改 auto-code-admin/pom.xml
    
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
> 3.修改 auto-code-web/application-dev.properties 配置文件 增加redis配置

```
#redis
spring.redis.host=127.0.0.1
spring.redis.port=6379
```
>4.到此为止,集群成功

### <a name="3.2">权限机制的实现</a>

> 本项目采用拦截器管理权限 具体实现 com.zengtengpeng.interceptor.UserInterceptor

    1.具体怎么实现?  权限默认采用@RequestMapping映射值作为权限校验.
    2.如果两个url想采用一个权限怎么办? 使用 com.zengtengpeng.common.annotation.Auth 注解, 
    值为 另外一个权限url.这样他们就共享同一个授权了,如果不写值默认只要登录就有该权限.
    3.如果该方法不需要登录就能访问怎么办? com.zengtengpeng.common.annotation.Pass 
    注解标注在方法上.该方法就不需要授权

### <a name="3.3">配置参数介绍</a>

> 基础请参照参数 [auto-code](https://gitee.com/ztp/auto-code#3),admin在基础参数的基础上扩展了两个特有的参数

参数名 |  默认值 |介绍
---|---|---
 authParentId| 无 | 添加权限到表的父id.如果为空则不自动生成权限
 thymeleafPath | templates |thymeleaf 放置页面的文件目录
 
 
### <a name="3.4">如何升级</a>

    1.下载项目后,自己的代码请放到 auto-code-web子模块或者自己重新新建模块
    2.更新请将 auto-code-common,auto-code-generator,auto-code-sys 覆盖成最新的代码就行.
    3.如果数据库有更新我会在上面更新介绍更新的SQL文件

## <a name="4">项目部分截图</a>

### <a name="4.1">pc</a>

![登录](http://106.13.119.110/auto-code-web/login.png)

![主页](http://106.13.119.110/auto-code-web/welcome.png)

![权限](http://106.13.119.110/auto-code-web/role.png)

![用户](http://106.13.119.110/auto-code-web/user.png)

![用户详情](http://106.13.119.110/auto-code-web/user_detail.png)

### <a name="4.2">mobile</a>

![登录](http://106.13.119.110/auto-code-web/mobile/login.png)

![菜单](http://106.13.119.110/auto-code-web/mobile/menu.png)

![主页](http://106.13.119.110/auto-code-web/mobile/welcome.png)

![权限](http://106.13.119.110/auto-code-web/mobile/auth.png)

![用户](http://106.13.119.110/auto-code-web/mobile/user.png)

![用户详情](http://106.13.119.110/auto-code-web/mobile/user_detail.png)