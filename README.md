# CySharedElementTransition
A compat library of android shared-element transition for lower api.
一个为Android较低版本api提供Android共享元素转场动画的兼容库。

## ScreenShot
![easytransition](https://github.com/ausboyue/CySharedElementTransition/blob/master/screenshot/screenshot.gif) 

## Download from Gradle

Add to your root build.gradle:
```xml
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency:
```xml
dependencies {
    implementation 'com.github.ausboyue:CySharedElementTransition:1.0.0'
}
```

## Friendly Hints
**If you can't download it, maybe you have used the Google's repository that you can't connect to the Google server.Please open the proxy or top the target repository.As follows：**
```xml
allprojects {
    repositories {
        maven { url 'https://jitpack.io' } // target repository,be top
        jcenter()
        google() // Google's repository
    }
}
```
# Get Started

**1. ActivityA jump to ActivityB，when need use "SharedElementTransition":**

- ActivityA edit code as below:
``` java
    ElementTransition.startActivity(intent, activityA, view1, view2, view...);
```
- ActivityB edit code as below:
``` java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ......
        ElementTransition.runEnterAnim(activityB);
        ......
    }
```
**2. ActivityB back to ActivityA:**

- ActivityB edit code like this:
``` java
    @Override
    public void onBackPressed() {
        ElementTransition.runExitAnim(activityB);
    }
```

# Bugs Report

If you find any bug when using it, please contact [me](mailto:ausboyue@qq.com). Thanks for helping us making better.

# Author

Cheny - @[ausboyue on GitHub](https://github.com/ausboyue/), @[www.icheny.cn](http://www.icheny.cn)

# Donate 
If you think it's good......

<img src="http://media.icheny.cn/alipayAndwxPay.jpg" width="500" >

# Other

Please give me some time to update the documentation.

# Release note
## 1.0.0
 - release first version v1.0.0 
 - nothing now
