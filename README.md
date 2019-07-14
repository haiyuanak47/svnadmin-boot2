# SvnAdmin-boot2

#### SVN管理工具

> 主要功能 
- 支持用户现有SVN项目导入，一键迁移；
- SVN仓库管理；
- SVN用户，用户组管理；
- SVN资源权限授权；
- 用户权限查看，密码更改；
- SVN仓库支持多库模式；

> 软件架构 
- 框架技术： SpringBoot_2.1.6.RELEASE + svnkit + Druid（数据库连接池）
- 项目构建： Maven、Jdk8

> 一、使用源码开发部署步骤：
1. 下载项目源码；
1. 找到文件 test\resources\svnadmin_init.sql 进行执行初始化；
1. 默认root账户：root/root
1. 删除所有账户，进行登录，则可以重新初始化管理员账号；
1. SVN认证账户和登录账户默认一致；


> 二、部署步骤：
1. 找到文件 src\test\resources\sql\svnadmin_init.sql 进行执行初始化；
1. 配置数据库连接信息，配置文件位置：resources/application-dev.yml
1. 打jar包后运行run.sh（需修改配置）sh run.sh start；环境推荐JDK1.8 
1. 默认root账户：root/root
1. 删除所有账户，进行登录，则可以重新初始化管理员账号；
1. SVN认证账户和登录账户默认一致；


> 三、使用多库启动模式：

假设你的SVN地址为D:\svn\demo，
那么你需要使用多库的启动方式

```
svnserve -d -r D:\svn
```
你的访问路径将是这样的：
svn://localhost/demo


> 四、使用单库启动：

 修改PjAuthService 的 formatRes方法增加
```
//替换项目根目录20190701 单库不带别名访问
res = StringUtils.replaceEach(res, new String[]{relateRoot+":"}, new String[]{""});
```
> 五、MySQL8.X支持：

- mysql 5.X 修改POM文件 
```
<jdbc.mysql.version>5.1.14</jdbc.mysql.version>
//驱动
driver-class-name: com.mysql.jdbc.Driver
```
- mysql 8.X 修改POM文件和数据库URL 
```
<jdbc.mysql.version>8.0.16</jdbc.mysql.version> 
 //MySQL.jdbc.url 增加 serverTimezone=UTC
MySQL.jdbc.url=jdbc:mysql://localhost:3310/svnadmin?characterEncoding=utf-8&serverTimezone=UTC
//驱动
driver-class-name: com.mysql.cj.jdbc.Driver
```
> 六、项目总结备忘：
- jsp支持 

使用1.4.2.RELEASE版本
```
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-maven-plugin</artifactId>
<!-- 必须是1.4.2版本，否则jsp访问不到 -->
<version>1.4.2.RELEASE</version>
```
jsp文件需要放置到META-INF/resources下才能访问
```
<resource>
   <directory>src/main/webapp</directory>
   <!--注意此次必须要放在此目录下才能被访问到 -->
	<targetPath>META-INF/resources</targetPath>
	<includes>
		<include>**/**</include>
	</includes>
</resource>
```
