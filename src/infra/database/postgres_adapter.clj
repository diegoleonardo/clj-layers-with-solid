(ns infra.database.postgres-adapter
  (:require [infra.database.connection :as conn]
            [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection])
  (:import (com.zaxxer.hikari HikariDataSource)))

(def db-config
  {:dbtype               "postgresql"
   :dbname               "transactions"
   :user                 "postgres"
   :password             "mysecretpassword"
   :host                 "172.17.0.2"
   :dataSourceProperties {:socketTimeout 30}})

(defn connection-pool [config]
  (let [configuration (or config
                          db-config)]
    (next.jdbc.connection/->pool
     HikariDataSource
     {:jdbcUrl         (next.jdbc.connection/jdbc-url configuration)
      :maximumPoolSize 100})))

(defrecord postgres-adapter [db-info]
  conn/connection

  (query [_ statement params]
    (let [query (clojure.string/replace statement #"\?" params)]
      (with-open [data-source (connection-pool db-info)
                  conn (jdbc/get-connection data-source)]
        (jdbc/execute! conn [query]))))

  (one [_ statement params]
    (with-open [data-source (connection-pool db-info)
                conn (jdbc/get-connection data-source)]
      (jdbc/execute-one! conn [statement params])))

  (close-conn [_]
    (with-open [ds (connection-pool db-info)]
      (.close (jdbc/get-connection ds)))))
