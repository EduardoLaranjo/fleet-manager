import React from "react";
import {useEffect} from "react";

export function Distance({from, to, map, maps}) {

  const arrow = {path: maps.SymbolPath.FORWARD_OPEN_ARROW};

  console.log('distance')

  useEffect(() => {
    const marker = new maps.Polyline({
      path: [{...from}, {...to}],
      icons: [{icon: arrow, offset: "99.997%"}],
      strokeColor: '#288E98',
      strokeOpacity: 1.0,
      map: map
    });

    // clean marker
    return () => marker.setMap(null);
  })

  return null;
}