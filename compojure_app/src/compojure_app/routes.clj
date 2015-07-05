(ns compojure_app.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as response]
            [compojure_app.models.queries :as queries]))

(defn get-dummy [request]
  {:status 20
   :body "Ok"})

(defn get-books-route [request]
  (queries/all-books))

(defn post-book-route [request]
  (let [title (get-in request [:params, :title])
        authors (get-in request [:params, :authors])]
    (queries/insert-book<! {:title title :authors authors})
    (response/redirect "/books")))

(defn delete-books-route [request]
  (queries/delete-books!)
  (response/redirect "/books"))

(defn route-not-found [request]
  "Not Found")

(defroutes app-routes
  (GET    "/dummy"     [] get-dummy)
  (GET    "/books"     [] get-books-route)
  (POST   "/books"     [] post-book-route)
  (DELETE "/books/all" [] delete-books-route)
  (route/not-found route-not-found))
