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

1. Include the library as local library project.

2. Your hamburger on navigation menu must have exactly same coordinates as hamburger on ActionBar.

3. In your `onCreate` method you need to config and build animation with GuillotineAnimation.GuillotineBuilder

	```java
    new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
     ```
Here `setActionBarViewForAnimation` method enables bounce effect of ActionBar at the end of the guillotine closing animation.

# Misc

Builder allows you to customize start delay, duration, interpolation and you can set listener if you want to do staff at the moment when menu has been opened or closed.

# Compatibility
  
  * Android 4.0.3 Ice Cream Sandwich (API level 15)
  
# Changelog

### Version: 1.0

  * Initial Build
  
## License

    Copyright 2015, Yalantis

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
