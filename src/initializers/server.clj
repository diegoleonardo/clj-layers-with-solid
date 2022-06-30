(ns initializers.server
  (:require [integrant.core :as ig]
            [infra.api.http-server :as http-server]
            [infra.api.http-router :as http-router]))

(derive ::server :duct/daemon)

(defmethod ig/init-key ::server
  [_ {:keys [port server router]}]
  (let [routes (http-router/create router)]
    (http-server/listen server port routes)))

(defmethod ig/halt-key! ::server
  [_ config]
  (.stop config))
