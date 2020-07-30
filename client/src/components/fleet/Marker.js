import React, {useEffect} from "react";

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

