"use client"
import { useRouter, useSearchParams } from "next/navigation"
import { useEffect, useState } from "react"
import HeaderForStaff from "../../../components/Header/HeaderForStaff"
import { loginCheck } from "../../../components/Helpers"


export default function EditDestination() {
  const [error, setError] = useState("")
  const [destination, setDestin] = useState(undefined)
  const router = useRouter()
  const searchParams = useSearchParams()
  const id = searchParams.get("id")
  loginCheck();

  useEffect(() => {
    fetch(`http://localhost:8081/api/destination/${id}`, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setDestin(data))
  }, [])

  function handleSubmit(event, destination) {
    event.preventDefault()
    fetch(`http://localhost:8081/api/destination`, {
      method: 'PUT',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt'),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: destination.id,
        name: destination.name,
        description: destination.description
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

  function handleDelete(destination) {
    const isDeleting = confirm("Are you sure you want to delete this object?")

    if (!isDeleting)
      return;

    fetch(`http://localhost:8081/api/destination/${destination.id}`, {
      method: "DELETE",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(() => router.push("/destinations"))
      .catch(error => console.log(error))
  }

  if (destination === undefined)
    return;

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
            <textarea name="description" value={destination.description} onChange={e => setDestin({ ...destination, description: e.target.value })} />
          </div>
          <button type="submit">Submit</button>
        </form>
        <button onClick={() => handleDelete(destination)}>Delete</button>
      </div>
    </>
  )
}