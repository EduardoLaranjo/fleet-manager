import React, {useEffect, useRef, useState} from "react";

const LISBON = {lat: 38.733858, lng: -9.1501}

export function GoogleMap({center, children}) {

  center = center || LISBON;

  const mapRef = useRef(null);

  const [maps] = useState(window.google.maps)
  const [map, setMap] = useState(null)

  useEffect(() => {
    const newMap = new maps.Map(mapRef.current, {
        zoom: 14,
        center: center,
        streetViewControl: false,
        mapTypeControl: false
      }
    );

    setMap(newMap);

  }, [maps.Map])


  function renderChildren() {
    return React.Children.map(children, child => {
      return React.cloneElement(child, {
        ...child.props,
        map: map,
        maps: maps
      })
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


