# NotificationProvider
A simple Notification Library for displaying notifications in Android. After Android O, notifications require channels to be displayed and this update makes the 
notification support for different versions of android troublesome. This library handles backend settings and creation of channels in the background and provide a
chained constructor method to display notifications in a single line of code.

## About 
    This library is developed by Jerry S Joseph as an Open-Source project. 

## How to Include in your project

Include in build.gradle (project level)

```
allprojects {
    repositories {
        ....
        maven { url 'https://jitpack.io' }
    }
}
```

Include in build.gradle (app level)
```
dependencies {
    ...
    //Notification Provider Library
    implementation 'com.github.JerrySJoseph.NotificationProvider:NotificationProviderModule:1.0.0'  //Change the version to the current latest release
}
```

## Usage

Notification Provider Library uses chained constructor to display simple notifications

### Simple notification
```java
new NotificationProvider(this)
                .setTitle("Notification Title")               //Required
                .setBody("Hi from Notification Provider")      //Required
                .setAutocancel(true)                          
                .setIntent(this.getIntent())                  //Routes to intent when clicked on Notification 
                .setNotificationIcon(R.mipmap.ic_launcher)
                .setChannelID("Channel_id")
                .setChannelName("Channel_name")
                .show();

```
### Expandable notification
Expandable notification supports large image, large text, message and inbox. Declare the desired type as 2nd parameter in the constructor as shown below.
```java
 new ExpandedNotificationProvider(this, ExpandedNotificationProvider.ExpandedNotificationType.INBOX).
                setTitle("This is a notification").
                setNotificationIcon(R.mipmap.ic_launcher).
                setIntent(this.getIntent()).
                setAutocancel(false).
                addMessages(new NotificationCompat.MessagingStyle.Message("Hello",System.currentTimeMillis(),"Jerin")).
                addMessages(new NotificationCompat.MessagingStyle.Message("Hi there",System.currentTimeMillis(),"Sundar")).
                addMessages(new NotificationCompat.MessagingStyle.Message("How are you?",System.currentTimeMillis(),"Jerin")).
                addMessages(new NotificationCompat.MessagingStyle.Message("I am fine bro. wbu?",System.currentTimeMillis(),"Sundar")).
                addMessages(new NotificationCompat.MessagingStyle.Message("U know me.. I am fine too",System.currentTimeMillis(),"Jerin")).
                setBigText("Apply NotificationCompat.InboxStyle to a notification if you want to add multiple short summary lines, " +
                        "such as snippets from incoming emails. This allows you to add multiple pieces of content text that are " +
                        "each truncated to one line, instead of one continuous line of text provided by NotificationCompat.BigTextStyle.").
                addLines("This is a line").
                addLines("This is a line").
                addLines("This is a line").
                addLines("This is a line").
                addLines("This is a line").
                addActionButton(new NotificationCompat.Action(R.drawable.ic_reply,"Reply",resultPendingIntent)).
                addActionButton(new NotificationCompat.Action(R.drawable.ic_reply,"Reply",resultPendingIntent)).
                addActionButton(new NotificationCompat.Action(R.drawable.ic_reply,"Reply",resultPendingIntent)).
                setBody("Test").
                setLargeIcon(img).
                setImage(img).
                show();

```
This example includes every available setter of this module. Use as your requirement.

```java
public enum ExpandedNotificationType{
        BIG_PICTURE,
        BIG_TEXT,
        MESSAGE,
        INBOX
    }
```
These are the avialable type of expanded notification. Make sure to set one of these as 2nd property to avoid any errors.

License
-------

    Copyright 2014 - 2021 Jerin Sebastian

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
