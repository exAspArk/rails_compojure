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

    # Compojure/Aleph – port 8000
    cd compojure_app && lein with-profile production trampoline run -m compojure_app.handler aleph

    # Compojure/Http-kit – port 5000
    cd compojure_app && lein with-profile production trampoline run -m compojure_app.handler

    # Compojure/Ring-Jetty – port 3000
    cd compojure_app && lein with-profile production trampoline ring server-headless

    # Ruby on Rails/WEBrick – port 3001
    cd rails_app && RAILS_ENV=production bundle exec rails s -p 3001

Run benchmark script:

    ruby benchmark.rb

Run benchmark script for HTTP-stack only without hitting DB:

    ruby benchmark-http-stack.rb

## Results

#### Intel Core i5-2450M 2.50 GHz, 16 Gb RAM, SSD

First time running:

    Comparison:

    Compojure - Aleph server:      20.0 i/s
    Compojure - Http-kit server:   19.9 i/s - 1.01x slower
    Ruby on Rails:                 19.8 i/s - 1.01x slower
    Compojure - Ring-Jetty server: 19.2 i/s - 1.04x slower

After 5 iterations (to warmup JIT Compilation of Clojure implementation):

    Comparison:

    Compojure - Http-kit server:   24.7 i/s
    Compojure - Aleph server:      23.2 i/s - 1.06x slower
    Compojure - Ring-Jetty server: 23.1 i/s - 1.07x slower
    Ruby on Rails:                 19.3 i/s - 1.28x slower

After JIT Compilation for HTTP-stack only:

    Comparison:

    Compojure - Http-kit server:   102.8 i/s
    Compojure - Ring-Jetty server: 97.5 i/s - 1.05x slower
    Compojure - Aleph server:      96.4 i/s - 1.07x slower
    Ruby On Rails:                 66.4 i/s - 1.55x slower

#### Intel Core i7-4750HQ 2 GHz, 8 GB RAM, SSD

First time running:

    Comparison:

    Ruby on Rails:                 19.6 i/s
    Compojure - Aleph server:      17.2 i/s - 1.14x slower
    Compojure - Http-kit server:   17.1 i/s - 1.14x slower
    Compojure - Ring-Jetty server: 16.4 i/s - 1.19x slower

After JIT Compilation:

    Comparison:

    Ruby on Rails:                 19.5 i/s
    Compojure - Http-kit server:   18.0 i/s - 1.09x slower
    Compojure - Aleph server:      17.6 i/s - 1.11x slower
    Compojure - Ring-Jetty server: 17.2 i/s - 1.14x slower

After JIT Compilation for HTTP-stack only:

    Comparison:

    Compojure - Http-kit server:   79.4 i/s
    Compojure - Ring-Jetty server: 75.5 i/s - 1.05x slower
    Compojure - Aleph server:      75.2 i/s - 1.06x slower
    Ruby On Rails:                 66.2 i/s - 1.20x slower
