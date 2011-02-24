(ns CmpjrAjaxTest.app
  (:use compojure.core
	clojure.contrib.json
	ring.adapter.jetty
	CmpjrAjaxTest.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(defroutes main-routes
  (GET "/" [] "<h1>bar!</h1>")
  (GET "/json" [] 
       {:headers {"Content-Type" "text/html"}
       :body (json-str {:foo "foo", :bar "bar"})})
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
    (handler/site main-routes))

(defn dev []
  (run-jetty #'app {:port 8080}))

