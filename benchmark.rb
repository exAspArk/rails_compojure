require "benchmark/ips"

Benchmark.ips do |x|
  x.report "Ruby on Rails" do
    `curl -X DELETE http://localhost:3001/books/all`
    `curl -X POST -H "Content-Type: application/json" -d '{ "book": {"title":"A", "authors":"B"} }' http://localhost:3001/books`
    `curl http://localhost:3001/books`
  end

  x.report "Compojure - Ring-Jetty server" do
    `curl -X DELETE http://localhost:3000/books/all`
    `curl -X POST -H "Content-Type: application/json" "http://localhost:3000/books?title=A&authors=B"`
    `curl http://localhost:3000/books`
  end

  x.report "Compojure - Http-kit server" do
    `curl -X DELETE http://localhost:5000/books/all`
    `curl -X POST -H "Content-Type: application/json" "http://localhost:5000/books?title=A&authors=B"`
    `curl http://localhost:5000/books`
  end

  x.report "Compojure - Aleph server" do
    `curl -X DELETE http://localhost:8000/books/all`
    `curl -X POST -H "Content-Type: application/json" "http://localhost:8000/books?title=A&authors=B"`
    `curl http://localhost:8000/books`
  end

  x.compare!
end
