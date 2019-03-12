# auto-code-admin
欢迎使用auto-code-admin后台代码自动生成引擎.2.0重大升级.支持`单表`, `一对一`, `一对多` ,`多对多`代码生成,支持无限级联 [演示地址](http://www.zengtengpeng.com/login/gotoLogin) 账号 `ztp`  密码 `111111`

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
    3. <a href="#3.3">如何自定义方法</a>
4. <a href="#4">项目部分截图</a>
    1. <a href="#3.1">PC端</a>
    2. <a href="#3.2">移动端</a>
    
## <a name="1">项目介绍</a>
### <a name="1.1">项目的优势在哪里</a>

> 1.目前市面上的代码生成工具绝大多数仅仅支持生成单表,该项目支持 `单表`, `一对一`, `一对多` ,`多对多` 
以及所对应的页面生成.大大简化了开发的工作量

> 2.该项目仅仅只是帮你生成单表以及多表的`增删改查`,不做任何底层的改动.只要你知道怎么使用 spring boot+mybatis技术.就能看懂代码

### <a name="1.2">什么情况选择该项目</a>
> 1.该项目前后台代码一起生成.如果你是做后台.页面展示比较估计.该项目非常适合你

> 2.如果你们采用前后台分离.或者开发的页面模板不固定.请使用  [auto-code](https://gitee.com/ztp/auto-code) 该项目.
这个项目只生成接口适用性很广.spring boot项目和传统的javaweb项目都适用.不管是一次开发还是二次开发,
只需要简单的集成一个jar包以及少量的配置.就能生成代码

### <a name="1.3">为何会发起该项目? </a>

> 绝大多数时候我们都是在做`增删改查`.每次创建一张表.然后我们需要重新写一次增删改查,
写虽然简单,不过极度耗时(controller,server,serverImpl,dao,xml) 
    所以才有了该项目,该项目能帮助我们减少后台开发的80%的工作量,让你专注于业务的实现.
 

### <a name="1.4">如果您觉得项目还行.请点赞.您的支持是我最大的动力[项目地址](https://gitee.com/ztp/auto-code-admin)</a>

![start](http://images.zengtengpeng.com/auto-code-web/start.png)

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
      -auto-code-sys ->系统设置模块
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

> 2.在src/main/resources 下有 已 auto-code_*.yaml 开头的配置文件.
其实采用一个文件就行了.这里写这么多文件是怕开发人员引起不必要的误会所以一个关系就写了一个文件.
实际开发中.可以只保留一个文件(默认是 `auto-code.yaml`)

> 3.com.zengtengpeng 包下 有 `Demo1simple` 单表 `Demo2OneToOne` 一对一 `Demo3OneToMany` 一对多
`Demo4ManyToMany`多对多 例子.具体可以参考下.下面的例子就是拷贝这里代码.

#### <a name="2.1.2">单表生成</a>

> 书写yaml文件,每个字段对应的注释下面有(对应 auto-code_simple.yaml)
```yaml 
    datasourceConfig:
        #驱动名称
        driverClassName: com.mysql.jdbc.Driver
        #数据库名称
        name: auto_code
        #jdbc链接
        url: jdbc:mysql://127.0.0.1:3306/auto_code?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
        #数据库用户名
        username: root
        #数据库密码
        password: 111111
    #thymeleaf 放置页面的文件夹
    thymeleafPath: templates
    #添加权限到表的父id.如果为空则需要手动添加权限
    authParentId: 89
    globalConfig:
        #数据库表配置
        tableNames:
        #表名称
        - dataName: test_simple_code
            #别名 不写默认采用驼峰命名法 test_simple_code->TestSimpleCode
          aliasName: SimpleCode
            #如果用多张表,请按照如下写法,继续往下写.
        #        - dataName: test_code2
        #          aliasName: DDDDDDD
        #生成代码的项目路径
        parentPath: E:\resource\workspaceJDB\auto-code-admin\auto-code-web
        #生成代码的父包 如父包是com.zengtengpeng.test  controller将在com.zengtengpeng.test.controller下 bean 将在com.zengtengpeng.test.bean下 ,service,dao同理
        parentPack: com.zengtengpeng.simple
        #是否覆盖生成文件 如果为true将会把以前的文件覆盖掉
        cover: false
        #xml存放的文件夹默认 mybatisMapper
        xmlPath: mybatisMapper
```

> 编写java代码
```java
import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.autoCode.StartCode;

public class Demo1simple {
    public static void main(String[] args) {
        StartCode startCode= new AdminStartCode();
        //对应书写
        startCode.start(AdminStartCode.saxYaml("auto-code_simple.yaml"));
    }
}
```

> 生成代码如下图

![select](http://images.zengtengpeng.com/auto-code-web/simple.png)

#### <a name="2.1.3">一对一代码生成 对应 auto-code_one-to-one.yaml (代码采用追加的方式.无需担心代码被覆盖) </a>

```yaml
datasourceConfig:
    #驱动名称
    driverClassName: com.mysql.jdbc.Driver
    #数据库名称
    name: auto_code
    #jdbc链接
    url: jdbc:mysql://127.0.0.1:3306/auto_code?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
    #数据库用户名
    username: root
    #数据库密码
    password: 111111
#thymeleaf 放置页面的文件夹
thymeleafPath: templates
#添加权限到表的父id.如果为空则需要手动添加权限
authParentId: 90
globalConfig:
    #生成代码的项目路径
    parentPath: E:\resource\workspaceJDB\auto-code-admin\auto-code-web
    #生成代码的父包 如父包是com.zengtengpeng.test  controller将在com.zengtengpeng.test.controller下 bean 将在com.zengtengpeng.test.bean下 ,service,dao同理
    parentPack: com.zengtengpeng.oneToOne
    #是否覆盖生成文件 如果为true将会把以前的文件覆盖掉
    cover: false
    #xml存放的文件夹默认 mybatisMapper
    xmlPath: mybatisMapper
    # 表关系配置  一对一 一对多 多对多 代码生成 采用追加的方式不需要担心代码覆盖
    relationConfig:
        #主表
        primary:
            #数据库表名
            dataName: test_one_to_one_user
            #别名: 如果不设置将采用驼峰命名法 test_user=TestUser
            beanName: OneToOneUser
            #主键名称
            primaryKey: id
            #是否生成 单表 代码
#            generate: false
            #如果单表代码已经生成,请填写代码的父包 如 com.zengtengpeng.manyToMany.bean.ManyToManyUser  请填写 com.zengtengpeng.manyToMany
#            existParentPackage: com.zengtengpeng.manyToMany 
            #备注
            remark: "一对一用户"
        #外表
        foreign:
            #数据库表名
            dataName: test_one_to_one_class
            #别名: 如果不设置将采用驼峰命名法 test_user=TestUser
            beanName: OneToOneClass
            #外键名称 就是已哪个字段和主表关联 填写数据库字段名
            foreignKey: user_id
            #是否生成 单表 代码
#            generate: false
            #如果单表代码已经生成,请填写代码的父包 如 com.zengtengpeng.manyToMany.bean.ManyToManyUser  请填写 com.zengtengpeng.manyToMany
#            existParentPackage: com.zengtengpeng.manyToMany 
            #备注
            remark: "一对一班级"
```

> 1.执行代码
```java
import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.build.AdminBuildOneToOne;
import com.zengtengpeng.relation.utils.RelationUtils;

public class Demo2OneToOne {
    public static void main(String[] args) {
        //auto-code_one-to-one.yaml 为配置文件名 不写默认是 auto-code.yaml
        RelationUtils.oneToOne(AdminStartCode.saxYaml("auto-code_one-to-one.yaml"), new AdminStartCode(), new AdminBuildOneToOne());
    }
}
```

> 生成代码如下图

![select](http://images.zengtengpeng.com/auto-code-web/one-to-one.png)

#### <a name="2.1.4">一对多 代码生成 auto-code_one-to-many.yaml (代码采用追加的方式.无需担心代码被覆盖)</a>

```yaml
datasourceConfig:
    #驱动名称
    driverClassName: com.mysql.jdbc.Driver
    #数据库名称
    name: auto_code
    #jdbc链接
    url: jdbc:mysql://127.0.0.1:3306/auto_code?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
    #数据库用户名
    username: root
    #数据库密码
    password: 111111
#thymeleaf 放置页面的文件夹
thymeleafPath: templates
#添加权限到表的父id.如果为空则需要手动添加权限
authParentId: 91
globalConfig:
    #生成代码的项目路径
    parentPath: E:\resource\workspaceJDB\auto-code-admin\auto-code-web
    #生成代码的父包 如父包是com.zengtengpeng.test  controller将在com.zengtengpeng.test.controller下 bean 将在com.zengtengpeng.test.bean下 ,service,dao同理
    parentPack: com.zengtengpeng.oneToMany
    #是否覆盖生成文件 如果为true将会把以前的文件覆盖掉
    cover: false
    #xml存放的文件夹默认 mybatisMapper
    xmlPath: mybatisMapper
    # 表关系配置  一对一 一对多 多对多 代码生成 采用追加的方式不需要担心代码覆盖
    relationConfig:
        #主表
        primary:
            #数据库表名
            dataName: test_one_to_many_user
            #别名: 如果不设置将采用驼峰命名法 test_user=TestUser
            beanName: OneToManyUser
            #主键名称
            primaryKey: id
            #是否生成 单表 代码
#            generate: false
            #如果单表代码已经生成,请填写代码的父包 如 com.zengtengpeng.manyToMany.bean.ManyToManyUser  请填写 com.zengtengpeng.manyToMany
#            existParentPackage: com.zengtengpeng.manyToMany 
            #备注
            remark: "一对多用户"
        #外表
        foreign:
            #数据库表名
            dataName: test_one_to_many_addr
            #别名: 如果不设置将采用驼峰命名法 test_user=TestUser
            beanName: OneToManyAddr
            #外键名称 就是已哪个字段和主表关联 填写数据库字段名
            foreignKey: user_id
            #是否生成 单表 代码
#            generate: false
            #如果单表代码已经生成,请填写代码的父包 如 com.zengtengpeng.manyToMany.bean.ManyToManyUser  请填写 com.zengtengpeng.manyToMany
#            existParentPackage: com.zengtengpeng.manyToMany 
            #备注
            remark: "一对多收货地址"
```

> 生成代码如下图

![select](http://images.zengtengpeng.com/auto-code-web/one-to-many.png)

#### <a name="2.1.5">多对多 代码生成 auto-code_many-to-many.yaml (代码采用追加的方式.无需担心代码被覆盖)</a>

```yaml
datasourceConfig:
    #驱动名称
    driverClassName: com.mysql.jdbc.Driver
    #数据库名称
    name: auto_code
    #jdbc链接
    url: jdbc:mysql://127.0.0.1:3306/auto_code?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai
    #数据库用户名
    username: root
    #数据库密码
    password: 111111
#thymeleaf 放置页面的文件夹
thymeleafPath: templates
#添加权限到表的父id.如果为空则需要手动添加权限
authParentId: 92
globalConfig:
    #生成代码的项目路径
    parentPath: E:\resource\workspaceJDB\auto-code-admin\auto-code-web
    #生成代码的父包 如父包是com.zengtengpeng.test  controller将在com.zengtengpeng.test.controller下 bean 将在com.zengtengpeng.test.bean下 ,service,dao同理
    parentPack: com.zengtengpeng.manyToMany
    #是否覆盖生成文件 如果为true将会把以前的文件覆盖掉
    cover: false
    #xml存放的文件夹默认 mybatisMapper
    xmlPath: mybatisMapper
    # 表关系配置  一对一 一对多 多对多 代码生成 采用追加的方式不需要担心代码覆盖
    relationConfig:
        #主表
        primary:
            #数据库表名
            dataName: test_many_to_many_user
            #别名: 如果不设置将采用驼峰命名法 test_user=TestUser
            beanName: ManyToManyUser
            #主键名称
            primaryKey: id
            #是否生成 单表 代码
#            generate: false
                        #如果单表代码已经生成,请填写代码的父包 如 com.zengtengpeng.manyToMany.bean.ManyToManyUser  请填写 com.zengtengpeng.manyToMany
#            existParentPackage: com.zengtengpeng.manyToMany 
            #备注
            remark: "多对多用户"
        #外表
        foreign:
            #数据库表名
            dataName: test_many_to_many_role
            #别名: 如果不设置将采用驼峰命名法 test_user=TestUser
            beanName: ManyToManyRole
            #外键名称 就是已哪个字段和主表关联 填写数据库字段名
            foreignKey: id
            #备注
            remark: "多对多角色"
            #是否生成 单表 代码
#            generate: false
            #如果单表代码已经生成,请填写代码的父包 如 com.zengtengpeng.manyToMany.bean.ManyToManyUser  请填写 com.zengtengpeng.manyToMany
#            existParentPackage: com.zengtengpeng.manyToMany 
        #第三表 -当生成多对多代码时该参数必填.否则会忽略该参数
        thirdparty:
            #数据库表名
            dataName: test_many_to_many_user_role
            #主键名称 该字段将和主表关联起来
            primaryKey: user_id
            #外键名称 该字段将和外表配置关联起来
            foreignKey: role_id
            #备注
            remark: "多对多用户角色"

```

> 执行代码
```java
import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.build.AdminBuildManyToMany;
import com.zengtengpeng.relation.utils.RelationUtils;

public class Demo4ManyToMany {
    public static void main(String[] args) {
        //auto-code_many-to-many.yaml 为配置文件名 不写默认是 auto-code.yaml
        RelationUtils.manyToMany(AdminStartCode.saxYaml("auto-code_many-to-many.yaml"), new AdminStartCode(), new AdminBuildManyToMany());
    }
}
```

> 生成代码如下图

![select](http://images.zengtengpeng.com/auto-code-web/one-to-many.png)



### <a name="2.2">生成代码注意事项</a>

> 1.多表支持无限级联生成. 比如 用户和收货地址对应,用户表和班级对应,等等.. 只要注意在书写yaml文件时 将 
```
 #是否生成 单表 代码
  generate: false
  #如果单表代码已经生成,请填写代码的父包 如 com.zengtengpeng.manyToMany.bean.ManyToManyUser  请填写 com.zengtengpeng.manyToMany
  existParentPackage: com.zengtengpeng.manyToMany 
```
填上就行. 如上我为了程序的可读性故意将user建成3张表,其实只要一张表就够了

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
![select](http://images.zengtengpeng.com/auto-code-web/select.png)


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

### <a name="3.3">如何自定义方法</a>

> 具体请参照 [auto-code](https://gitee.com/ztp/auto-code#3)

## <a name="4">项目部分截图</a>

### <a name="4.1">pc</a>

![登录](http://images.zengtengpeng.com/auto-code-web/login.png)

![主页](http://images.zengtengpeng.com/auto-code-web/welcome.png)

![权限](http://images.zengtengpeng.com/auto-code-web/role.png)

![用户](http://images.zengtengpeng.com/auto-code-web/user.png)

![用户详情](http://images.zengtengpeng.com/auto-code-web/user_detail.png)

### <a name="4.2">mobile</a>

![登录](http://images.zengtengpeng.com/auto-code-web/mobile/login.png)

![菜单](http://images.zengtengpeng.com/auto-code-web/mobile/menu.png)

![主页](http://images.zengtengpeng.com/auto-code-web/mobile/welcome.png)

![权限](http://images.zengtengpeng.com/auto-code-web/mobile/auth.png)

![用户](http://images.zengtengpeng.com/auto-code-web/mobile/user.png)

![用户详情](http://images.zengtengpeng.com/auto-code-web/mobile/user_detail.png)