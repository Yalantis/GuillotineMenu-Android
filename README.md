# Guillotine animation

Neat library, that provides a simple way to implement guillotine-styled animation

[![Yalantis](https://raw.githubusercontent.com/Yalantis/GuillotineMenu-Android/master/made-in-yalantis.png)](https://yalantis.com/?utm_source=github)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-GuillotineMenu-green.svg?style=flat)](https://android-arsenal.com/details/1/1995)

[![Android Gems](http://www.android-gems.com/badge/Yalantis/GuillotineMenu-Android.svg?branch=master)](http://www.android-gems.com/lib/Yalantis/GuillotineMenu-Android)

Check this [project on Dribbble] (https://dribbble.com/shots/2018249-Guillotine-Menu)

Also, read how it was done in [our blog] (https://yalantis.com/blog/how-we-developed-the-guillotine-menu-animation-for-android/?utm_source=github)

<img src="https://d13yacurqjgara.cloudfront.net/users/495792/screenshots/2113314/draft-03.gif" alt="Guillotine animation gif" style="width:800;height:600">


# Usage

*For a working implementation, have a look at the app module*

1. Add JitPack repository in your root build.gradle at the end of repositories:

    ~~~
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }

    ~~~

2. Add the dependency to your app build.gradle

    ~~~
    dependencies {
        compile 'com.github.Yalantis:GuillotineMenu-Android:1.2'
    }
    ~~~

3. You need to create a layout for the navigation menu (`guillotine.xml` in sample app), which will later open and close guillotine-style. The only tricky part here is that the navigation layout should be on top of any other content and will disappear after closing animation ends. That is why content layout (`activity.xml` in sample app) should also have hamburger icon at the same coordinates as navigation menu has.

4. After that all you need to do is to build animation by passing navigation layout object, navigation and content layout hamburger objects to `GuillotineAnimation.GuillotineBuilder` in your `onCreate` method

	```java
    new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setActionBarViewForAnimation(toolbar)
                .build();
     ```
Here `setActionBarViewForAnimation` method enables bounce effect of Toolbar at the end of the guillotine closing animation.

# Misc

Builder allows you to customize start delay, duration, interpolation and you can set listener if you want to do staff at the moment when menu has been opened or closed.

# Compatibility
  
  * Android 4.0.3 Ice Cream Sandwich (API level 15)
  
# Changelog

### Version: 1.0

  * Initial Build
  
### Version: 1.2
  
  * Moved to using Toolbar instead of ActionBar
  * Updated Gradle versions
  * Fixed bugs

#### Let us know!

We’d be really happy if you sent us links to your projects where you use our component. Just send an email to github@yalantis.com And do let us know if you have any questions or suggestion regarding the animation. 

P.S. We’re going to publish more awesomeness wrapped in code and a tutorial on how to make UI for Android (iOS) better than better. Stay tuned!

## License

    Copyright 2017, Yalantis

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
