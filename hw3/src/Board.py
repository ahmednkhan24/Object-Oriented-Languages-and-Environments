'''
This is the definition of a class that models a two dimensional board.
Currently a board is denoted by having two dimensions, and something that can
be tracked as 'empty' in order to know if a space is filled or not.

This has been modeled to fit what the project requirement wants.
The board can be made to any size: 3x3, 3x4, 500x250, etc while also maintaining
all logic checks, like checking for a horizontal, vertical, or diagonal win by simply
changing the parameters given to the constructor.

This model is generic enough to work with any type of game that requires a board.
This project was used to play Tic-Tac-Toe, but a board object can easily be made to 
play Battleship, Scrabble, or any other board game.

all methods that begin with __ are meant to be private.
'''

import numpy as np

class Board:
    # constructor
    def __init__(self, dimX, dimY, empty):
        self.empty = empty
        self.dimX  = dimX
        self.dimY  = dimY
        self.board = self.__createBoard()

    # getters
    def getX(self):
        return self.dimX

    def getY(self):
        return self.dimY

    # resets the board by creating a new board array
    def resetBoard(self):
        self.board = self.__createBoard()

    # returns true if the board at coordinates (x,y) is already occupied, false otherwise
    def isOccupied(self, xCoord, yCoord):
        if self.board[xCoord][yCoord] == self.empty:
            return False
        else:
            return True
    
    # adds the symbol to the board at the specified location
    def makeMove(self, x, y, symbol):
        self.board[x][y] = symbol
    
    # returns a string representation of the board
    # looks like this if the board is a 3x3, like for Tic-Tac-Toe
    #
    # +-------+-------+-------+
    # + (0,0) | (0,1) | (0,2) +
    # +-------+-------+-------+
    # + (1,0) | (1,1) | (1,2) +
    # +-------+-------+-------+
    # + (2,0) | (2,1) | (2,2) +
    # +-------+-------+-------+
    def boardToString(self):
        middle = "+---+---+---+\n"
        string =  ""
        string += middle
        for x in range(self.dimX):
            for y in range(self.dimY):
                if y == 0:
                    string += "+ " + self.board[x][y]
                elif y == self.dimY - 1:
                    string += self.board[x][y] + " +\n"
                else:
                    string += " | " + self.board[x][y] + " | "
            string += middle
        return string

    # checks if there is currently a win on the board for the given symbol
    def checkForWin(self, symbol):
        if self.__rowsWin(symbol) or self.__colsWin(symbol) or self.__diagsWin(symbol, 3):
            return True
        else:
            return False

    # checks the rows of the board if there is a win
    def __rowsWin(self, symbol):
        for x in range(self.dimX):
            values = self.board[x,:]
            if all(v == symbol for v in values):
                return True
        return False

    # checks the columns of the board if there is a win
    def __colsWin(self, symbol):
        for y in range(self.dimY):
            values = self.board[:,y]
            if all(v == symbol for v in values):
                return True
        return False

    # checks the diagonals of the board if there is a win
    def __diagsWin(self, symbol, length):
        diagonals = self.__getDiagonals(length)
        for d in diagonals:
            if all(v == symbol for v in d):
                return True
        return False
    
    # function returns a list of diagonal values from the board
    # original code found here:
    # https://stackoverflow.com/questions/6313308/get-all-the-diagonals-in-a-matrix-list-of-lists-in-python
    def __getDiagonals(self, length):
        # left diagonals
        diags = [self.board[::-1,:].diagonal(i) for i in range(-self.board.shape[0]+1,self.board.shape[1])]
        # right diagonals
        diags.extend(self.board.diagonal(i) for i in range(self.board.shape[1]-1,-self.board.shape[0],-1))
        diagonals = []
        for i in diags:
            if len(i) == length:
                diagonals.append(i)
        return diagonals

    # returns a 2d list with the specificed dimensions, initialized to whatever the is classified as 'empty'
    def __createBoard(self):
        board = np.array([[self.empty for y in range(self.dimX)] for x in range(self.dimY)])
        return board