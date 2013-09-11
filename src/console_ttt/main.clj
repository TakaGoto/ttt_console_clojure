(ns console_ttt.main
  (:require [ttt.game :refer [play-game]]
            [console_ttt.ui :refer [console]]
            [console_ttt.new-ui :refer [ui-console]]))

(defn -main [& args]
  (play-game ui-console))
