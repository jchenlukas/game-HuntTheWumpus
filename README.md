# game-HuntTheWumpus
uses a graph to represent a game map for the game Hunt the Wumpus, a classic RPG with very simple rules

summary: The rules of my games are that the hunter can use "wasd" to move around and the color of the vertices will be red if the wumpus is two or less blocks from him. There are also two additional rules: 1. the teleport machine is always visible to the user. The user can step on the machine and if hit any key within "wasd", the hunter will be teleport to a new, safe place (but he may be teleported to a trap!). 2. There is an invisible trap within the game. If the hunter steps on the trap, the trap will become visible and there will be an additional visible wumpus.

usage: python3 ___.py
