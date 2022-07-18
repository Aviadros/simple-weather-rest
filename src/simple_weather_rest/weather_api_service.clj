(ns simple-weather-rest.weather-api-service
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [simple-weather-rest.db :as db]))

(def ^{:private true} api-key (System/getenv "API_KEY"))
(def ^{:private true} weather-api-url "https://api.openweathermap.org/data/2.5/weather")
(defn now [] (str (java.time.LocalDateTime/now)))

(defn json-body->map [request]
  (json/read-str (:body request)
                 :key-fn keyword))


(defn get-city-weather [city]
  (if-let [weather-data  (client/get weather-api-url
                                     {:accept :json
                                      :query-params {:q city
                                                     :appid api-key
                                                     :units "metric"}
                                      :throw-exceptions false})]
    (if (= 200 (:status weather-data))
      (let [temp      (:temp (:main (json-body->map weather-data)))
            response  {:city city
                       :temperature temp
                       :datetime (now)}]
        (db/insert-temperature response)
        response)

      (json-body->map weather-data))
    nil))