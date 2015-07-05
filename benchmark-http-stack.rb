require "benchmark/ips"

Benchmark.ips do |x|

  x.report "Ruby On Rails" do
    `curl http://localhost:3001/dummy`
  end

  x.report "Compojure - Aleph server" do
    `curl http://localhost:8000/dummy`
  end

  x.report "Compojure - Http-kit server" do
    `curl http://localhost:5000/dummy`
  end

  x.report "Compojure - Ring-Jetty server" do
    `curl http://localhost:3000/dummy`
  end

  x.compare!
end
