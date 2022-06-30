(ns infra.api.router
  (:require [application.create-transaction :as create-transaction]
            [application.get-transaction :as get-transaction]))

(defn- create-handler [{:keys [body-params deps]}]
  (create-transaction/execute deps body-params))

(defn- get-handler [{:keys [path-params deps]}]
  (let [code        (:code path-params)
        transaction (get-transaction/execute deps code)]
    {:status 200
     :body   transaction}))

(defn wrap-repository [deps]
  {:name ::wrap-database
   :wrap (fn [handler]
           (fn [request]
             (let [new-request (assoc request :deps deps)]
               (handler new-request))))})

(defn routes [deps]
  [["/transactions"
    {:middleware [(wrap-repository deps)]}
    ["" {:post {:summary "Route to create a transaction"
                :handler create-handler}}]
    ["/:code" {:get {:summary "Route to get a transaction"
                     :handler get-handler}}]]])
