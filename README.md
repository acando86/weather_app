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





# !!TODO

Lot of things. From the point of view of implementation, the following:
* **offline/cache support**. Ideally, this should be provided in the "data>local" package (currently empty)
* more test coverage (given the time costrains tests have been provided to give an idea of what can be tested and how, but several scenarios are not covered at the moment; also, automation tests are missing)
* **screen rotation**: i'm not exactly a fan of screen rotations, although it;s 100% true that any app should properly handle screen rotation, and this is an important point especially for end users of the app, I have always thought that from the point of view of development screen rotation in android is more a technical requirement or trick than a real feature: as a developer I would like to focus first and more on business requirements than screen rotation
* **UI/UX** design and experience.



