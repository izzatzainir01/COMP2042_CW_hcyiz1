# Brick Destroy
## Description
This is a simple arcade game based on the classic game, Breakout, and is an extension of [Filippo Ranza's](https://github.com/FilippoRanza/Brick_Destroy)  original implementation of the game. This is my coursework for COMP2042 Developing Maintainable Software in the University of Nottingham Malaysia Campus.

### Tested on:
 - Windows 10
 - Java JDK 17
 - Maven 3.6.3

## Changes
### General Refactoring
 - Removed unused and/or redundant variables, imports and methods throughout all the classes.
 - Renamed classes/methods/variables into names that make more sense personally.
 - Encapsulated most of the variables throughout all classes, excluding constants.
 - Rearranged some methods and/or variables in a particular order to make it more readable personally.
 - Reduced the responsibilities of some classes by moving methods to other classes and/or creating new classes.
 - Added comments everywhere to help with readability.
 - Reorganised the classes into packages and sub-packages.
   
### Class Separation
 - Separated Crack from Brick to allow future additions with easier access to Crack and reduce bloat. 
 - Separated level creation from Game (formerly Wall) into a Levels class to reduce the Game's responsibilities.

### Design Patterns
 - Attempted to implement MVC (Model-View-Controller) design pattern to the project.
 - Implemented the Factory pattern on the Brick classes, resulting in a BrickFactory class.

### Game Changes
 - Implemented a `tick` system, where every `tick`, the game checks the states of the different elements to make decisions. The `tick` is called by the GameController in a loop. This system was originally in the GameBoard (now GameController); I simply moved it to the Game.
 - Implemented a score system, with different types of Bricks contributing different scores. The score is reduced every time the ball is lost.
 - Increased the speed of Player and Ball because the original speed was painfully slow.
 - Moved all collision checks except Player into Game and improved the collision system.
 - Randomised the Ball's travel angle after every impact with a Brick to promote unpredictability.
 - Made players restart from the first level rather than from the same level.

### Cosmetic Changes
 - Improved the original Views (Home Menu, Pause screen) and used JButtons instead of Rectangles.
 - Added Info Views accessible from Home Menu for the game's description and controls.
 - Added a High Score List View accessible from Home Menu that displays a ranking of the top 20 users and their scores.
 - Added a View that prompts the user for their username before the Game starts.
 - Added Views for when the game is stopped due to the player clearing a level, clearing all levels or loses the game.

### Other Changes
 - Abstracted a Controller class and some View classes with similar templates to avoid rewriting code.
 - Wrote some utility classes to help with repetitive code.
 - Implemented a static Timer to avoid creating a new instance of a Timer for every new game.
 - Used Maven build tools
 - Did some JUnit tests
 - Added Javadocs
