import React, {useEffect, useState} from "react";

export function useMarker(pos, handleOnClick, map, maps, img) {

  useEffect(() => {

    const marker = new maps.Marker({
      position: pos,
      map: map,
      icon: img,
    });

    if (handleOnClick) {
      marker.addListener("click", () => handleOnClick(pos));
    }

    // clean marker
    return () => marker.setMap(null);

  }, [])

}

function searchPlacesNearBy(service, request, setState) {
  service.nearbySearch(request, (places, status) => {
    if (status === 'OK') {
      const parsedPlaces = places
        .filter(place => place.business_status === "OPERATIONAL")
        .map(place => ({id: place.id, pos: {lat: place.geometry.location.lat(), lng: place.geometry.location.lng()}}))

      console.log(parsedPlaces)
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
    console.log('new poi')

    console.log(currLocation.lat, currLocation.lng, types, radius)
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
    return hotels.map(place => <div id={place.id}><Hotel pos={place.pos} map={map} maps={maps}
                                                         onClick={onPointClick}/></div>);
  }

  function renderRestaurants() {
    return restaurants.map(place => {
      console.log(place.length)
      return <Restaurant id={place.id} pos={place.pos} map={map} maps={maps}
                  onClick={onPointClick}/>
    });
  }

  function renderGasStations() {
    return gasStations.map(place => <GasStation id={place.id} pos={place.pos} map={map} maps={maps}
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

export function TruckPath({path, map, maps}) {
  path = path || [];

  function renderPath(path) {
    path = path.map((pos, idx) => ({id: idx, ...pos}))
    return path.map((pos, idx) => {
      if (idx === (path.length - 1)) {
        return <TruckEnd id={pos.id} pos={pos} map={map} maps={maps}/>
      }
      if (idx === 0) {
        return <TruckStart id={pos.id} pos={pos} map={map} maps={maps}/>
      }
      return <TruckMiddle id={pos.id} pos={pos} map={map} maps={maps}/>
    })
  }

  return (
    <>
      {renderPath(path)}
    </>
  )
}

function TruckStart({pos, map, maps}) {
  const img = {
    url: `${process.env.PUBLIC_URL}/fleet/icn-first-location.png`,
    scaledSize: new maps.Size(15, 15),
  };

  useMarker(pos, null, map, maps, img);
  return null;
}

function TruckMiddle({pos, map, maps}) {
  const img = {
    url: `${process.env.PUBLIC_URL}/fleet/icn-path.png`,
    scaledSize: new maps.Size(15, 15),
  };

  useMarker(pos, null, map, maps, img)
  return null;
}

function TruckEnd({pos, map, maps}) {
  const img = {
    url: `${process.env.PUBLIC_URL}/fleet/icn-current-location.png`,
    scaledSize: new maps.Size(30, 30),
    anchor: new maps.Point(15, 15)
  };

  useMarker(pos, null, map, maps, img);
  return null;
}

