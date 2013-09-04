(ns ttt.ui.console-ui-spec
  (:require [speclj.core :refer :all])
  (:require [console_ttt.ui :refer :all]))

(defn gen-board [board]
  (subvec (clojure.string/split board #"") 1))

(describe "#print-board"
  (around [it]
    (with-out-str (it)))

  (it "asks for your move"
    (should= "5"
      (with-in-str "5"
        (prompt "Enter your move: "))))

  (it "asks for player one type"
    (should= "h"
      (with-in-str "h"
        (prompt "Enter player one type(h/c): "))))

  (it "asks for player two type"
    (should= "c"
      (with-in-str "c"
        (prompt "Enter player two type(h/c): "))))

  (it "says Game over!  It is a tie."
    (should= "Game over!  It is a tie.\n"
      (with-out-str
        (prompt-end-game "tie"))))

  (it "says Game over!  X has won!"
    (should= "Game over!  X has won!\n"
      (with-out-str
        (prompt-end-game "X")))))

(describe "#ask-ttt-options"
  (around [it]
    (with-out-str (it)))

  (describe "#ask-player-option"
    (it "retrieves player one option"
      (should= "h"
        (with-in-str "h\n"
          (ask-player-option "one"))))

    (it "asks for player type again if not an option"
      (should= "c"
        (with-in-str "u\nl\nc\n"
          (ask-player-option "one")))))

  (it "retrieves player two options"
    (should= "c"
      (with-in-str "c\n"
        (ask-player-option "two"))))

  (it "retrieves board size"
    (should= "3"
      (with-in-str "3\n"
        (ask-board-option))))

  (it "asks for board option again if not an option"
    (should= "3"
      (with-in-str "6\n3\n"
        (ask-board-option))))

  (it "retrieves ttt options"
    (should= {:p-one "h" :p-two "c" :board-size "3"}
      (with-in-str "h\nc\n3\n"
        (ask-ttt-options))))

  (it "retrieves ttt options with some wrong inputs"
    (should= {:p-one "c" :p-two "h" :board-size "4"}
      (with-in-str "u\nx\nc\ny\nh\n5\n4"
        (ask-ttt-options)))))

;(describe "#print-board"
;  (it "prints out a 3x3 board"
;    (should= "| 1 | 2 | 3 |\n| 4 | 5 | 6 |\n| 7 | 8 | 9 |\n"
;      (with-out-str
;        (display-board (gen-board "_________")))))
;
;  (it "prints out a 3x3 board with marks"
;    (should= "| X | O | 3 |\n| 4 | 5 | 6 |\n| 7 | 8 | 9 |\n"
;      (with-out-str
;        (display-board (gen-board "XO________"))))))

(describe "#display"
  (it "displays the message"
    (should= "Welcome to Tic Tac Toe!\n"
      (with-out-str
        (display-message "Welcome to Tic Tac Toe!")))))

(describe "#continue"
  (around [it]
    (with-out-str (it)))
  (it "is true when user wants to continue"
    (should= true
      (with-in-str "y\n"
        (continue?))))

  (it "is false when user doesn't want to continue"
    (should= false
      (with-in-str "n\n"
        (continue?))))

  (it "is true even if capital Y"
    (should= true
      (with-in-str "Y\n"
        (continue?)))))

(describe "#display-welcome-message"
  (around [it]
    (with-out-str (it)))

  (it "displays a welcome message"
    (should= "Welcome to Tic Tac Toe!\n"
      (with-out-str
        (display-welcome-message)))))

(describe "#console"
  (around [it]
    (with-out-str (it)))

  (it "prompts a welcome to the player"
    (should= "Welcome to Tic Tac Toe!\n"
      (with-out-str
        (console "welcome"))))

  (it "asks if user wants to play again"
    (should= true
      (with-in-str "y\n"
        (console "ask play again"))))

  (it "asks for ttt options"
    (should= {:p-one "h" :p-two "c" :board-size "3"}
      (with-in-str "h\nc\n3\n"
        (console "ask options"))))

;  (it "prints out a board"
;    (should= "| 1 | 2 | 3 |\n| 4 | 5 | 6 |\n| 7 | 8 | 9 |\n"
;      (with-out-str
;        (console "print board" (gen-board "_________")))))

  (it "says Game over!  X has won!"
    (should= "Game over!  X has won!\n"
      (with-out-str
        (console "end game" "X"))))
  )

