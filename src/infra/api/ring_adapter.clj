(ns infra.api.ring-adapter
  (:require [infra.api.http-server :as http-server]
            [ring.adapter.jetty :as jetty]))

(defrecord ring-adapter []
  http-server/http-server

  (listen [_ port routes]
    (let [s (jetty/run-jetty routes
                             {:port  port
                              :join? false})]
      s)))
