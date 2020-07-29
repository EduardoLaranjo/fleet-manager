import React, {useState} from "react";
import {GoogleMap} from "./GoogleMap";
import {PointOfInterest, TruckPath} from "./Markers";
import TruckApi from "../../apis/truck";

import {SearchBar} from "./SearchBar";
import {Distance} from "./Distance";

export function FleetMap() {

  const [destination, setDestination] = useState(null)

  const [truck, setTruck] = useState({})
  const [searchPOI, setSearchPOI] = useState({radius: 0, poi: []})

  const handleOnSearch = async ({license, poi, radius}) => {
    const {data: truck} = await TruckApi.get(license)
    const currLocation = truck.path[truck.path.length - 1]

    setTruck({currLocation: {...currLocation}, path: truck.path})
    setSearchPOI( {radius: radius, poi: [...poi]})
    setDestination(null)
  };

  const renderTruckPath = () => {
    if (truck.path) {
      return <TruckPath path={truck.path}/>
    }
  };

  const handleOnMarkerClick = (pos) => {
    setDestination(pos)
  };

  const renderPointsOfInterest = () => {
    if (searchPOI.poi.length > 0 && searchPOI.radius > 0) {
      return <PointOfInterest currLocation={truck.currLocation}
                              types={searchPOI.poi}
                              radius={searchPOI.radius}
                              onPointClick={handleOnMarkerClick}/>
    }
  };

  const renderDistance = () => {
    if (truck && destination) {
      return <Distance from={truck.currLocation} to={destination} />
    }
  };


  return (
    <div>
      <SearchBar onSearchSubmit={handleOnSearch}/>

      <GoogleMap>
        {renderTruckPath()}
        {renderDistance()}
        {renderPointsOfInterest()}
      </GoogleMap>

    </div>
  )
}


