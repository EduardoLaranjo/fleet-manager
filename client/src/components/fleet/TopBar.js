import React, {useState} from "react";
import './TopBar.css'


const POIS = [
  {key: 0, text: 'View all'},
  {key: 1, text: 'Gas Station'},
  {key: 2, text: 'Restaurants'},
  {key: 3, text: 'Hotels'}
]

const RADIUS = [
  {key: 0, text: '20'},
  {key: 1, text: '30'},
  {key: 2, text: '50'},
]

export function TopBar() {

  const handleClickApply = () => {

  }

  return (
    <div className="container-top-bar">
      <Input/>
      <Dropdown options={POIS}/>
      <Dropdown options={RADIUS}/>
      <button onClick={handleClickApply}>Apply</button>
    </div>
  )
}

function Dropdown({options}) {
  const [selected, setSelected] = useState(null)

  return (
    <select>
      {options.map(({key, text}) => <option key={key}>{text}</option>)}
    </select>
  )
}

function Input() {
  const [value, setValue] = useState(null)
  const handleChange = (event) => setValue(event.target.value)
  return <><input onChange={handleChange} value={value}/></>
}
