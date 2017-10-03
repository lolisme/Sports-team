# Search for sports teams app

Android app that leverages the [the sports db api](http://www.thesportsdb.com/) to display players from different teams worldwide.

> The app is composed of several activities, some of them being: 
+ MainActivity: the apps landing page where users are able to search for any teams of their choice.
+ Search Results Activity: here, relevant search results are displayed, having being retrieved successfully from theSportsDb API.
+ User Sign In
+ User Sign Up

## The sign in and sign up as seen on the app.

sign in                    |           Sign up
:-------------------------:|:-------------------------:
![image sign up](https://i.imgur.com/Yqja7yG.png)  |  ![image sign in](https://i.imgur.com/Aq5QEDM.png)

## Overview

The app does the following:

1. Search a sports team using the [the sports db api](http://www.thesportsdb.com/)
2. Display a list of the team members.
3. Replace ActionBar with an ActionView search Bar
4. Use SearchView to search for Teams.
5. Show ProgressBar during the user Authentication activities.
6. Add a detail view to display more information about the selected player from the results list
7. Includes touch gestures such as swipe and drag.


## Libraries

This app leverages two third-party libraries:

 * [Firebase](firebase.com) - For user authentication and no-sql data storage.
 * [Picasso](http://square.github.io/picasso/) - For remote image loading
