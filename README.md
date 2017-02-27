# Sliding Tab With Color Icons
Sliding Tab With Color Icons!

 ![Sliding Tab With Color Icons - Example1](https://raw.githubusercontent.com/myinnos/SlidingTabWithColorIcons/master/gif/CustomTabLayout_01.gif)
 `` `` `` `` `` `` `` ``
  ![Sliding Tab With Color Icons - Example2](https://raw.githubusercontent.com/myinnos/SlidingTabWithColorIcons/master/gif/CustomTabLayout_02.gif)
  
#### Kindly use the following links to use this library:

In build.gradle (Project)

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
	
And then in the other gradle file(may be your app gradle or your own module library gradle, but never add in both of them to avoid conflict.)
	
	 dependencies {
	        compile 'com.github.myinnos:SlidingTabWithColorIcons:1.0'
	        }

How to use
-----
**Step 1:** create layout:
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tab="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <in.myinnos.customimagetablayout.ChangeColorTab
        android:id="@+id/tabChangeColorTab"
        android:layout_width="match_parent"
        android:layout_height="60dp">

        <in.myinnos.customimagetablayout.ChangeColorItem
            android:layout_width="0dp"
            android:layout_height="fill_parent"
            android:layout_weight="1"
            android:padding="5dp"
            tab:item_icon="@drawable/ic_one"
            tab:item_icon_color="#ff45c01a"
            tab:item_tab_color="@color/colorPrimary"
            tab:item_tab_position="bottom"
            tab:item_text="message"
            tab:item_text_color="@color/colorPrimaryDark"
            tab:item_text_size="12sp" />
	    
	    ...

    </in.myinnos.customimagetablayout.ChangeColorTab>

    <android.support.v4.view.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/tabChangeColorTab" />
</RelativeLayout>
```
**Step 2:** set view pager to ChangeColorTab:
```
ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
ChangeColorTab changeColorTab = (ChangeColorTab) findViewById(R.id.tabChangeColorTab);
changeColorTab.setViewpager(mViewPager);
//Attach page Adapter
mViewPager.setAdapter(<YOUR_VIEW_PAGE_ADAPTER>);
```
Features
-----
- Set Tab Icon:
```xml
 tab:item_icon="@drawable/ic_one"
```
- Change Icon Color:
```xml
 tab:item_icon_color="#ff45c01a"
```
- Change Tab Color:
```xml
 tab:item_tab_color="@color/colorPrimary"
```
- Change Tab Line Position (bottom|top):
```xml
 tab:item_tab_position="bottom"
```
- Set Text (no need text simply skip this):
```xml
 tab:item_text="message"
```
- Change Text Color:
```xml
 tab:item_text_color="@color/colorPrimaryDark"
```
- Change Text Size:
```xml
 tab:item_text_size="12sp"
```

##### Any Queries? or Feedback, please let me know by opening a [new issue](https://github.com/myinnos/SlidingTabWithColorIcons/issues/new)!

## Contact
#### Prabhakar Thota
* :globe_with_meridians: Website: [myinnos.in](http://www.myinnos.in "Prabhakar Thota")
* :email: e-mail: contact@myinnos.in
* :mag_right: LinkedIn: [PrabhakarThota](https://www.linkedin.com/in/prabhakarthota "Prabhakar Thota on LinkedIn")
* :thumbsup: Twitter: [@myinnos](https://twitter.com/myinnos "Prabhakar Thota on twitter")    

License
-------

    Copyright 2017 MyInnos

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
