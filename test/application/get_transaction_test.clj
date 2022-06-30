(ns application.get-transaction-test
  (:require [clojure.test :refer [deftest testing is]]
            [matcher-combinators.test :refer [match?]]
            [infra.repository.transaction-memory-repository :as memory-repository]
            [application.get-transaction :as gt]))

(def code "12345678")

(def transaction-example {:code                code
                          :amount              1000
                          :number-installments 12
                          :payment-method      "credit-card"})

(def transaction-matcher {:code                string?
                          :amount              number?
                          :number-installments integer?
                          :payment-method      string?})

(deftest get-transaction
  (let [code-param (keyword code)
        state      (atom {(keyword code) transaction-example})
        repo       (memory-repository/->transaction-memory-repository state)
        deps       {:repository repo}]
    (testing "should return a valid transaction"
      (is (match? transaction-matcher
                  (gt/execute deps code-param))))))
