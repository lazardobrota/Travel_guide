"use client"
import { useRouter } from "next/navigation"
import { useEffect, useState } from "react"
import HeaderForStaff from "../../../components/Header/HeaderForStaff"
import Destination from "../../../types/destination"
import { loginCheck } from "../../../components/Helpers"



export default function NewDestination() {
  const [error, setError] = useState("")
  const [destination, setDestin] = useState(new Destination())
  const router = useRouter()

  loginCheck();
  function handleSubmit(e, destin) {
    e.preventDefault()

    fetch('http://localhost:8081/api/destination', {
      method: 'POST',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem("jwt"),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        name: destin.name,
        description: destin.description
      })
    })
      .then(res => res.json())
      .then(data => {
        if (data.name !== null)
          return router.push("/destinations")
        throw Error("Invalid data")
      })
      .catch(() => setError("There is already destination with that name"))
  }

  return (
    <>
      <HeaderForStaff />
      <div>
        <label className="invalid-data">{error}</label>
        <form onSubmit={e => handleSubmit(e, destination)}>
          <div>
            <label>Name: </label>
            <input name="name" value={destination.name} onChange={e => setDestin({ ...destination, name: e.target.value })} />
          </div>
          <div>
            <label>Description: </label>
            <textarea name="lastname" value={destination.description} onChange={e => setDestin({ ...destination, description: e.target.value })} />
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    </>
  )
}