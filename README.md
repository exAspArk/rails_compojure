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

    cd compojure_app && lein run -m compojure_app.handler            # port 5000
    cd compojure_app && lein ring server-headless                    # port 3000
    cd rails_app && RAILS_ENV=production bundle exec rails s -p 3001 # port 3001

Run benchmark script:

    ruby benchmark.rb

Results:

    Comparison:

    Ruby on Rails:               19.3 i/s
    Compojure - Http-kit server: 16.9 i/s - 1.14x slower
    Compojure - Ring server:     15.3 i/s - 1.26x slower
