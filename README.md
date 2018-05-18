

  
Mobile Development - Android Studio
==

**Head Lecturer & Tutor** </br>
[Amnon Dekel](https://scholar.google.com/citations?user=5lTmLKsAAAAJ&hl=en)</br>
[Amir Uval](https://github.com/auval)

**Contributers** </br>
[Shay Rubach](https://github.com/ShayRubach)</br>


## Useful links & tools
- [StackEdit.io - Edit ReadMe online](https://stackedit.io/app#)
- [Android Developers Guides](https://developer.android.com)
- [StackOverflow ✔](https://stackoverflow.com)
- [How to create a Multiple Row ✌️ Recycler View  ](http://www.coderconsole.com/2015/10/android-multiple-row-layout-using.html)
- [Room Persistent Database]()
- [CodeLabs Room Tutorial](https://codelabs.developers.google.com/codelabs/android-persistence/#0)
- [Joda Time Library](http://www.joda.org/joda-time/)
- [DebugDB](https://github.com/amitshekhariitbhu/Android-Debug-Database) - <br>![debugdb](https://user-images.githubusercontent.com/21342315/39724491-35e01d1a-5249-11e8-92f2-2fa571ad088e.png)
This tool lets you see your DB information & perform queries on a localhost Web UI. <br>**note:** While configuring this tool, if your terminal doesn't recognize 'adb', you can either use its full path (usually on windows inside c:/users/AppData/android/platform-tools/adb.exe), or add an [_Environment Variable for 'adb'_](https://lifehacker.com/the-easiest-way-to-install-androids-adb-and-fastboot-to-1586992378) - recommanded.

### Tools ![tools](https://user-images.githubusercontent.com/21342315/39400544-98a9ba32-4b32-11e8-8e17-10e5a6d16df8.png)
  
- Android Studio 3.1.2
- Nexus 5S [Emulator - Virtual Device]
- Windows 7

## My First Mobile Application

1. Create a "MainMenuActivity" to hold the entire course assignments for each line on the list.
2. Inflate the clicked activity <br>
Detailed instructions can be found [here on Amir Uval's page](https://github.com/auval/AndroidWorkshop)

### Activity #0 - MainMenuActivity

![mainMenuActivity](https://user-images.githubusercontent.com/21342315/39399057-7a89948e-4b17-11e8-9871-e274ca257b9b.png) 
<br/>This activity containts a [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview) that holds a list of [Tasks](https://github.com/ShayRubach/MobileDevelopment/blob/master/app/src/main/java/com/pwnz/www/mobileapplicaiton/model/Task.java). Each task has its corresponding [task_list_row](https://github.com/ShayRubach/MobileDevelopment/blob/master/app/src/main/res/layout/task_list_row.xml) layout. In our case, they all share the same one. The layout consists of icon, title, id and due date.
- To launch the desired activity, simply click it.
<br/>

---

### Activity #1 - Simple Calculator Activity

#### How to use the app? <br/>
- Pretty much trivial


![simpleCalculator](https://user-images.githubusercontent.com/21342315/39399139-d02e471c-4b18-11e8-832e-2163dd254dc3.png)


### Activity #2 - Birthday List Activity
**Worth reading (!) application error I recieved during development**:<br>  ["the application may be doing too much work on its main thread"](https://stackoverflow.com/questions/14678593/the-application-may-be-doing-too-much-work-on-its-main-thread) 


- Added semi-cool random avatar choice logic everytime a new birhday is added, just for a better look.
- _**TODO**_: choose avatar from a gallery. <br>

Application flow:
---
![bdaylist](https://user-images.githubusercontent.com/21342315/39721545-30e8220c-5240-11e8-94ff-cd2de356f2bc.png) ![addbday](https://user-images.githubusercontent.com/21342315/39721504-0ee39f38-5240-11e8-8219-d7100860acfd.png) ![dueshbag](https://user-images.githubusercontent.com/21342315/39721614-6171d9d6-5240-11e8-9c6c-b338fc5faa0c.png)

### Activity #3 - Low Level Graphics Game
I introduce to you, the '***Annoying Rainbow Cats***' - a game with no specific goal.
This game is basically an annoying introduction to the famous old 8bit [Nyan Cat](http://www.nyan.cat/).<br>
![arc_splash](https://user-images.githubusercontent.com/21342315/40210289-67202142-5a44-11e8-841c-ebed8b0cdc65.png)![bigalone](https://user-images.githubusercontent.com/21342315/40244654-b360959e-5ac3-11e8-860e-0013c8152eaf.png)
![roam](https://user-images.githubusercontent.com/21342315/40244625-9e66845a-5ac3-11e8-8d94-3b02fd47326a.png)![hit](https://user-images.githubusercontent.com/21342315/40244566-7841b434-5ac3-11e8-8975-42f4fc903b9b.png)

