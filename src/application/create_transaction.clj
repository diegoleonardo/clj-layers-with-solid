(ns application.create-transaction
  (:require [domain.repository.transaction-repository :as transaction-repository]))

(defn execute [deps transaction]
  (transaction-repository/save (:repository deps)
                               transaction))
