import React, {useEffect, useRef, useState} from "react";

const LISBON = {lat: 38.733858, lng: -9.1501}

const mapStyles = [
  {
    featureType: "poi",
    stylers: [{visibility: "off"}]
  },
  {
    featureType: "transit",
    stylers: [{visibility: "off"}]
  }
]

export function GoogleMap({center = LISBON, children}) {
  const {maps} = window.google

  const mapRef = useRef(null);
  const [map, setMap] = useState(null)

  useEffect(() => {
    const map = new maps.Map(mapRef.current, {
        zoom: 11,
        center: center,
        streetViewControl: false,
        mapTypeControl: false,
        styles: mapStyles
      }
    );
    setMap(map);
  }, [])

  function renderChildren() {
    return React.Children.map(children, child => {
      if (child) {
        return React.cloneElement(child, {
          ...child.props,
          map: map,
          maps: maps
        })
      }
    })
  }

  return <>
    <MapContainer mapRef={mapRef}/>
    {renderChildren()}
  </>

}

const propsAlwaysEqual = () => true

const MapContainer = React.memo(function ({mapRef}) {
  return <div ref={mapRef} style={{width: "100vw", height: "100vh"}}/>
}, propsAlwaysEqual)


