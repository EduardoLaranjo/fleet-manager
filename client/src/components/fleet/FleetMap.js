import React, {useState} from "react";
import {GoogleMap} from "./GoogleMap";
import {GasStation, Hotel, Restaurant, Truck} from "./Markers";
import {Distance} from "./Distance";

export function FleetMap() {
  const [to, setTo] = useState(null)
  const [truck, setTruck] = useState({pos: {lat: 38.7752344, lng: -9.3278361}})
  const [hotels, setHotels] = useState([{lat: 38.733858, lng: -9.1501}, {lat: 38.903858, lng: -9.1501}])
  const [gasStations, setGasStation] = useState([])
  const [restaurants, setRestaurants] = useState([])

  const handleOnClickMarker = (pos) => {
    setTo(pos)
  }

  function renderHotels() {
    return hotels.map(pos => <Hotel pos={pos} handleOnClick={handleOnClickMarker}/>);
  }

  function renderRestaurants() {
    return restaurants.map(pos => <Restaurant pos={pos} handleOnClick={handleOnClickMarker}/>);
  }

  function renderGasStations() {
    return gasStations.map(pos => <GasStation pos={pos} handleOnClick={handleOnClickMarker}/>);
  }

  let distance = <></>;

  if (to) {
    distance = <Distance from={truck.pos} to={to}/>
  }

  return <div>


    <GoogleMap>
      <Truck pos={truck.pos}/>
      {distance}

      {renderHotels()}
      {renderGasStations()}
      {renderRestaurants()}

    </GoogleMap>

  </div>
}