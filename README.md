# Posts
Simple posts app using a test REST API.

## Architecture
I went with a mixture for this implementation: Clean Architecture to handle the data layer and MVVM to handle the presentation layer. I strongly believe we 
should leave the data layer as far as we can from the presentation layer, which I like to design the dumbest possible.
Clean Architecture allows to encapsulate the whole layer by exposing only what's strictly necessary to the presentation layer to show by using `interactor`s 
or `use case`s.

The MVVM implementation features some of the most up to date Jetpack libraries there is, including: [Room](https://developer.android.com/jetpack/androidx/releases/room), 
[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) and the new [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines)
 framework alongside [Flow](https://developer.android.com/kotlin/flow) (which is not part of the Jetpack libraries but works well with them.)
 
 ## Reasons of choosing each library
 -  **Dagger2**: provides a mechanism to fill your dependencies wherever you need them, test cases not being the exception.
 - **Room**: this was the easies to choose. Room is the most popular database implementation for Android and I chose it due to it's scalability and easinessto use. This project employs a Room database to handle the favorites section alongside Flow so I could handle all table changes seamlessly. Of course Flow & Room by themselves don't achieve this, so in order to do that, I followed the unique source of truth principle to successfully listen for table changes.
 - **LiveData**: LiveData allows you to listen to data changes for a more reactive app, making use of the [Observer](https://en.wikipedia.org/wiki/Observer_pattern) design pattern.
 - **Coroutines**: Kotlin Coroutines are profiling to become RxJava's successor. They allow you to write async, clean & sequential code to handle processess that need to run on separate threads. This becomes handy in a Clean Architecture implementation since the use cases are called from the ViewModel, and the ViewModel possesses a scope for the Coroutines to run.
 - **Flow**: some people say Flow will replace LiveData eventually and I think they're right. Based on promises, Flow allows you to get data by suspending execution and returning with whatever you asked. In this particular implementation, Flow was crucial to achieve a reactive behavior when selecting items from the favorites table.
 - **Retrofit**: Android's most popular REST Api consuming library is designed to perform requests in separate threads. With native coroutines support, calling endpoints has never been easier (when working alongside coroutines, because if you go with a different approach, you won't be needing the suspend operators.)
