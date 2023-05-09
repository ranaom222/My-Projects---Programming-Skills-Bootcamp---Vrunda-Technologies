"use client";
import "bootstrap/dist/css/bootstrap.min.css"; // Import bootstrap CSS
import React from "react";

const api = {
  key: "", // in .env file
  base: "https://api.openweathermap.org/data/2.5/",
};

export default function Home() {
  const [searchQuery, setSearchQuery] = React.useState(undefined);
  const [weather, setWeather] = React.useState(undefined);

  const search = (event) => {
    fetch(`${api.base}weather?q=${searchQuery}&units=metric&APPID=${api.key}`)
      .then((res) => res.json())
      .then((result) => {
        setSearchQuery("");
        setWeather(result);
        console.log(result);
      });
  };

  const dateBuilder = (d) => {
    let months = [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December",
    ];
    let days = [
      "Sunday",
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday",
    ];
    const date = new Date(d);
    let day = days[date.getDay()];
    let dt = date.getDate();
    let month = months[date.getMonth()];
    let year = date.getFullYear();
    const hours = date.getHours() % 12 || 12;
    const minutes = ("0" + date.getMinutes()).slice(-2);
    const seconds = ("0" + date.getSeconds()).slice(-2);
    const amOrPm = date.getHours() >= 12 ? "PM" : "AM";
    const formattedDateTime = `${hours}:${minutes}:${seconds} ${amOrPm}`;
    return `${day} ${dt} ${month} ${year} - ${formattedDateTime}`;
  };

  return (
      <main>
        {/* head tag starts */}
        {/* head tag ends */}

        {/* navbar starts */}
        <nav className="navbar bg-body-tertiary navbar-dark bg-dark">
          <div className="container-fluid">
            <a className="navbar-brand">Ishan's Weather App</a>
            <div className="d-flex">
              <input
                className="form-control me-2"
                onChange={(val) => setSearchQuery(val.target.value)}
                value={searchQuery}
                type="text"
                placeholder="Search here"
              />
              <button
                className="btn btn-primary"
                id="searchWeather"
                onClick={search}
              >
                Check
              </button>
            </div>
          </div>
        </nav>
        {/* navbar ends */}

        {/* <div className="row g-3 align-items-center mt-3">
        <div className="col-3"></div>
        <div className="col-1">
          <label className="col-form-label">Search here</label>
        </div>
        <div className="col-3">
          <input
            className="form-control"
            type="text"
            id="cityName"
            onChange={(val) => setSearchQuery(val.target.value)}
            value={searchQuery}
          />
        </div>
        <div className="col-2">
          <button
            className="btn btn-primary"
            id="searchWeather"
            onClick={search}
          >
            Check weather
          </button>
        </div>
        <div className="col-3"></div>
      </div> */}

        {typeof weather !== "undefined" ? (
          <div className="row mt-3">
            {/* row 1 start */}
            <div className="col-3"></div>
            <div className="card col-6 shadow-sm">
              <div className="card-body">
                <h5 className="card-title">
                  {weather.name}, {weather.sys.country}
                </h5>
                <p className="card-text">
                  <em>{weather.weather[0].description}</em>
                </p>
              </div>
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <img
                    src={`http://openweathermap.org/img/w/${weather.weather[0].icon}.png`}
                    alt="weather icon"
                    className="weather-icon"
                  />
                  {Math.round(weather.main.temp)}°c {weather.weather[0].main}
                </li>
                <li className="list-group-item">
                  <strong>
                    <em>{dateBuilder(weather.dt * 1000)}</em>
                  </strong>
                </li>
              </ul>
            </div>
            <div className="col-3"></div>
            {/* row 1 ends*/}

            {/* row 2 starts */}
            <div className="col-3"></div>
            <div className="card col-6 mt-2 shadow-sm">
              <div className="card-body">
                <h5 className="card-title">Temperature</h5>
                <p className="card-text">
                  <em>Wind</em>
                </p>
              </div>
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <i>Feels like </i> {Math.round(weather.main.feels_like)}°c
                </li>
                <li className="list-group-item">
                  <i>Peak temperature </i> {Math.round(weather.main.temp_max)}°c
                </li>
                <li className="list-group-item">
                  <i>Lowest temperature </i> {Math.round(weather.main.temp_min)}
                  °c
                </li>
                <li className="list-group-item">
                  <i>Wind speed </i> {Math.round(weather.wind.speed * 3.6)} km/h
                </li>
              </ul>
            </div>
            <div className="col-3"></div>
            {/* row 2 ends */}

            {/* row 3 starts */}
            <div className="col-3"></div>
            <div className="card col-6 mt-2 shadow-sm">
              <div className="card-body">
                <h5 className="card-title">Pressure</h5>
                <p className="card-text">
                  <em>{weather.main.pressure} hPa</em>
                </p>
              </div>
            </div>
            <div className="col-3"></div>
            {/* row 3 ends */}

            {/* row 4 starts */}
            <div className="col-3"></div>
            <div className="card col-6 mt-2 shadow-sm">
              <div className="card-body">
                <h5 className="card-title">Humidity</h5>
                <p className="card-text">
                  <em>{weather.main.humidity}%</em>
                </p>
              </div>
            </div>
            <div className="col-3"></div>
            {/* row 4 ends */}

            {/* row 5 starts */}
            <div className="col-3"></div>
            <div className="card col-6 mt-2 shadow-sm">
              <div className="card-body">
                <h5 className="card-title">Visibility</h5>
                <p className="card-text">
                  <em>{weather.visibility / 1000} km</em>
                </p>
              </div>
            </div>
            <div className="col-3"></div>
            {/* row 5 ends */}
          </div>
        ) : (
          <div className="card card-body m-3 border bg-light shadow-sm">
            No results
          </div>
        )}
      </main>
  );
}
