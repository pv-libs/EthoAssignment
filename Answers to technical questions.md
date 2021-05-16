# Answers to technical questions

#### 1. How long did you spend on the coding test? What would you add to your solution if you had more time?
 - I spent around 12 hours on this Assignment
 - If i has more time, i could improve the UI.

#### 2. What was the most useful feature that was added to the latest version of your chosen language?
 - ``StateFlow``, ``SharedFlow`` are one the new features added to Kotlin Coroutines recently. 
 ```
  @Singleton
class MockConnection @Inject constructor() {
    private val serverEventFlow = MutableSharedFlow<Event>()
    private val clientEventFlow = MutableSharedFlow<Event>()
    
    ...
}
 ```
 
  - ``sealed class`` is an another relativly new feature of Kotlin, i used
  ```
sealed class ServerEvent : Event()
sealed class ClientEvent : Event()
  ```
  
  #### 3. How would you track down a performance issue in a communication system in production?
   - To track down performance issues in production, I would try to integrate FirebaseAnalytics and record all the important metrics as Events. Using those events we can create Funnels/User-Flow to track performance.
  
