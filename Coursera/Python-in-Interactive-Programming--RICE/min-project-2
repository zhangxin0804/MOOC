# template for "Guess the number" mini-project
# input will come from buttons and an input field
# all output for the game will be printed in the console

import simplegui
import random

#global variables
secret_number = 0
mode = 0  # mode 0: range from 0 to 100, default
          # mode 1: range from 0 to 1000
numOfRemainGuess = 7  # for range 0 to 100, max guess is 7, default
                      # for range 0 to 1000, max guess is 10
    
# helper function to start and restart the game
def new_game():
    # initialize global variables used in your code here
    global secret_number,numOfRemainGuess
    if mode is 0:
        numOfRemainGuess = 7
        secret_number = random.randrange(0, 100)
        print "New game. Range is from 0 to 100"
        print "Number of remaining guesses is " + str(numOfRemainGuess)
    else:
        numOfRemainGuess = 10
        secret_number = random.randrange(0, 1000)
        print "New game. Range is from 0 to 1000"
        print "Number of remaining guesses is " + str(numOfRemainGuess)
    print ""
    
   
# define event handlers for control panel
def range100():
    # button that changes the range to [0,100) and starts a new game 
    global mode
    mode = 0
    new_game()
       
def range1000():
    # button that changes the range to [0,1000) and starts a new game     
    global mode
    mode = 1
    new_game()
    
    
def input_guess(guess):
    # main game logic goes here	
    global numOfRemainGuess
    guessNum = int(guess)
    print "Guess was " + str(guessNum) 
    numOfRemainGuess = numOfRemainGuess - 1  	
    print "Number of remaining guesses is " + str(numOfRemainGuess)

    if guessNum < secret_number and numOfRemainGuess!=0:
        print "Higher!"
    elif guessNum > secret_number and numOfRemainGuess!=0:
        print "Lower!"
    elif guessNum == secret_number:
        print "Correct!"
    
    if numOfRemainGuess is 0 or guessNum == secret_number:
        if guessNum!=secret_number:
            print "You ran out of guesses. The number was " + str(secret_number)
        print ""
        new_game()
    print ""
    
#########################################################################
    
# create frame
frame = simplegui.create_frame('Guess the number', 400, 400, 200)

# register event handlers for control elements and start frame
inp = frame.add_input('Guess Enter', input_guess, 100)
button1 = frame.add_button('Range: 0-100', range100, 200)
button2 = frame.add_button('Range: 0-1000', range1000, 200)
# call new_game 
new_game()

# always remember to check your completed program against the grading rubric
