# Navigation Component

Cupcake app
=================================

This app contains an order flow for cupcakes with options for quantity, flavor, and pickup date.
The order details get displayed on an order summary screen and can be shared to another app to
send the order.


What have I learnt?
--------------
- Create a NavHost composable to define routes and screens in your app.
- Navigate between screens using a NavHostController.
 -Manipulate the back stack to navigate to previous screens.
- Use intents to share data with another app.
- Customize the AppBar, including the title and back button.


My Notes
---------------

Parts of the Navigation Component
The Navigation component has three main parts:

- **NavController:** Responsible for navigating between destinations—that is, the screens in your app.
- **NavGraph:** Maps composable destinations to navigate to.
- **NavHost:** Composable acting as a container for displaying the current destination of the NavGraph.
  
<p align="center">
<img  src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/3d935282-2cf2-402f-b88d-81e4ee85ce61"  width=300 height=300>
</p>

- **NavController:** An instance of the NavHostController class. You can use this object to navigate between screens, for example, by calling the navigate() method to navigate to another destination. You can obtain the NavHostController by calling rememberNavController() from a composable function.
- **StartDestination:** A string route defining the destination shown by default when the app first displays the NavHost. In the case of the Cupcake app, this should be the Start route.


<p align="center">
<img src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/7b547734-e7a9-4c24-9a56-6ff7f69e2734" width=400 height=170>
</p>

- **Route:** A string corresponding to the name of a route. This can be any unique string. You'll use the name property of the CupcakeScreen enum's constants.
- **Content:** Here you can call a composable that you want to display for the given route.


<p align="center">
<img src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/5da010ed-447a-4655-bf57-495e0abc67cc" width=450 height=65>
</p>

- The navigate method takes a single parameter: a String corresponding to a route defined in your NavHost. If the route matches one of the calls to composable() in the NavHost, the app then navigates to that screen.

<p align="center">
<img src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/55b4e75f-f341-4b77-b4f6-a3317dcc870d" width=600 height=65>
</p>

- **Route:** The string representing the route of the destination you want to navigate back to.
- **Inclusive:** A Boolean value that, if true, also pops (removes) the specified route. If false, popBackStack() will remove all destinations on top of—but not including—the start destination, leaving it as the topmost screen visible to the user.

