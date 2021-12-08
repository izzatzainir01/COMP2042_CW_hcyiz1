# Brick Destroy
## Description
This is a simple arcade game based on the classic game, Breakout, and is an extension of [Filippo Ranza's](https://github.com/FilippoRanza/Brick_Destroy)  original implementation of the game. This is my coursework for COMP2042 Developing Maintainable Software in the University of Nottingham Malaysia Campus.

### Tested on:
 - Windows 10
 - Java JDK 17
 - Maven 3.6.3

## Changes
### General Refactoring
 - Removed unused and/or redundant variables, imports and methods
   throughout all the classes.
 - Rearranged some methods and/or variables in a particular order to
   make it more readable personally.
 - Added comments everywhere to help with readability and ease of
   knowing what something does.
 - Reorganised the classes into packages and sub-packages.
 - Renamed classes/methods/variables into names that make more sense personally.
 - Encapsulated most of the variables throughout all classes, with the
   exception of constants.
   
### Class Separation
Separated some large classes into smaller ones. A notable class that received this treatment is the Crack class, which was separated from Brick. This was done in order to allow for any future additions to easily access the Crack class as well as to reduce bloat in the Brick class.

Another notable separation is the Levels class, which used to be some methods in the Wall (now Game) class for creating levels.

### Design Patterns
Attempted to implement the MVC (Model-View-Controller) design pattern to the project. A lot of classes were added as a result of this, notably View classes. Another notable design pattern that I attempted to implement is the Factory design pattern, which I implemented on the Brick classes.

### Game Changes
Made it so that when the user loses a level, they'd have to start from the very beginning, rather than from the same level. The same goes for when the user restarts the game via the pause screen, the winning screen or the losing screen.

Implemented a score system that keeps track of the user's username and total score throughout the game and saves them into a .csv file upon leaving the game. The score is reduced every time the ball is lost. To make this work, I managed to write a basic CSV API that eased this process.

Other minor changes include making the Ball and Player move faster as the original game's speed was painfully slow. Besides that, I improved the collision system which made the bounces feel a bit smoother. I also made the Ball randomise its angle of travel every time it collides with a Brick to promote unpredictability, which makes the game much more fun personally.

### Cosmetic Changes
Added many new Views to the game in addition to improving the existing Views in the original project. Some notable new Views are the following:
 - Info Views
 - High Score list
 - Username prompt
 - Game End Views (cleared a round, all rounds, or lost the game)

### Other Changes
 - Used Maven build tools
 - Did some JUnit tests
 - Added Javadocs
