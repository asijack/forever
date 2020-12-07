# 规范
> 参考：https://www.jianshu.com/p/45c1675bec69#3-%E5%91%BD%E5%90%8D%E8%A7%84%E8%8C%83

## 1.目录结构
```xml
com
└── asijack
    └── app
        ├── App.java 定义 Application 类
        ├── Config.java 定义配置数据（常量）
        ├── base 基础组件
        ├── data 数据处理
        │   ├── DataManager.java 数据管理器，
        │   ├── local 来源于本地的数据，比如 SP，Database，File
        │   ├── model 定义 model（数据结构以及 getter/setter、compareTo、equals 等等，不含复杂操作）
        │   └── remote 来源于远端的数据
        ├── feature 功能
        │   ├── feature0 功能 0
        │   │   ├── feature0Activity.java
        │   │   ├── feature0Fragment.java
        │   │   ├── xxAdapter.java
        │   │   └── ... 其他 class
        │   └── ...其他功能
        ├── injection 依赖注入
        ├── util 工具类
        └── widget 小部件
    └── forever
    └── ...
```

## 2.颜色资源文件（color/）
专门存放颜色相关的资源文件。</br>
命名规则：``[类型_逻辑名称``]。</br>
例如：sel_btn_font.xml。</br>
颜色资源也可以放于 res/drawable/ 目录，引用时则用 @drawable 来引用，但不推荐这么做，最好还是把两者分开。

## 3.图片资源文件（drawable/ 和 mipmap/）
res/drawable/ 目录下放的是位图文件（.png、.9.png、.jpg、.gif）或编译为可绘制对象资源子类型的 XML 文件，</br>
res/mipmap/ 目录下放的是不同密度的启动图标，所以 res/mipmap/ 只用于存放启动图标，其余图片资源文件都应该放到 res/drawable/ 目录下。</br>
命名规则：``[类型{_模块名}_逻辑名称、类型{_模块名}_颜色``]。</br>
例如：btn_main_about.png	主页关于按键 类型_模块名_逻辑名称</br>
>注意：使用 Android Studio 的插件 SelectorChapek 可以快速生成 selector，前提是命名要规范。

## 4.布局资源文件（layout/）
命名规则：``[类型_模块名、类型{_模块名}_逻辑名称``]。</br>
例如：</br>
activity_main_head.xml	主窗体头部 类型_模块名_逻辑名称</br>
fragment_music_player.xml	音乐片段的播放器 类型_模块名_逻辑名称</br>

## 5.values 资源文件（values/）
values/ 资源文件下的文件都以 s 结尾，</br>
如 attrs.xml、colors.xml、dimens.xml，起作用的不是文件名称，而是 <resources> 标签下的各种标签，</br>
比如 <style> 决定样式，<color> 决定颜色，所以，可以把一个大的 xml 文件分割成多个小的文件</br>
>比如:可以有多个 style 文件，如 styles.xml、styles_home.xml、styles_item_details.xml、styles_forms.xml。

## 6.colors.xml
<color> 的 name 命名使用下划线命名法，在你的 colors.xml 文件中应该只是映射颜色的名称一个 ARGB 值，而没有其它的。</br>
不要使用它为不同的按钮来定义 ARGB 值。</br>
不建议：<color name="button_foreground">#FFFFFF</color>
正确做法： <color name="white"     >#FFFFFF</color>

## 7.dimens.xml
好的做法： <dimen name="font_22">22sp</dimen>

## 8.strings.xml
<string> 的 name 命名使用下划线命名法.</br>
采用以下规则：``[{模块名_}逻辑名称``]，这样方便同一个界面的所有 string 都放到一起，方便查找。</br>
例如：main_menu_about	主菜单按键文字

## 9.id 命名
命名规则：``[view缩写{_模块名}_逻辑名``]，</br>
例如： btn_main_search、btn_back。</br>
例如： btn_main_search、btn_back

## 10.类注释
```java
/**
 * <pre>
 *     author : Blankj
 *     e-mail : xxx@xx
 *     time   : 2017/03/07
 *     desc   : xxxx 描述
 *     version: 1.0
 * </pre>
 */
public class WelcomeActivity {
    ...
}
```

## 11.方法注释
```java
/**
 * bitmap 转 byteArr
 *
 * @param bitmap bitmap 对象
 * @param format 格式
 * @return 字节数组
 */
public static byte[] bitmap2Bytes(Bitmap bitmap, CompressFormat format) {
    if (bitmap == null) return null;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(format, 100, baos);
    return baos.toByteArray();
}
```
