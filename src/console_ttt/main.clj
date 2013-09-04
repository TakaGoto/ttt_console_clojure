(ns console_ttt.main
  (:require [ttt.game :refer :all :as game])
  (:require [console_ttt.ui :refer :all :as ui]))

(defn -main [& args]
  (game/play-game ui/console))
