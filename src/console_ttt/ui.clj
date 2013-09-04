(ns console_ttt.ui
  (:require [ttt.board :refer [get-length
                              convert-empty-to-nums]]))

(defn prompt [message] (println message) (read-line))

(defn display-message [message] (println message))

(defn prompt-end-game [result]
  (if (= result "tie")
  (println "Game over!  It is a tie.")
  (println (str "Game over!  " result " has won!"))))

(defn ask-player-option [player]
  (loop [type (prompt (str "Enter player " player " type(h/c): "))]
    (if (or (= type "c") (= type "h"))
      type
       (recur (prompt (str "Enter correct player " player " type: "))))))

(defn ask-board-option []
  (loop [board-size (prompt (str "Enter board size: "))]
    (if (or (= board-size "3") (= board-size "4"))
      board-size
      (recur (prompt (str "Enter board size: "))))))

(defn display-board [board]
  (doseq [row (partition (get-length board)
                    (convert-empty-to-nums board))]
     (println (str "| " (clojure.string/join " | " row) " |"))))

(defn ask-ttt-options []
    {:p-one (ask-player-option "One")
    :p-two (ask-player-option "Two")
    :board-size (ask-board-option )})

(defn continue? []
  (let [input (prompt "play again? (y)es: ")]
    (if (= (clojure.string/lower-case input) "y")
      true
      false)))

(defn display-welcome-message []
  (println "Welcome to Tic Tac Toe!"))

(defn console [prompt & [board]]
  (case prompt
    "welcome" (display-welcome-message)
    "ask play again" (continue?)
    "ask options" (ask-ttt-options)
    "print board" (display-board board)
    "end game" (prompt-end-game board)))
