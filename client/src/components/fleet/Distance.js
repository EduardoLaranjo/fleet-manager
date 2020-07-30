import {useEffect} from "react";
import "./Distance.css"

export function Distance({from, to, map, maps}) {

  const arrow = {path: maps.SymbolPath.FORWARD_OPEN_ARROW};

  useEffect(() => {
    const marker = new maps.Polyline({
      path: [{...from}, {...to}],
      icons: [{icon: arrow, offset: "96%"}],
      strokeColor: '#288E98',
      strokeOpacity: 1.0,
      map: map,
    });

    const distanceBetween = maps.geometry.spherical.computeDistanceBetween(new maps.LatLng(from.lat, from.lng), new maps.LatLng(to.lat, to.lng));

    const info = new maps.InfoWindow({
      content: `<div class="distance-container"><span> Distance: ${Math.trunc(distanceBetween)}m</span></div>`,
      position: from
    });

    info.open(map)

    // clean marker
    return () => {
      info.close()
      marker.setMap(null)
    };

  })

  return null;
}
