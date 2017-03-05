# mockstar
Demo project for using MockWebServer for unit tests. This project also hints on mocking out views while testing using `Mockito` and focuses on the usage of `MockWebServer` for writing tests.

 
## Using `MockWebServer` for writing unit tests.
 
 Most of the demos out there make use of `MockWebServer` for writing `androidTest`s and adding custom `JUnitTestRunner`s and are more focused on integration testing. Nowadays, MVP architecture is getting adopted by many android developers and we like our `logic` layer to be tested properly. MVP enables proper testing of the logic layer via unit tests and separates it from the pains of testing the android UI. While testing, we should not hit the actual web server. If we hit actual server while testing, then the tests become flaky as they depend upon the actual network availability. This project shows how we can write unit tests for our `Presenter`s / logic easily. It also shows how we can test certain error conditions properly with the use of `MockWebServer` which becomes as easy as hitting the actual remote server.
 
 Project implements the following :
 
 - Uses [RxJava](https://github.com/ReactiveX/RxJava) for the demos.
 - Follows the `MVP` architecture inspired by [repository](https://github.com/googlesamples/android-architecture).
 - Makes use of [`Dagger2`](https://google.github.io/dagger/)
 - Uses [`Retrofit2`](https://github.com/square/retrofit) to fetch from the [Pokemon API](http://www.pokeapi.co/docsv2/#pokemon)
 - Uses [Butterknife](http://jakewharton.github.io/butterknife/)
 - Uses `TestScheduler` for writing tests.
 - Also uses [`Mockito`](http://site.mockito.org/) for mocking out the `View` for now.
 - This project makes use of [`MockWebServer`](https://github.com/square/okhttp/tree/master/mockwebserver)
 
## Implementation
 This project has a separate module called [`mocks`](https://github.com/ragdroid/mockstar/tree/master/mocks). Our android application module `app` has a `testCompile` dependency on the `mocks` module. Also, created a [`LocalResponseDispatcher`](https://github.com/ragdroid/mockstar/blob/master/app/src/main/java/com/ragdroid/mockstar/api/LocalResponseDispatcher.java) which takes care of the local API needs.
 To run tests :
 `./gradlew clean test`

## To-Do
  - Currently the server call is written inside the `Presenter`, so we are testing our [`MainPresenterImpl`](https://github.com/ragdroid/mockstar/blob/master/app/src/test/java/com/ragdroid/mockstar/MainPresenterImplTest.java). Demonstrate a more complex case and extend the demo to test the model. 
  - More Documentation.
