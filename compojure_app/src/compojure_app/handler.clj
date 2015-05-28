(ns compojure_app.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [org.httpkit.server :refer [run-server]]
            [compojure_app.routes :refer [app-routes]]
            [compojure_app.models.queries :as queries]))

(defn init []
  (queries/create-books-table-if-not-exists!))

(def app
  (-> (routes app-routes)
      (wrap-defaults api-defaults)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)))

(defn -main []
  (run-server app {:port 5000})
  (println "Started server on port 5000"))
