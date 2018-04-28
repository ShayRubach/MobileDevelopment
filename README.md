  
Methods In Software Engineering
==

**Head Lecturer & Tutor**
[Amnon Dekel](https://scholar.google.com/citations?user=5lTmLKsAAAAJ&hl=en)
[Amir Uval](https://github.com/auval) 


**Contributers**
[Shay Rubach](https://github.com/ShayRubach)
[Google <3](https://google.com)


## Useful links & tools
- [StackEdit.io - Edit README online](https://stackedit.io/app#)
- [Android Developers Guides](https://developer.android.com)
- [StackOverflow <3](https://stackoverflow.com)

### Tools
- Android Studio 3.1.2
- Samsung S3, Nexus 5S [Virtual Device]

Detailed instruction can be found [here on Amir Uval's page](https://github.com/auval/AndroidWorkshop)

## My First Mobile Application

1. Create a "MainMenuActivity" to hold the entire course assignments for each line on the list.
2. Inflate the clicked activity

### Activity #0 - MainMenuActivity

Detailed instruction can be found [here](https://github.com/auval/AndroidWorkshop)

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

#### How to use the app? <br/>
- Move with ↑ ↓ only to navigate between items
- Use Spacebar to check/uncheck an item from the list