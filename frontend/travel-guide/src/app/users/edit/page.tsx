"use client"
import { useRouter, useSearchParams } from "next/navigation"
import { FormEvent, use, useEffect, useState } from "react"
import HeaderForStaff from "../../../components/Header/HeaderForStaff"
import { loginCheck } from "../../../components/Helpers"


export default function EditUser() {
  const [error, setError] = useState("")
  const [user, setUser] = useState(undefined)
  const router = useRouter()
  const searchParams = useSearchParams()
  const id = searchParams.get("id")

  loginCheck();

  useEffect(() => {
    fetch(`http://localhost:8081/api/user/${id}`, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setUser(data))
  }, [])

  if (user === undefined)
    return;

  function handleSubmit(e, user) {
    e.preventDefault();
    fetch(`http://localhost:8081/api/user`, {
      method: 'PUT',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt'),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: user.id,
        email: user.email,
        userTypeId: user.userTypeId,
        active: user.active,
        password: "something",
        name: user.name,
        lastname: user.lastname
      })
    })
      .then(res => res.json())
      .then(data => {
        if (data.email !== null)
          return router.push("/users")
        throw Error("Invalid data")
      })
      .catch(() => setError("There is already user with that email"))
  }

  if (user === undefined)
    return;

  return (
    <>
      <HeaderForStaff />
      <div>
        <label className="invalid-data">{error}</label>
        <form onSubmit={e => handleSubmit(e, user)}>
          <div>
            <label>Role: {user.role}</label>
          </div>
          <div>
            <label>Name: </label>
            <input name="name" value={user.name} onChange={e => setUser({ ...user, name: e.target.value })} />

            <label>Lastname: </label>
            <input name="lastname" value={user.lastname} onChange={e => setUser({ ...user, lastname: e.target.value })} />
          </div>
          <div>
            <label>Email: </label>
            <input name="email" value={user.email} onChange={e => setUser({ ...user, email: e.target.value })} />

            <label>Active: </label>
            <input disabled={user.userTypeId === 1 ? true : false} name="type" type="checkbox" defaultChecked={user.active} onChange={e => setUser({ ...user, active: !user.active })} />
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    </>
  )
}