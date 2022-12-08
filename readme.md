# PokeDex MVVM

PokeDex MVVM is an Android app that demonstrates the implementation of PokeAPI.co using Kotlin, MVVM, and Clean Architecture Multi Module. The app has a Homepage and a Detail Page. On the Homepage, the user can see a list of Pokemon and can search for them by name or change the ID sorting from ascending to descending. On the Detail Page, the user can see details of a Pokemon, including its name, stats, description, type, and evolution.

The app uses components such as Jetpack Paging 3, Hilt for Dependency Injection, OKHttp + Retrofit, Glide, DiffUtil, Coroutines, and more. It also includes unit test examples using JUnit.

### Components
- MVVM: MVVM stands for Model-View-ViewModel, and is a design pattern that separates the data model, user interface, and business logic of an app into distinct components. This makes it easier to test and maintain the app.

- Dependency Injection: Dependency injection is a design pattern that allows for the separation of concerns in an app by allowing objects to be passed to other objects, rather than creating them directly. This makes it easier to test and maintain the app.

- Jetpack Paging 3: This library provides a way to load and display large datasets in your app without having to load the entire dataset into memory at once. This allows your app to use less memory and be more performant.

- Hilt: Hilt is a dependency injection library that makes it easier to manage dependencies in your app. It uses annotations to automatically generate code that injects dependencies into your classes, reducing the amount of boilerplate code you need to write.

- OKHttp + Retrofit: These libraries are used for networking in this app. OKHttp is a popular HTTP client that can be used to make network requests, and Retrofit is a library that makes it easier to use OKHttp to make RESTful API calls.

- Glide: Glide is an image loading library that makes it easier to display images in this app. It can load images from a variety of sources, including URLs, and has built-in support for caching and placeholder images.

- DiffUtil: DiffUtil is a utility class that can be used to calculate the differences between two lists of data. This is useful when you need to update a RecyclerView with new data but only want to update the parts of the list that have changed.

- Coroutines: Coroutines are a way to write asynchronous, non-blocking code in a more readable and concise way. They can be used to perform long-running tasks, such as network requests, in the background without blocking the main thread of your app.

### Clean Architecture Multi Module
Clean Architecture is a software design pattern that separates the different concerns of an app into distinct layers. This makes it easier to maintain and test the app, and also allows for more modular and reusable code.

In Clean Architecture Multi Module, the app is split into multiple modules, each with a specific concern. For example, one module might contain the business logic of the app, while another might contain the user interface code. This allows for a more modular and scalable app.
