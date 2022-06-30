(ns infra.repository.transaction-database-repository
  (:require [domain.repository.transaction-repository :as transaction-repository]
            [infra.database.connection :as connection]))

(defrecord transaction-database-repository [conn]
  transaction-repository/transaction-repository

  (save [_ transaction]
    (connection/query conn
                      "insert into transaction (code, amount, number_installments, payment_amount) values (?, ?, ?, ?)"
                      transaction)
    (map #(connection/query conn
                            "insert into installment (code, number, amount) values (?, ?, ?)"
                            %)
         (:installments transaction)))

  (get-info [_ code]
    (let [transaction-data (connection/one conn
                                           "select * from transaction where code=?"
                                           code)
          installments-data (connection/query conn
                                              "select * from installment where code=?"
                                              code)]
      (map #(assoc transaction-data :installments %)
           installments-data))))
