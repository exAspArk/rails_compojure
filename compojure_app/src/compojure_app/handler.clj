(ns compojure_app.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [org.httpkit.server :refer [run-server]]
            [aleph.http :as http]
            [compojure_app.routes :refer [app-routes]]
            [compojure_app.models.queries :as queries]))

(defn init []
  (queries/create-books-table-if-not-exists!))

(def app
  (-> (routes app-routes)
      (wrap-defaults api-defaults)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)))

(defn -main [& args]
  (let [engine (first args)]
    (println "engine is: " engine)
    (if (= engine "aleph")
      (do (println "Gonna start alepth server")(http/start-server app {:port 8000}))
      (do (println "Gonna start http-kit server")(run-server app {:port 5000})))
    (println "Started server on port" (if (= engine "aleph") 8000 5000))))
