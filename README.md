# simple-weather-rest

This simple weather app will send requests to [openweathermap][] to get the temperature for the requested city.
The request will be saved to a PostgreSQL database with the timestamp of the request.

Requests need to be sent to http://localhost:3000/weather/{city} and will answer with a json in the form of

    {
    "city": "Berlin",
    "temperature": 22.43,
    "datetime": "2022-07-17T20:21:13.721"
    }

[openweathermap]: https://api.openweathermap.org

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

You will need Docker installed

## Running

run the docker-compose.yml in this repository (make sure you don't have anything running on port 5432)

    docker-compose up -d

the created PostgreSQL database will be running on localhost:5432 on the database postgres with
username: postgres
password: password

Set the Environment Variable "API_KEY" with the API key for openweathermap.org

To start a web server for the application on port 3000, run:

    lein ring server-headless

after running the application call

    localhost:3000/setup

once to setup the database for the application
