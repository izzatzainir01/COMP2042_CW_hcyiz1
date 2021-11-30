
# Brick Destroy
## Description
This is a simple arcade game based on the classic game, Brick Breaker. The objective of the game is to destroy all bricks with a ball. You have 3 attempts until you lose the game. After which, you have to restart from the first round.

Scores are counted by the amount of impacts that happen between the ball and a brick. Different types of bricks will contribute different scores. The score will be displayed after every round. If you complete all rounds, the score displayed will be your total score from all rounds. If you lose the game, it will display your total score up until you lost.

Credit goes to [Filippo Ranza](https://github.com/FilippoRanza/Brick_Destroy) for the original project.

## Controls
| Key | Description |
|--|--|
| SPACE | Start/stop the game |
| ESC | Pause the game |
| A | Move the paddle left
| D | Move the paddle right
| F1 | Open debug console

## Changes
### Refactoring
 - Restructured the project into packages and sub-packages.
 - Removed unused imports/variables/methods and renamed some variables/methods.
 - Added comments everywhere.
 - Encapsulated and isolated most of the classes from one another.
 - Separated large classes.
 - Improved on some of the game mechanics.
 - Implemented the MVC design pattern.

### Additions
 - Used Maven build tools.
 - Implemented a score system.
 - Changed the look of the Main Menu and Pause screen.
 - Added an Info section.
 - Added displays for when the player clears a round, clears all rounds and loses the game.
