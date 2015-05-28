(ns compojure_app.models.queries
  (:require [yesql.core :refer [defqueries]]))

(defqueries "compojure_app/models/book_queries.sql" {:connection "postgres://postgres@127.0.0.1:5432/rails_compojure"})
