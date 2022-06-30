(ns initializers.http-router
  (:require [infra.api.reitit-adapter :as reitit-adapter]
            [infra.api.router :as router]
            [integrant.core :as ig]))

(defmethod ig/init-key ::router-adapter
  [_ config]
  (let [routes (router/routes config)]
    (reitit-adapter/->reitit-adapter routes)))
