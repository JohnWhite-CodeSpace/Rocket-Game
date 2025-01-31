# Rocket Adventure

This is my project for the Object-Oriented Programming laboratories at the Warsaw University of Technology.

## Before Running the Program
- Ensure that all graphic, text, and audio files are located inside the `res` folder and are accessible via commands in the code.
- If any files are missing from the `res` folder or are inaccessible (especially audio files), you can try commenting out the corresponding instantiations in the code. Most of these instantiations are located in the `GamePanel` class.
- Check if your IDE is capable of running this program.

## Rules
- **Consult me before committing any changes.**
- **Do not change the extensions of audio files** inside the `res` folder. Java Swing, as far as I am aware, only supports `.wav` files.
- **Report any bugs, performance drops, or other issues** you encounter while running this beta version.

## Basic Info
This game, as mentioned above, is a project for our Object-Oriented Programming classes at the Warsaw University of Technology. It is currently intended solely for educational purposes. Perhaps in the future, it will be available to other users, but for now, it serves as a testing ground for our knowledge of Java programming.

## Branches
- The `master` branch contains the most recent version of the game, which has been rewritten in JavaFX. It is still in the process of transferring various functionalities from the old version and developing new ones, such as the **player pathfinding method** (`BestRoute.java` class), which is based on the **Traveling Salesman Problem (TSP) genetic algorithm** *(note for my professor from Genetic Algorithms classes).*
- The `master_swing` branch contains the old version of the game, which was written in Java Swing. It **does not** include a pathfinding algorithm for the player and is prone to bugs.

