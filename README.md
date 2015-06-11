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

Run 3 servers:

    # port 5000
    cd compojure_app && lein with-profile production trampoline run -m compojure_app.handler

    # port 3000
    cd compojure_app && lein with-profile production trampoline ring server-headless

    # port 3001
    cd rails_app && RAILS_ENV=production bundle exec rails s -p 3001

Run benchmark script:

    ruby benchmark.rb

Results:

    Comparison:

    Ruby on Rails:               17.4 i/s
    Compojure - Http-kit server: 16.7 i/s - 1.04x slower
    Compojure - Ring server:     16.5 i/s - 1.05x slower
