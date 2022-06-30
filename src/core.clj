(ns core
  (:require [integrant.core :as ig]
            [initializers.system :as stm]))

(defn -main []
  (let [deps (stm/config)]
    (ig/init deps)))
