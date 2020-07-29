import React, {useState} from "react";
import './SearchBar.css'
import {Form, Input, Select} from "semantic-ui-react";
import Button from "semantic-ui-react/dist/commonjs/elements/Button";

const POINTS_OF_INTEREST = [
  {key: 0, text: 'View all', value: 'all'},
  {key: 1, text: 'Gas Station', value: 'gas_station'},
  {key: 2, text: 'Restaurants', value: 'restaurant'},
  {key: 3, text: 'Hotels', value: 'hotel'}
]

const ALL_POINTS_OF_INTEREST = POINTS_OF_INTEREST
  .filter(pair => pair.value !== 'all' || pair.key !== '')
  .map(value => value.value);

const RADIUS = [
  {key: 1, text: '2 Kms', value: 2000},
  {key: 2, text: '5 Kms', value: 5000},
  {key: 3, text: '10 Kms', value: 10000}
]

export function SearchBar({onSearchSubmit}) {
  const [formState, setFormState] = useState({license: '', poi: [], radius: 0, errors: {}});

  const handlePointOfInterestChange = (e, {value}) => {
    if ('all' === value) {
      setFormState({...formState, poi: [...ALL_POINTS_OF_INTEREST]})
    } else {
      setFormState({...formState, poi: [value]})
    }
  }

  const handleRadiusChange = (e, {value}) => {
    setFormState({...formState, radius: value})
  };

  const handleLicenseChange = (event) => {
    setFormState({...formState, license: event.target.value})
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const errors = {}

    if (formState.license.length <= 0) {
      errors.license = true
    }

    if (formState.poi.length <= 0) {
      errors.pois = true
    }

    if (formState.radius <= 0) {
      errors.radius = true
    }

    setFormState({...formState, errors})

    if (!errors.radius && !errors.license && !errors.pois) {
      onSearchSubmit(formState)
    }

  }

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className={"search-bar-container"} widths='equal'>
        <Form.Field
          control={Input}
          placeholder='Search by license plate'
          onChange={handleLicenseChange}
          error={formState.errors.license}
        />
        <Form.Field
          control={Select}
          placeholder='Select POI type'
          options={POINTS_OF_INTEREST}
          onChange={handlePointOfInterestChange}
          error={formState.errors.pois}
        />
        <Form.Field
          control={Select}
          placeholder='Select radius'
          options={RADIUS}
          onChange={handleRadiusChange}
          error={formState.errors.radius}
        />
        <Button color="teal" type="submit">Apply</Button>
      </Form.Group>
    </Form>
  )
}