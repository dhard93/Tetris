This is a tile-based, pixel art Tetris clone created using javaFX.

Included in the repo are the game's source files, resource files (images, sounds, database), and the pom.xml file, which maven uses to generate the necessary dependencies
for the game.

 *********
 * SETUP *
 *********
 - The following need to be downloaded and installed to run the game (see pom.xml for all dependencies):
    - Java:   java 17.0.4 2022-07-19 LTS (openJDK)
    - JavaFX: openjfx 17.0.4
    - Maven:  apache-maven-3.8.6
    - SQLITE: 3.37.2
    
 - IF RUNNING IN IDE: 
    
    - If using an IDE, import the maven project into your IDE, and configure your jdk and javafx sdk installations with your IDE. Then the game will be set to run.
    
 - IF RUNNING IN CLI:
     - Add java and maven to your system's PATH so that the commands to run the game will work from any directory.
    
     - Once these are installed, clone the repository to your system.
 
     - Navigate to the root directory of the repo, called 'Tetris' (the directory with the pom.xml in it).
 
     - When running for first time, type the following command into your CLI to perform a clean install and generate necessary dependencies for your system:
            mvn clean install
     
     - To run the game, type the following command into your CLI:
            mvn javafx:run
        
    

 *--------------*
 * TETRIS RULES *
 *--------------*
 - The game has a current Tetromino and a next Tetromino.

 - The player controls the current Tetromino, which can fall, move, rotate, soft drop, and hard drop.

 - The current Tetromino will start at its starting position at the top of the game board and fall by
   1 tile space at the speed of gravity.

 - Gravity starts at 1 tile space per second, but will get progressively faster as the player's level increases.

 - The current Tetromino will fall until it is resting on top of either a placed Tile on the game board, or the
   bottom of the game board, after which its Tiles will be placed on the game board.

 - The player can soft drop the current Tetromino, which makes it fall at a faster rate, or hard drop it,
   which causes it to instantly drop to the position of the preview Tiles.

 - When the current Tetromino is placed on the game board, if it was not hard dropped, it will enter a 0.5 second
   delay period, during which the player will have one last change to move/rotate it before it locks into place on
   the game board. If it was hard dropped, then there is no delay.

 - Once the current Tetromino is locked in on the game board, the game will check to see if any rows are filled
   with placed Tiles. If so, each filled row will be animated and cleared of its Tiles, and all rows above it
   will be dropped down.

 - Next, tests will be run to check if game over conditions have
   been met. If so, the game will end - if not, then the current Tetromino's type changes to that of the next
   Tetromino, a new next Tetromino is randomly selected, and the current Tetromino is reset to the top of the game board
   to repeat the cycle. This cycle repeats until game over conditions are met.

 - A TETRIS is achieved when 4 lines are cleared at once.

 - Each time a Tetromino is placed on the game board, score is calculated based on number of lines cleared,
   the player's current level, and the number of rows that were soft and/or hard dropped before placement.

 - Each time a Tetris is achieved, a golden letter from the word 'TETRIS' will appear in the top left display window,
   until the full word TETRIS is displayed.

 - If the player earns all 6 TETRIS letters, then 'TETRIS MASTER' will be activated, and they will receive a substantial
   score bonus.

 *----------------------*
 * TETRIS MENU CONTROLS *
 * ---------------------*
 - Navigate menu options: UP and DOWN keys
 - Select menu option:    SPACE or ENTER key

 *--------------------------*
 * TETRIS GAMEPLAY CONTROLS *
 *--------------------------*
 - Move:       LEFT and RIGHT keys
 - Rotate:     Z and X keys
 - Soft Drop:  DOWN key
 - Hard Drop:  UP key
 - Play/Pause: SPACE or ENTER key
 */
