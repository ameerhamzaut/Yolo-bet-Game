# BETTING GAME

# Backend for a Game #


* Player is sending a bet and whole number from 1 to 100 to a server
* Server generates random whole number from 1 to 100, and if the player's number is greater, calculates win and sends it back to the client.
* Win depends on chance: = bet * (99 / (100 - number)), as an example, if player selected the number 50 and bet 40.5, the win would be 80.19


# Requirements: #

* Java 11 (or up)
* Spring boot 2
* REST + JSON (WebSocket for communication instead is a plus)
* Unit and Integration tests
* Data validation


# Optional Task: #
Write a test that is going to play 1 million rounds in parallel in 24 threads and will calculate how much money the player is receiving back (RTP)
Example: For 1 million games player has spent 1 million and had won 990000, RTP is going to be 99% 
