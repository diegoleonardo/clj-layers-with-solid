(ns domain.repository.transaction-repository
  (:refer-clojure :exclude [get]))

(defprotocol transaction-repository
  (save [this transaction])
  (get-info [this code]))
