(ns infra.database.connection)

(defprotocol connection
  (query [this statement params])
  (one [this statement params])
  (close-conn [this]))
