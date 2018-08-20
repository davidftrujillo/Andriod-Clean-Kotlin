# XMOBA

## Clean architecture
Taking into account the requirements of the project and the environment in which the different applications of the company will coexist, I have decided to apply an implementation of **Clean architecture** with 3 layers (data, domain and presentation).
To decide the organization of the code, I have thought about different solutions:
- To have and unique module and separate into different packages every layer.
- To have 3 different modules, one per layer.
- To have one module for every "feature" of the application.

I have finally made the decision to use the second option, for the following reasons:
- Having an isolated module for all data access (via network request or local storage) will allow us to reuse the entire module (which is fully developed and tested) in future applications within the company. For example, thinking in a group of applications that can access to same data (list of users in this case), we should only implement the presentation module, since we can reuse the other modules.
- Presentation layer is fully decoupled from the business logic (domain module) and data access layer (data module). 
- We have different models objects for every layer, since the model we recover from network can be totally different from the data we need to show in the view (I use a mapper for every entity in the project when I need to pass any object through layers, to adapt/format the data at convenience.
- It is more easy to test every layer in an isolated way.

The third options can be useful if we had to implement a Single sign on system to allow a user to access different applications with the same credentials, the solution could be redesigned to be modularized by feature, where the registration and login functionality could be shared between applications.

### Domain
Contains all the business logic of the application, represented as Use Cases. A use case represents an individual action a user can do, like get the list of users or find a user by its email, for example. Usually, an use case needs to do something with data, so the way to communicate with the data module is through an interface that represents all the actions we can do. This way, we work with an abstraction, and we are independent of the implementation details, or the source of the data we are working with.

### Data
Contains the models as are received from the network. It's in charge of calling the corresponding endpoint, parse the response, save it into local database (if corresponds) and propagate it to domain layer. It also has all the logic to get data from local database and SharedPreferences as well.
This module also has the implementation of the interface from the domain layer.
This project works with twi different data sources:
- Network
- Local database

For every action (e.g. get the list of users), we have to decide from which data source we'll get the data applying some policy. In this case, every time the application requests some data from network, it will save the response in local database, and this data will be valid for the next 5 minutes. That is, if the same request is executed within the next 5 minutes, the data will come from database, there won't be a network request.

### Presentation - MVP
For the presentation layer I have decided to use MVP (Movel View Presenter) in order to organize and structure as well as possible this layer. In the picture below, the Interactor blocks corresponds to the domain layer, and the REST API and Database API, to the data layer.
![MVP](https://cdn-images-1.medium.com/max/1600/0*4E8U5YuG22bLp4h8. "MVP")

In my implementation, every feature (list and detail) has its own fragment (view) and presenter. Every time the user interact with the view (click on cell row from the list, or on a button), the corresponding method on the presenter is called. This way, we avoid executing any logic in the view, which will only take care of painting its current state at every moment. The presenter is in charge of executing any needed operation. Usually, it will execute an use case, do something with the data received (if necessary), and tell the view to update itself, show some feedback to the user, etc.

## Libraries
I always try to use as less third party libraries as possible. In this case I have use these ones.
- Android Support libraries: RecyclerView, CardView, Design and ConstraintLayout for the layouts.
- [Retrofit](https://square.github.io/retrofit/ "Retrofit") and [OkHttp](http://square.github.io/okhttp/ "OkHttp") for the network requests.
- [Dagger2](https://google.github.io/dagger/ "Dagger2") for dependency injection. It has a very long learning curve, but once it's under control it facilitates the development process, as well as testing. It is a big help to respect the dependency inversion principle.
- [RxJava2](https://github.com/ReactiveX/RxJava "RxJava2"). Since this is not totally required due to the scope of the project, I have decided to integrate it in order to demonstrate how it can be used inside an Android project.
- [Room](https://developer.android.com/topic/libraries/architecture/room "Room"). Part of the Android Architecture Components from Google. In the project it's used to persist the data locally.
- [Picasso](http://square.github.io/picasso/ "Picasso") to load images asynchronously in an easy way.
- [MockitoKotlin](https://github.com/nhaarman/mockito-kotlin "MockitoKotlin") to facilitate the unit tests in Kotlin.
