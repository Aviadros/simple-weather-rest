(defproject simple-weather-rest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://localhost:3000"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [clj-http "3.12.3"]
                 [org.clojure/data.json "2.4.0"]
                 [metosin/ring-http-response "0.9.3"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler simple-weather-rest.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
