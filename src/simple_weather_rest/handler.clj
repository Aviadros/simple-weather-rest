(ns simple-weather-rest.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [simple-weather-rest.weather-api-service :as wthr]
            [simple-weather-rest.db :as db]
            [ring.util.http-response :as response]
            [clojure.data.json :as json]))

(defn- string->int [x]
  (Integer/parseInt x))

(defn get-city-weather [city]
  (let [weather-data (wthr/get-city-weather city)]
    (if-let [status (:cod weather-data)]

      (-> (response/bad-request (json/write-str weather-data))
          (response/status (string->int status))
          (response/content-type "application/json"))

      (-> (response/ok (json/write-str weather-data))
          (response/content-type "application/json")))))

(defroutes app-routes
  (GET "/" [] "Simple Weather API")
  (GET "/setup" [] (db/setup))
  (context "/weather" []
    (GET "/:city" [city] (get-city-weather city))))

(def app
  (wrap-defaults app-routes site-defaults))
