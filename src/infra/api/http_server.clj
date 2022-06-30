(ns infra.api.http-server)

(defprotocol http-server
  (listen [this port routes]))
