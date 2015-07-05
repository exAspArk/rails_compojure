(ns compojure_app.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [org.httpkit.server :as http-kit]
            [aleph.http :as aleph-http]
            [compojure_app.routes :refer [app-routes]]
            [compojure_app.models.queries :as queries]))

(defn init []
  (queries/create-books-table-if-not-exists!))

(def app
  (-> (routes app-routes)
      (wrap-defaults api-defaults)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)))

(defn- run-aleph-server []
  (aleph-http/start-server app {:port 8000})
  (println "Started server on port 8000"))

(defn- run-http-kit-server []
  (http-kit/run-server app {:port 5000})
  (println "Started server on port 5000"))

(defn -main [& args]
  (let [engine (first args)]
    (if (= engine "aleph")
      (run-aleph-server)
      (run-http-kit-server))))
