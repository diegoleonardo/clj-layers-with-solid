(ns initializers.repository
  (:require [infra.repository.transaction-memory-repository :as mem-repository]
            [integrant.core :as ig]))

(defmethod ig/init-key ::repository
  [_ {:keys [init-state]}]
  (mem-repository/->transaction-memory-repository init-state))

#_(defmethod ig/halt-key! ::repository
    [_ config]
    (.stop config))
