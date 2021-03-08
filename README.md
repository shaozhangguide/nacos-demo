# nacos-demo(Nacos 课程涉及代码)

## 项目介绍
* ### ☘️介绍
     * 包含了Nacos 获取配置的基本用法
     * 定时任务的用例以及区别

* ### ☘️项目结构

     ```
        ├── README.md
        ├── pom.xml
        ├── src
        │   ├── main
        │   │   ├── java
        │   │   │   └── com
        │   │   │       └── xinshoucun
        │   │   │           └── nacosdemo
        │   │   │               ├── NacosDemoApplication.java
        │   │   │               ├── controller
        │   │   │               │   └── TestController.java
        │   │   │               └── test
        │   │   │                   └── Main.java ----定时任务、Nacos 基本用法
        │   │   └── resources
        │   │       └── application.properties
     ```  
 
* ## ☘️Nacos 系统搭建-命令合集

* ### ☘️Java
     * JDK 下载:https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html 
     * 配置JDK
       * 编辑配置文件: vi /etc/profile
       * export JAVA_HOME=”Java 目录”
       * export PATH=$JAVA_HOME/bin:$PATH
       * source /etc/profile

* ### ☘️Maven
     * 下载:http://maven.apache.org/download.cgi
     * 配置
       * vi /etc/profile
       * export M2_HOME=”Maven 目录”
       * export PATH=$M2_HOME/bin:$PATH
       * source /etc/profile

* ### ☘️MySQL
     * 安装
       * 更新YUM:rpm -Uvh  http://dev.mysql.com/get/mysql57-community-release-el7-9.noarch.rpm
       * yum -y install mysql-community-server
       * 查看安装版本:mysql -V
     * 配置
       * 启动MySQL:systemctl start mysqld
       * 设置开机自启:systemctl enable mysqld
       * 设置密码:grep 'temporary password' /var/log/mysqld.log
       * 进行安装配置:mysql_secure_installation
     * 配置远程访问
       * 登录:mysql -uroot -p
       * 授权:grant all on *.* to 'root'@'%' IDENTIFIED BY '1pyyEdmspC>b-123';
       * flush privileges;

* ### ☘️Nacos
     * 下载:https://github.com/alibaba/nacos/releases
     * 初始化
       * 初始化数据库:执行/conf 目录下的nacos-config.sql文件
       * 打开/conf/application.properties 中的数据库注解
     * 启动
       * sh startup.sh -m standalone
    
