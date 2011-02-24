(ns CmpjrAjaxTest.app
  (:use compojure.core
        clojure.contrib.json
        net.cgrand.enlive-html
        ring.adapter.jetty
        CmpjrAjaxTest.core)
  (:require [compojure.route :as route]
    [compojure.handler :as handler]))

(deftemplate index "resources/test.html"
  [cmap]
  [:span#msg] (content (:msg cmap)))

(defroutes main-routes
  (GET "/" [] (index {:msg "Hello World!"}))
  (GET "/msg/:msg" [msg] (index {:msg msg}))
  (GET "/json" [] 
    {:headers {"Content-Type" "text/html"}
     :body (json-str {:foo "foo", :bar "bar"})})
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (handler/site main-routes))

(defn dev []
  (run-jetty #'app {:port 8080}))
