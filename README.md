# shanhai-productivity-doc
🍭山海生产力Doc - 基于SmartDoc进行个性需求定制，致敬 SmartDoc

#### 关于SmartDoc

maven plugin for smart-doc api documentation generation tool

#### SmartDoc项目地址

https://github.com/TongchengOpenSource/smart-doc-maven-plugin

#### 山海生产力Doc&SmartDoc差异

目前 山海生产力Doc 1.0 版本基于SmartDoc v3.0.1版本源码进行了局部扩展。

为了方便使用，山海生产力Doc 更换了package name，除扩展功能外，其他配置与SmartDoc V3.1的配置参数相同。

#### 山海生产力Doc扩展能力

- [ ] 支持模板文件从本地读取

  支持修改模板文件内容（不支持修改模板定义名称），做自定义显示。

#### 配置说明（smart-doc.json）

```json
{
  "projectName": "Demo Project",
  "allInOne": true,
  "extTemplateEnable": true, //是否启用自定义模板 （shanhai-productivity-doc新增）
  "extTemplateRootPath": "d:/SmartExtTemplate", //自定义模板储存根路径（shanhai-productivity-doc新增，从https://github.com/SeeMountainSea/shanhai-productivity-doc/tree/main/shanhai-productivitydoc/src/main/resources下载全部原模板文件）
  "extTemplateVariableConfigPath":"d:/SmartExtTemplate/extVariable.json" //自定义模板扩展变量配置文件（shanhai-productivity-doc新增，JSON格式文件,可以自行配置新的变量写入模板文件）
}
```

#### 注意事项

SmartDoc原模板文件有的是unix编码 ，注意修改模板文件的时候建议使用notepad++这类文件编辑器打开编辑。

不要使用其他编辑器编辑，否则会出现模板文件无法解析的情况。
