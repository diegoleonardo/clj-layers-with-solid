(ns initializers.http-adapter
  (:require [infra.api.ring-adapter :as ring-adapter]
            [integrant.core :as ig]))

(derive ::http-adapter :duct/daemon)

(defmethod ig/init-key ::http-adapter
  [_ _]
  (ring-adapter/->ring-adapter))

#_(defmethod ig/halt-key! ::http-adapter
    [_ config]
    (.stop config))
