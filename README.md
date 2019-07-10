Scenario: We want to build the software for a bowling-arena. We need to have a simple UI where players can track their game. For simplicity only consider single players. Be creative, be pragmatic!

Requirements Task: Bowling Scoreboard according to the rules specified here - 
please take a close look on how to calculate the score of Strikes and Spares. A perfect game needs to score 300 points. 
Also, the last frame is up to 3 rolls. 
1. Input: Next roll - number of the pins hit. 
2. Checks: pin number check for every roll, max number of pins for the frame, end-of-the-game check, etc. 
3. Ouput: Scoreboard displaying frames, total score by frames, hits within the frame, including Strikes, Spares and Misses 
(see the link above for reference). 
4. Displaying Splits and Fouls is NOT required. 
5. Scorebord is to be refreshed after every roll. 

6. Simplicity: single player only, the GUI is not the focus of this task, can be very basic, 
but it should show the total score and the score of each frame.
It should also be possible to do a roll (e.g. enter number of pins) using this gui. 
7. Errors need to be displayed. 

8. The project needs to be easily compiled & packaged, have a proper build system. 

9. A short description on how to get it to run is also helpful. 
The code should be well structured, readable and testable. Bonus: implement automated tests

How to run the application?

1. It is a spring boot application.
2. Simply import the project into the IDE of your choice.
3. Run mvn clean install
4. Right-click on App.java and select "Run as Java Application"
5. Once the application is started, access - http://localhost:8080/playgame 

  a. Add pin numbers and click on Submit for game play. 
  b. To reset or restart the game, press RESTART button.
