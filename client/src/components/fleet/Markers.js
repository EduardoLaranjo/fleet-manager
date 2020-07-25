import {useEffect} from "react";

function useMarker(pos, handleOnClick, map, maps, imgUrl) {

  const image = {
    url: imgUrl,
    scaledSize: new maps.Size(22, 22),
    anchor: new maps.Point(10, 10)
  };

  useEffect(() => {
    const marker = new maps.Marker({
      position: pos,
      map: map,
      icon: image
    });

    if (handleOnClick) {
      marker.addListener("click", () => handleOnClick(pos));
    }

    // clean marker
    return () => marker.setMap(null);
  })

}

export function GasStation({pos, handleOnClick, map, maps}) {
  const imgUrl = `${process.env.PUBLIC_URL}/fleet/icn-gas-station.png`;
  useMarker(pos, handleOnClick, map, maps, imgUrl)
  return null;
}

export function Hotel({pos, handleOnClick, map, maps}) {
  const imgUrl = `${process.env.PUBLIC_URL}/fleet/icn-hotel.png`;
  useMarker(pos, handleOnClick, map, maps, imgUrl)
  return null;
}

export function Restaurant({pos, handleOnClick, map, maps}) {
  const imgUrl = `${process.env.PUBLIC_URL}/fleet/icn-restaurant.png`;
  useMarker(pos, handleOnClick, map, maps, imgUrl)
  return null;
}

export function Truck({pos, map, maps}) {
  const imgUrl = `${process.env.PUBLIC_URL}/fleet/icn-current-location.png`;
  useMarker(pos, null, map, maps, imgUrl)
  return null;
}




