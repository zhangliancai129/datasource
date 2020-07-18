### Oracle驱动程序的选择
+ Oracle 11
>JDK5 使用 ojdbc5<br>
>JDK6, 7, 8 及以上版本使用 ojdbc6<br>
+ Oracle 12, 18
>JDK8 及以上版本使用 ojdbc8
+ Oracle 19
>JDK10 及以上使用 ojdbc10<br>
>JDK8 使用 ojdbc8

[Oracle 驱动手动添加的方法](https://www.cnblogs.com/pangkang/p/8250014.html)<br>
>cd到下载的驱动jar包所在路径下，执行如下命令：<br>
>mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=1.0.0 -Dpackaging=jar -Dfile=ojdbc6.jar<br>
>然后在pom.xml文件中增加依赖即可