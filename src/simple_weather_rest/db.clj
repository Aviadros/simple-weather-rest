(ns simple-weather-rest.db
  (:require [clojure.java.jdbc :as sql]))

(def pg-db {:dbtype "postgresql"
            :dbname "postgres"
            :host "localhost"
            :user "postgres"
            :password "password"})

(defn setup []
  (sql/db-do-commands pg-db
                      (sql/create-table-ddl :temperature
                                            [[:temperature  "varchar(15)"]
                                             [:datetime     "varchar(100)"]
                                             [:city         "varchar(200)"]]
                                            {:conditional? true})))

(defn insert-temperature [data]
  (sql/insert! pg-db :temperature data))