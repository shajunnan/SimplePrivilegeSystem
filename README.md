# 简单权限管理系统
后端SpringMVC框架 + 前端Vue渐进式框架，实现简单的角色权限管理功能。

## 需求
- 管理员登录
- 用户登录
- 根据权限动态显示菜单
- 根据权限
- 权限增删改
- 角色增删改
- 用户增删改
- 角色授权设置
- 用户角色设置
- 点击菜单显示页面服务

## 环境依赖
- JDK 1.8
- Tomcat v9.0.22
- Maven v3.6.1
- MySQL v8.0.16

## 部署步骤
1. 安装Tomcat
2. 安装Maven
3. 使用IDEA配置tomcat
4. 运行服务

## 目录结构描述 
```
├── SimplePrivilegeSystem.iml   // idea工程配置文件
├── .idea                       // idea项目配置信息
├── src                         // 
│   ├── main                    // 
│   │   ├── java                // 源代码
│   │   ├── resources           // 资源文件夹
│   │   └── webapp              // web根目录
│   │       ├── WEB-INF         // 配置文件存放目录
│   │       │   ├── spring-context.xml  // springmvc配置
│   │       │   └── web.xml             // web初始化配置信息
│   │       └── index.jsp       // 默认首页
│   └── test                    // 测试类目录
│       ├── java                // 测试源代码
│       └── resources           // 测试资源文件夹
├── Readme.md                   // help
└── pom.xml                     // maven依赖包
```

## 代码规范
1. 命名规范
    - 类名使用大驼峰
    - 变量名使用小驼峰
    - 静态变量名全大写，多单词使用下划线分隔
2. 注释规范
    - 每个函数的上方使用文档注释
    - 标注此函数功能，作者
3. 格式规范
    - 使用IDEA格式化代码功能。
