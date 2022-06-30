(ns initializers.system
  (:require [initializers.repository :as repository]
            [initializers.http-router :as router-adapter]
            [initializers.http-adapter :as http-adapter]
            [initializers.server :as server]
            [integrant.core :as ig]))

(defn config []
  {::http-adapter/http-adapter {}

   ::repository/repository {:init-state (atom {})}

   ::router-adapter/router-adapter {:repository (ig/ref ::repository/repository)}

   ::server/server {:port   3000
                    :server (ig/ref ::http-adapter/http-adapter)
                    :router (ig/ref ::router-adapter/router-adapter)}})
