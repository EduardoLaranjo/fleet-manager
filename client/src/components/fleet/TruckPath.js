import React from "react";
import {useMarker} from "./Marker";

export function TruckPath({path, map, maps}) {
  path = path || [];

  function renderPath(path) {
    return path.map((pos, idx) => {
      if (idx === (path.length - 1)) {
        return <TruckEnd key={pos.id} pos={pos} map={map} maps={maps}/>
      }
      if (idx === 0) {
        return <TruckStart key={pos.id} pos={pos} map={map} maps={maps}/>
      }
      return <TruckMiddle key={pos.id} pos={pos} map={map} maps={maps}/>
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