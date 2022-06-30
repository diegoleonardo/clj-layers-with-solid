(ns infra.repository.transaction-memory-repository
  (:require [domain.repository.transaction-repository :as transaction-repository]))

(defrecord transaction-memory-repository [state]
  transaction-repository/transaction-repository

  (save [_ transaction]
    (let [code (-> transaction
                   :code
                   keyword)]
      (swap! state assoc code transaction)))

  (get-info [_ code]
    (get @state (keyword code))))
