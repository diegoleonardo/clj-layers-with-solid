(ns application.create-transaction-test
  (:require [clojure.test :refer [deftest testing is]]
            [infra.repository.transaction-memory-repository :as memory-repository]
            [application.create-transaction :as ct]))

(deftest create-transaction
  (let [state (atom {})
        repo (memory-repository/->transaction-memory-repository state)
        deps {:repository repo}]
    (testing "should create a transaction"
      (ct/execute deps {:code "foobar"
                        :amount 10
                        :number-installments 12
                        :payment-method "credit-card"})
      (is (> (count @state) 0)))))
