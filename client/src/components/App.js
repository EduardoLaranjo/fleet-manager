import React from "react";
import {FleetMap} from "./fleet/FleetMap";
import "./App.css";
import {TopBar} from "./fleet/TopBar";


export function App() {
  return (
    <div>
      <TopBar/>
      <FleetMap/>
    </div>
  );
}