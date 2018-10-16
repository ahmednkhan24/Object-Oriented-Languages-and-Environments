'''
all print statements and input statements are only in this file, 
which allows future iterations to completely change the user inteface 
of the game by simply changing code in this file.

This file can simulate any 2 player game without changing anything.
All this file does is play a 2 player game by:
    continuing to loop until the game has been won, or the user exits
    printing the board
    getting user input
    validating user input
    checking for a win by player 1 or player 2 
    checking for a tie game
    allowing the user to play again if he/she wants
There is no logic for playing any specific game in this file, and therefore can be applied to any
2 player game that has a board.

This entire project was designed using tight cohesion and loose coupling.
'''

import Game
import Player
import Board

print("CS474 F18 - HW3 Tic Tac Toe - Ahmed Khan akhan227")

# # create the necessary objects for the game
player1 = Player.Player("Player 1", "X")
player2 = Player.Player("Player 2", "O")
board   = Board.Board(3, 3, " ")
game    = Game.Game(board, [player1, player2])

# start the game
print("Type the row and column in which you wish to move.")
gameInProgress = True
while gameInProgress:
    # refresh the game
    print(game.printBoard())
    game.incrRound()
    player = game.whoseTurn()

    # make the user's move
    move = input("Turn " + str(game.getRound()) + ": " + player.getName() + " (" + player.getSymbol() + "), choose your move: ")
    message = game.validateMove(move)
    while not message == "VALID":
        move = input(message)
        message = game.validateMove(move)
    game.makeMove(move, player.getSymbol())

    # check for a win or a tie game
    message = ""
    if game.isWin(player.getSymbol()):
        game.printBoard()
        message += player.getName() + " wins!"
        player.incrWin()
        gameInProgress = False
    elif game.boardIsFull():
        game.printBoard()
        message += "Tie Game!"
        gameInProgress = False
    
    # check if the users want to play again
    if not gameInProgress:
        again = input(message + " Play again? (y/n): ")
        if again == "y":
            game.newGame()
            gameInProgress = True

print("Goodbye!")