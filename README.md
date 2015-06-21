# Ruby on Rails vs Compojure Benchmark

## Setup

Install Leiningen.

Install Bundler and gems:

    gem install bundler
    gem install benchmark-ips
    cd rails_app && bundle

Install PostgreSQL and run:

    psql -U postgres -f provision_database.sql

## Running

Run 4 servers:

    # port 8000
    cd compojure_app && lein with-profile production trampoline run -m compojure_app.handler aleph

    # port 5000
    cd compojure_app && lein with-profile production trampoline run -m compojure_app.handler

    # port 3000
    cd compojure_app && lein with-profile production trampoline ring server-headless

    # port 3001
    cd rails_app && RAILS_ENV=production bundle exec rails s -p 3001

Run benchmark script:

    ruby benchmark.rb

Results of the first time run test:

    Comparison:

    Compojure - Aleph server:       20.0 i/s
    Compojure - Http-kit server:       19.9 i/s - 1.01x slower
       Ruby on Rails:       19.8 i/s - 1.01x slower
    Compojure - Ring-Jetty server:       19.2 i/s - 1.04x slower

Results after 5 iterations to warmup JIT Compilation of Clojure implementation:

    Comparison:

    Compojure - Http-kit server:       24.7 i/s
    Compojure - Aleph server:       23.2 i/s - 1.06x slower
    Compojure - Ring-Jetty server:       23.1 i/s - 1.07x slower
       Ruby on Rails:       19.3 i/s - 1.28x slower

Run benchmark script for http-stack only:

    ruby benchmark-http-stack.rb

Results after JIT Compilation:

    Comparison:

    Compojure - Http-kit server:      102.8 i/s
    Compojure - Ring-Jetty server:       97.5 i/s - 1.05x slower
    Compojure - Aleph server:       96.4 i/s - 1.07x slower
       Ruby On Rails:       66.4 i/s - 1.55x slower

Hardware:

    Intel Core i5 2450M 2.50 GHz, 16 Gb RAM, SSD
