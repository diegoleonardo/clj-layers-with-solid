(ns application.get-transaction
  (:require [domain.repository.transaction-repository :as transaction-repository]))

(defn execute [deps code]
  (transaction-repository/get-info (:repository deps)
                                   code))
