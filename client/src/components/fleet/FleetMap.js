import React, {useRef, useState} from "react";
import {GoogleMap} from "./GoogleMap";
import TruckApi from "../../apis/truck";
import {SearchBar} from "./SearchBar";
import {Distance} from "./Distance";
import {TruckPath} from "./TruckPath";
import {PointOfInterest} from "./PointsOfInterest";
import {Message, Portal} from "semantic-ui-react";
import "./FleetMap.css"


export function FleetMap() {

  const [destination, setDestination] = useState(null)
  const [truck, setTruck] = useState({})
  const [searchPOI, setSearchPOI] = useState({radius: 0, poi: []})
  const [err, setErr] = useState('')
  const timeoutId = useRef(0)

  const handleOnSearch = async ({license, poi, radius}) => {
    setDestination(null)

    try {

      clearTimeout(timeoutId.current)

      const {data: truck} = await TruckApi.get(license)
      const currLocation = truck.path[truck.path.length - 1]

      setTruck({currLocation: {...currLocation}, path: truck.path})
      setSearchPOI({radius: radius, poi: [...poi]})

    } catch (e) {

      if (e.response.status === 404) {
        setErr(`Couldn't find truck with license ${license}.`)
      } else {
        setErr('Unexpected error contact support.')
      }

      timeoutId.current = setTimeout(() => setErr(''), 3000)

    }

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
      return <Distance from={truck.currLocation} to={destination}/>
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
      <Portal open={!!err}>
        <Message className="message-error" compact negative size='small'>
          <Message.Header>{err}</Message.Header>
        </Message>
      </Portal>
    </div>
  )
}


