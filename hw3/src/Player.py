'''
This is the definition of a class that models a player.
Currently a player is denoted by having a name and a symbol specific to him/her.
The class also tracks how many wins the player has.

This has been modeled to fit what the project requirement wants, and more, since 
the requirements did not specify tracking the amount of wins.

This model is generic enough to work with any type of game that requires a player
so that it can be reused, or even by adding on to it by creating child classes through inheritance.

all values are meant to be hidden, and should only be obtained using the respective getter method.
'''

class Player:
    # constructor
    def __init__(self, name, symbol):
        self.name   = name
        self.symbol = symbol
        self.wins   = 0

    # getters
    def getName(self):
        return self.name
    
    def getSymbol(self):
        return self.symbol

    def getWins(self):
        return self.wins

    # adds one to the amount of wins the player has
    def incrWin(self):
        self.wins += 1