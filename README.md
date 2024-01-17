# 山海生产力Doc简介
🍭山海生产力Doc - 基于SmartDoc进行个性需求定制，致敬 SmartDoc

### 关于SmartDoc

maven plugin for smart-doc api documentation generation tool

### SmartDoc项目地址

https://github.com/TongchengOpenSource/smart-doc-maven-plugin

### 山海生产力Doc&SmartDoc差异

目前 山海生产力Doc 1.0 版本基于SmartDoc v3.0.1版本源码进行了局部扩展。

为了方便使用，山海生产力Doc 更换了package name，除扩展功能外，其他配置与SmartDoc v3.0.1的配置参数相同。

# 山海生产力Doc使用说明

### 引入插件

```
 <plugin>
        <groupId>com.wangshanhai.productivitydoc</groupId>
        <artifactId>shanhai-productivitydoc-maven-plugin</artifactId>
        <version>1.0.1</version>
        <configuration>
            <configFile>./src/main/resources/smart-doc.json</configFile>
            <projectName>Demo API</projectName>
            <excludes>
                <exclude>com.alibaba:fastjson</exclude>
            </excludes>
            <includes>
                <include>com.alibaba:fastjson</include>
            </includes>
        </configuration>
        <executions>
            <execution>
                <goals>
                    <goal>html</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
</plugins>
```

注：山海生产力Doc此处配置与SmartDoc除了groupId、artifactId、version配置不同外，其他配置内容与SmartDoc一致。

### 扩展能力

- [x] 支持模板文件从本地读取

  支持修改模板文件内容（不支持修改模板定义名称），做自定义显示。

### 插件配置参数说明

smart-doc版本：v3.0.1

山海生产力Doc版本：v1.0.x

| 配置| 必填 | 类型| 默认值| 描述|
|----------------------------------------------------------- | ---- | -------------- | ------------------- | ------------------------------------------------------------ |
| `outPath`                                                    | ✔    | `String`       |                     | 指定文档的输出路径                                           |
| `serverUrl`                                                  | ❌    | `String`       | `http://127.0.0.1`  | 服务器地址, 导出`postman`建议设置成`http://{{server}}`方便直接在`postman`直接设置环境变量。 `2.4.8`后导出`postman`建议使用`serverEnv`,避免多出导出时修改配置。 |
| `serverEnv`                                                  | ❌    | `String`       |                     | 服务器地址, 导出`postman`建议设置成`http://{{server}}`方便直接在`postman`直接设置环境变量。改配置是为了支持postman导出时不用全局修改`serverUrl` |
| `pathPrefix`                                                 | ❌    | `String`       |                     | 设置`path`前缀, 如配置`Servlet ContextPath`。                |
| `isStrict`                                                   | ❌    | `Boolean`      |                     | 是否开启严格模式,严格模式会强制检查代码注释，在`2.6.3`即以后的插件版本设置此项时检查到注释错误也会直接中断插件白嵌套的构建周期。 作为团队使用建议使用设置为`true`，提升对开发人员的注释要求，提升文档的完善度。 |
| `allInOne`                                                   | ❌    | `Boolean`      | `false`             | 是否将文档合并到一个文件中，一般推荐为`true`。               |
| `coverOld`                                                   | ❌    | `Boolean`      | `false`             | 是否覆盖旧的文件，主要用于`Markdown`文件覆盖。               |
| `createDebugPage`                                            | ❌    | `Boolean`      | `false`             | `smart-doc`支持创一个类似`Swagger`那种可调试接口的`HTML`文档页面，仅在`AllInOne`模式中起作用。 从@2.0.1开始，对html文档，无论是allInOne还是非allInOne模式都能够生成debug页面。 |
| [`packageFilters`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=packagefilters) | ❌    | `String`       |                     | `Controller`包过滤，多个包用英文逗号隔开。 `2.2.2`开始需要采用正则：`com.test.controller.*` `2.7.1`开始支持方法级别正则：`com.test.controller.TestController.*` |
| `packageExcludeFilters`                                      | ❌    | `String`       |                     | 对`packageFilters`排除子包，多个包用英文逗号隔开 `2.2.2`开始需要采用正则：`com.test.controller.res.*` |
| `md5EncryptedHtmlName`                                       | ❌    | `Boolean`      | `false`             | 只有每个`Controller`生成一个`HTML`文件是才使用。             |
| `style`                                                      | ❌    | `String`       |                     | 基于`highlight.js`的[代码高亮](https://highlightjs.org/)设置。 |
| `projectName`                                                | ❌    | `String`       |                     | 只有每个`Controller`生成一个`HTML`文件是才使用。 如果`smart-doc.json`中和插件中都未设置`projectName`，`2.3.4`开始，插件自动采用`pom`中的`projectName`作为默认填充， 因此使用插件时可以不配置。 |
| `sortByTitle`                                                | ❌    | `Boolean`      | `false`             | 接口标题排序。                                               |
| `showAuthor`                                                 | ❌    | `Boolean`      | `true`              | 是否显示接口作者名称。                                       |
| `requestFieldToUnderline`                                    | ❌    | `Boolean`      | `false`             | 自动将驼峰入参字段在文档中转为下划线格式。                   |
| `responseFieldToUnderline`                                   | ❌    | `Boolean`      | `false`             | 自动将驼峰响应字段在文档中转为下划线格式。                   |
| `inlineEnum`                                                 | ❌    | `Boolean`      | `false`             | 是否将枚举详情展示到参数表中。                               |
| `recursionLimit`                                             | ❌    | `int`          | `7`                 | 设置允许递归执行的次数用于避免一些对象解析卡主。             |
| `allInOneDocFileName`                                        | ❌    | `String`       | `index.html`        | 只有配置项目所有`Controller`生成一个`HTML`文件时才生效。     |
| `requestExample`                                             | ❌    | `Boolean`      | `true`              | 是否将请求示例展示在文档中。                                 |
| `responseExample`                                            | ❌    | `Boolean`      | `true`              | 是否将响应示例展示在文档中。                                 |
| `urlSuffix`                                                  | ❌    | `String`       |                     | 支持`SpringMVC`旧项目的`url`后缀。                           |
| `language`                                                   | ❌    | `String`       | `CHINESE`           | mock值的国际化支持。                                         |
| `displayActualType`                                          | ❌    | `Boolean`      | `false`             | 是否在注释栏自动显示泛型的真实类型短类名。                   |
| `appKey`                                                     | ❌    | `String`       |                     | `torna`平台对接`appKey`。                                    |
| `appToken`                                                   | ❌    | `String`       |                     | `torna`平台`appToken`。                                      |
| `secret`                                                     | ❌    | `String`       |                     | `torna`平台`secret`。                                        |
| `openUrl`                                                    | ❌    | `String`       |                     | `torna`平台地址，填写自己的私有化部署地址。                  |
| `debugEnvName`                                               | ❌    | `String`       |                     | `torna`环境名称。                                            |
| `replace`                                                    | ❌    | `Boolean`      | `true`              | 推送`torna`时替换旧的文档。改动还是会推送过去覆盖的，这个功能主要是保证代码删除了，`torna`上没有删除。 |
| `debugEnvUrl`                                                | ❌    | `String`       |                     | 推送`torna`配置接口服务地址。                                |
| `tornaDebug`                                                 | ❌    | `Boolean`      | `true`              | 是否打印`torna`推送日志。                                    |
| `ignoreRequestParams`                                        | ❌    | `List<String>` |                     | 忽略请求参数对象，把不想生成文档的参数对象屏蔽掉。           |
| [`dataDictionaries`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=datadictionaries) | ❌    | `List<Object>` |                     | 配置数据字典 `2.4.6`开始可以配置枚举实现的接口， 当配置接口时title将使用实现枚举的类描述，如果有已经实现的枚举需要忽略的话，可以在实现枚举类上增加`@ignore`进行忽略。 |
| [`errorCodeDictionaries`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=errorcodedictionaries) | ❌    | `List<Object>` |                     | 错误码列表 `2.4.6`开始可以配置枚举实现的接口， 当配置接口时title将使用实现枚举的类描述，如果有已经实现的枚举需要忽略的话，可以在实现枚举类上增加`@ignore`进行忽略。 |
| [`revisionLogs`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=revisionlogs) | ❌    | `List<Object>` |                     | 文档变更记录。                                               |
| [`customResponseFields`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=customresponsefields) | ❌    | `List<Object>` |                     | 自定义添加字段和注释，一般用户处理第三方`jar`包库。          |
| [`customRequestFields`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=customrequestfields) | ❌    | `List<Object>` |                     | 自定义请求体的注释。                                         |
| [`requestHeaders`](https://smart-doc-group.github.io/#/./advanced.md/?id=公共请求头) | ❌    | `List<Object>` |                     | 设置公共请求头。                                             |
| [`requestParams`](https://smart-doc-group.github.io/#/./advanced.md/?id=公共请求参数) | ❌    | `List<Object>` |                     | 公共请求参数(通过拦截器处理的场景)。                         |
| [`rpcApiDependencies`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=rpcapidependencies) | ❌    | `List<Object>` |                     | 项目开放的`Dubbo API`接口模块依赖，配置后输出到文档方便使用者集成。 |
| `rpcConsumerConfig`                                          | ❌    | `String`       |                     | 文档中添加`Dubbo Consumer`集成配置，用于方便集成方可以快速集成。 |
| [`apiObjectReplacements`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=apiobjectreplacements) | ❌    | `List<Object>` |                     | 使用自定义类覆盖其他类做文档渲染。                           |
| [`apiConstants`](https://smart-doc-group.github.io/#/./advanced.md/?id=静态常量替换) | ❌    | `List<Object>` |                     | 配置自己的常量类，`smart-doc`在解析到常量时自动替换为具体的值。 `2.4.2`版本开始使用到常量也无需配置，`smart-doc`已经能够自动解析。 |
| [`responseBodyAdvice`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=responsebodyadvice) | ❌    | `List<Object>` |                     | `ResponseBodyAdvice`是`Spring`框架中预留的钩子，它作用在`Controller`方法执行完成之后，`http`响应体写回客户端之前， 它能方便的织入一些自己的业务逻辑处理了，因此`smart-doc`也提供了对`ResponseBodyAdvice`统一返回设置(不要随便配置根据项目的技术来配置)支持， 可用`ignoreResponseBodyAdvice` tag来忽略。 |
| [`requestBodyAdvice`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=requestbodyadvice) | ❌    | `List<Object>` |                     | 设置`RequestBodyAdvice`统一请求包装类。                      |
| [`groups`](https://smart-doc-group.github.io/#/zh-cn/advanced/config?id=groups) | ❌    | `List<Object>` |                     | 对不同的`Controller`进行分组。                               |
| `requestParamsTable`                                         | ❌    | `String`       |                     | 是否将请求参数表展示在文档中。                               |
| `responseParamsTable`                                        | ❌    | `Boolean`      |                     | 是否将响应参数表展示在文档中。                               |
| `framework`                                                  | ❌    | `String`       | `spring` or `dubbo` | `Spring`和`Apache Dubbo`是`smart-doc`默认支持解析生成文档的框架，不配置`framework`时根据触发的文档构建场景自动选择`Spring`或者 `Dubbo`，`smart-doc`目前也支持`JAX-RS`的标准，因此使用支持`JAX-RS`标准的框架(如：`Quarkus`)可以作为体验使用，但是还不完善。 可选值: `spring`,`dubbo`,`JAX-RS`,`solon` |
| `randomMock`                                                 | ❌    | `Boolean`      | `false`             | `randomMock`用于控制是否让`smart-doc`生成随机`mock`值，在`2.6.9`之前的版本中`smart-doc`会自动给参数和自动生成随机值， 每次生成的值都不一样，现在你可以设置为`false`来控制随机值的生成。 |
| `componentType`                                              | ❌    | `String`       | `RANDOM`            | `openapi component key generator` `RANDOM` : 支持 `@Validated` 分组校验 `NORMAL`: 不支持 `@Validated`, 用于 `openapi` 生成代码 |
| `apiUploadNums`                                              | ❌    | `Integer`      | `RANDOM`            | 上传torna时，支持文档分批上传，设置文档批次的大小。          |
| extTemplateEnable | ❌ | Boolean | false | 是否启用自定义模板 （shanhai-productivity-doc新增） |
| extTemplateRootPath | ❌ | String |  | 自定义模板储存根路径（shanhai-productivity-doc新增，从https://github.com/SeeMountainSea/shanhai-productivity-doc/tree/main/shanhai-productivitydoc/src/main/resources下载全部原模板文件） |
| extTemplateVariableConfigPath | ❌ | String |  | 自定义模板扩展变量配置文件（shanhai-productivity-doc新增，JSON格式文件,可以自行配置新的变量写入模板文件） |



### 插件配置样例（smart-doc.json）

```json
{
    "serverUrl": "http://127.0.0.1",
    "serverEnv": "http://{{server}}",
    "pathPrefix": "",
    "isStrict": false,
    "allInOne": true,
    "outPath": "D://md2",
    "randomMock": false,
    "coverOld": true,
    "createDebugPage": true,
    "packageFilters": "",
    "packageExcludeFilters": "",
    "md5EncryptedHtmlName": false,
    "style": "xt256",
    "projectName": "smart-doc",
    "framework": "spring",
    "skipTransientField": true,
    "sortByTitle": false,
    "showAuthor": true,
    "requestFieldToUnderline": true,
    "responseFieldToUnderline": true,
    "inlineEnum": true,
    "recursionLimit": 7,
    "allInOneDocFileName": "index.html",
    "requestExample": "true",
    "responseExample": "true",
    "requestParamsTable": true,
    "responseParamsTable": true,
    "urlSuffix": ".do",
    "displayActualType": false,
    "appToken": "c16931fa6590483fb7a4e85340fcbfef",
    "isReplace": true,
    "openUrl": "http://localhost:7700/api",
    "debugEnvName": "测试环境",
    "debugEnvUrl": "http://127.0.0.1",
    "tornaDebug": false,
    "ignoreRequestParams": [
        "org.springframework.ui.ModelMap"
    ],
    "dataDictionaries": [
        {
            "title": "http状态码字典",
            "enumClassName": "com.power.common.enums.HttpCodeEnum",
            "codeField": "code",
            "descField": "message"
        },
        {
            "enumClassName": "com.xx.IEnum",
            "codeField": "code",
            "descField": "message"
        }
    ],
    "errorCodeDictionaries": [
        {
            "title": "title",
            "enumClassName": "com.power.common.enums.HttpCodeEnum",
            "codeField": "code",
            "descField": "message",
            "valuesResolverClass": ""
        },
        {
            "enumClassName": "com.xx.IEnum",
            "codeField": "code",
            "descField": "message"
        }
    ],
    "revisionLogs": [
        {
            "version": "1.0",
            "revisionTime": "2020-12-31 10:30",
            "status": "update",
            "author": "author",
            "remarks": "desc"
        }
    ],
    "customResponseFields": [
        {
            "name": "code",
            "desc": "响应代码",
            "ownerClassName": "org.springframework.data.domain.Pageable",
            "ignore": true,
            "value": "00000"
        }
    ],
    "customRequestFields": [
        {
            "name": "code",
            "desc": "状态码",
            "ownerClassName": "com.xxx.constant.entity.Result",
            "value": "200",
            "required": true,
            "ignore": false
        }
    ],
    "requestHeaders": [
        {
            "name": "token",
            "type": "string",
            "desc": "desc",
            "value": "token请求头的值",
            "required": false,
            "since": "-",
            "pathPatterns": "/app/test/**",
            "excludePathPatterns": "/app/page/**"
        },
        {
            "name": "appkey",
            "type": "string",
            "desc": "desc",
            "value": "appkey请求头的值",
            "required": false,
            "pathPatterns": "/test/add,/testConstants/1.0",
            "since": "-"
        }
    ],
    "requestParams": [
        {
            "name": "configPathParam",
            "type": "string",
            "desc": "desc",
            "paramIn": "path",
            "value": "testPath",
            "required": false,
            "since": "-",
            "pathPatterns": "**",
            "excludePathPatterns": "/app/page/**"
        },
        {
            "name": "configQueryParam",
            "type": "string",
            "desc": "desc",
            "paramIn": "query",
            "value": "testQuery",
            "required": false,
            "since": "-",
            "pathPatterns": "**",
            "excludePathPatterns": "/app/page/**"
        }
    ],
    "rpcApiDependencies": [
        {
            "artifactId": "SpringBoot2-Dubbo-Api",
            "groupId": "com.demo",
            "version": "1.0.0"
        }
    ],
    "rpcConsumerConfig": "src/main/resources/consumer-example.conf",
    "apiObjectReplacements": [
        {
            "className": "org.springframework.data.domain.Pageable",
            "replacementClassName": "com.power.doc.model.PageRequestDto"
        }
    ],
    "apiConstants": [
        {
            "constantsClassName": "com.power.doc.constants.RequestParamConstant"
        }
    ],
    "responseBodyAdvice": {
        "className": "com.power.common.model.CommonResult"
    },
    "requestBodyAdvice": {
        "className": "com.power.common.model.CommonResult"
    },
    "groups": [
        {
            "name": "测试分组",
            "apis": "com.power.doc.controller.app.*"
        }
    ],
    "requestParamsTable": true,
    "responseParamsTable": true,
    "componentType": 1,
    "extTemplateEnable": true, 
    "extTemplateRootPath": "d:/SmartExtTemplate", 
    "extTemplateVariableConfigPath":"d:/SmartExtTemplate/extVariable.json" 
}
```



### 自定义修改模板注意事项

SmartDoc原模板文件有的是unix编码 ，注意修改模板文件的时候建议使用notepad++这类文件编辑器打开编辑。

不要使用其他编辑器编辑，否则会出现模板文件无法解析的情况。
