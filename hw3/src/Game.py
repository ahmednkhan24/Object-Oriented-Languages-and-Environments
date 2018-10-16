'''
This is the definition of a class that models a game object.
Currently a game is denoted by having a board, and having 2 players.

This has been modeled to fit what the project requirement wants.
The board can be made to any size: 3x3, 3x4, 500x250, etc while also maintaining
all logic checks, like checking for a horizontal, vertical, or diagonal win.

This model is generic enough to work with any type of 2 player game that requires a board.

The only method that would need minimal changing to work with other games is simply
the validateMove() function. This is because different games have different criteria for a valid move
'''

import Player
import Board

class Game:
    # constructor
    def __init__(self, board, players):
        self.board   = board
        self.players = players
        self.round   = 0

    # returns what round the current game is on
    def getRound(self):
        return self.round

    # adds one to the round variable
    def incrRound(self):
        self.round += 1

    # returns a string of the current board to the console
    def printBoard(self):
        return self.board.boardToString()
    
    # returns the player whose turn it currently is
    def whoseTurn(self):
        if self.round % 2 == 0:
            return self.players[1]
        else:
            return self.players[0]
    
    # given a string of the user's move, returns a message spcecifying what went wrong
    def validateMove(self, move):
        # if the user did not enter 2 values
        split = move.split()
        if len(split) != 2:
            return "Invalid, move you must enter 2 numbers, try again: "

        # if the user did not enter values within the range
        x = int(split[0]) - 1
        y = int(split[1]) - 1
        if x < 0 or x >= self.board.getX() or y < 0 or y >= self.board.getY():
            return "Invalid move, outside board, try again: "

        # if the user chose a location that is already filled
        if self.board.isOccupied(x, y):
            return "Invalid move, position already taken, try again: "

        return "VALID"
    
    # given a string of the user's move that is assumed to be valid, inserts it into the board
    def makeMove(self, move, symbol):
        split = move.split()
        x = int(split[0]) - 1
        y = int(split[1]) - 1
        self.board.makeMove(x, y, symbol)

    # returns true if the board has hit its capacity, false otherwise
    def boardIsFull(self):
        if self.round == self.board.getX() * self.board.getY():
            return True
        else:
            return False
    
    # returns true if the symbol has a win on the board, false otherwise
    def isWin(self, symbol):
        return self.board.checkForWin(symbol)

    # starts a new game by resetting the values of this object, except for the players
    def newGame(self):
        self.board.resetBoard()
        self.round = 0