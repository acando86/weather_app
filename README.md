# weather_app
Weather App

Basic app based on OpenWeather APIs that shows weather forecasting for the upcoming 5 days

# Architecture

The code is strcutured into the following packages:
```
  └── weatherapp
           ├── BaseApplication.java
           ├── Constants.java
           ├── data
           │   ├── local
           │   └── remote
           │   
           ├── di
           │   ├── component
           │   └── module
           ├── domain
           │   
           ├── helper
           │   
           └── presentation
               ├── base
               │   
               └── home
```

It's a 3-tier architecture with
* **data layer**: responsible of providing the data. Ideally this should offer multiple data sources, both remotely (network requests) and locally (share pref, files, database, etc for caching and offline support). Currently, only the network request is supported
* **domain/business layer**: presenters of the presentation layer should interact with the data layer via repositories provided in this package, to allow separation of concerns, decouple network/database models from view models and provide mapping/filters/chain of network request etc when needed
* **presentation layer**: responsibile of handling the UI. Our presentation layer is organised by user cases (eg, `home`) and it follows MVP pattern. Basic classes to handle the view-presenter binding are provided in the `base` sub-package

Regarding MVP, every user case has
* a contract class, providing View and Presenter interfaces; this provides the way view and provider can communicate each other by exposing interface methods
* activity (in principle, also fragments or android views in a more complex app) implementing the view interface in the contract; views are responsible of creating the corresponding presenter, propagate to the presenter lifecycle events, click events, etc. 
* presenter Java class, that handles all the business logic of the view. Ideally, no Android code should appear in the presenter so that we can completely test the presenter with unit tests 
* model classes: the model storing data for the view. this should not be confused with the pojo classes in the network layer
* additional android classes (adapters, etc)

# Libraries

* Dagger2 : dependency injection framework (does not use reflection). While its surely true that dependecy injection can be achieve without using any DI framework, dagger2 makes things easier without performance penalities at runtime. For example, it's easy with dagger to expose scheduler threads and replace them for unit tests. 
* RxJava and related: implementation of the observable pattern with a lot of built in support for map/filter, etc.. Its dotchain syntax (together with retrolambda, not used in this project) brings a flavour of functional programming "style" in the android platform, alleviating certain "callback hells" that can appear when handling multiple data sources, multiple scenarios, etc and that otherwise would be handled using a lot of boolean flags difficult to manage. 
* retrofit2: static-type REST client, useful to provide clean interface to APIs; it also supports rx out of the box
* some json decoder library. in this project, i decided to play with "logansquare" which is a fast json decoder/serialiser. gson and jackson are other popular choices with many features. 
* butterknife: just to remove some boilerplate of findViewById code


# Run app and tests

Clone the project
Open the project in Android Studio
The app can be built and run using Build > Build APK and installing the resulting APK file on the device
To run the unit tests, just right click on the test folder and select "Run" in the available options

(!) Warning: android 2.2 is required
apt command  from the android-apt plugin was used in android studio < 2.2 for dagger2/butterknife
Add plugin: 'com.neenbedankt.android-apt' and replace annotationProcessor as appropriate if you are running an older version.
Further information here: https://github.com/bluelinelabs/LoganSquare

E2E functional and automation tests have not been provided. unit tests covered some of the basic scenarios. 

## Command-line gradle

To use command-line gradle 
```
gradle assembleDebug
```
or
```
gradle assembleRelease
```

to create apk for debug or release version respectively. 

# Additional notes

** API KEY**
My personal openweather API key has been used for this project and, in order to make the project self-contained, has been pushed and is visible inside the code. This in a production environment is of course a security fault. The API key should never been uploaded on the resporitory (especially a public one) and it would be better to store it in a file that is not committed. 

** Proguard **
Release version of the app uses proguard
# !!TODO (a lot)

Lot of things. From the point of view of implementation, the following:
* **offline/cache support**. Ideally, this should be provided in the "data>local" package (currently empty)
* **improve test coverage** (given the time costrains tests have been provided to give an idea of what can be tested and how, but several scenarios are not covered at the moment; also, automation tests are missing)
* **screen rotation**: i'm not exactly a fan of screen rotations, although it;s 100% true that any app should properly handle screen rotation, and this is an important point especially for end users of the app, I have always thought that from the point of view of development screen rotation in android is more a technical requirement or trick than a real feature: as a developer I would like to focus first and more on business requirements than screen rotation
* **CI/CD pipeline**: when someone try to push on pre or master branch, this should trigger the executions of the test suite. I have had not itme to setup one yet but it's important to setup it in the early stages of the project, to avoid (as I might have done during this project) to commit in the history some code that breaks the tests 
* **UI/UX** design and experience.



