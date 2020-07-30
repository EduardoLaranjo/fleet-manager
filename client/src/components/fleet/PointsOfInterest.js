import React, {useEffect, useState} from "react";
import {useMarker} from "./Marker";

function searchPlacesNearBy(service, request, setState) {
  service.nearbySearch(request, (places, status) => {
    if (status === 'OK') {
      const parsedPlaces = places
        .filter(place => place.business_status === "OPERATIONAL")
        .map(place => ({id: place.id, pos: {lat: place.geometry.location.lat(), lng: place.geometry.location.lng()}}))

      setState(parsedPlaces)
    }
  });
}

export function PointOfInterest({currLocation, types, radius, onPointClick, map, maps}) {

  const service = new maps.places.PlacesService(map);

  const [hotels, setHotels] = useState([])
  const [gasStations, setGasStation] = useState([])
  const [restaurants, setRestaurants] = useState([])

  useEffect(() => {
    setRestaurants([]);
    setGasStation([]);
    setHotels([]);

    for (const type of types) {

      const request = {
        location: currLocation,
        radius: radius,
        type: [type]
      };

      if (type === 'hotel') {
        searchPlacesNearBy(service, request, setHotels);
      }

      if (type === 'gas_station') {
        searchPlacesNearBy(service, request, setGasStation);
      }

      if (type === 'restaurant') {
        searchPlacesNearBy(service, request, setRestaurants);
      }

    }


  }, [currLocation.lat, currLocation.lng, types, radius])

  function renderHotels() {
    return hotels.map(place => <Hotel key={place.id} pos={place.pos} map={map} maps={maps}
                                                         onClick={onPointClick}/>);
  }

  function renderRestaurants() {
    return restaurants.map(place => <Restaurant key={place.id} pos={place.pos} map={map} maps={maps}
                                                onClick={onPointClick}/>);
  }

  function renderGasStations() {
    return gasStations.map(place => <GasStation key={place.id} pos={place.pos} map={map} maps={maps}
                                                onClick={onPointClick}/>);
  }

  return (
    <>
      {renderGasStations()}
      {renderHotels()}
      {renderRestaurants()}
    </>
  )
}

export function GasStation({pos, onClick, map, maps}) {
  const img = {
    url: `${process.env.PUBLIC_URL}/fleet/icn-gas-station.png`,
    scaledSize: new maps.Size(30, 30),
    anchor: new maps.Point(15, 15)
  };

  useMarker(pos, onClick, map, maps, img)
  return null;
}

export function Hotel({pos, onClick, map, maps}) {
  const img = {
    url: `${process.env.PUBLIC_URL}/fleet/icn-hotel.png`,
    scaledSize: new maps.Size(30, 30),
    anchor: new maps.Point(15, 15)
  };

  useMarker(pos, onClick, map, maps, img)
  return null;
}

export function Restaurant({pos, onClick, map, maps}) {
  const img = {
    url: `${process.env.PUBLIC_URL}/fleet/icn-restaurant.png`,
    scaledSize: new maps.Size(30, 30),
    anchor: new maps.Point(15, 15)
  };

  useMarker(pos, onClick, map, maps, img)
  return null;
}