"use client"
import { useRouter, useSearchParams } from "next/navigation"
import { useEffect, useState } from "react"
import HeaderForStaff from "../../../../components/Header/HeaderForStaff"


export default function EditDestination() {
  const [destination, setDestin] = useState(undefined)
  const router = useRouter()
  const searchParams = useSearchParams()
  const id = searchParams.get("id")
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
      .then(() => router.push("/destinations"))
      .catch(error => console.log(error))
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
      <button onClick={() => handleDelete(destination)}>Delete</button>
    </>
  )
}