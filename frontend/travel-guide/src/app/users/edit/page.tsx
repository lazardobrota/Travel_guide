"use client"
import { useRouter, useSearchParams } from "next/navigation"
import { FormEvent, use, useEffect, useState } from "react"


export default function EditUser() {

  const [user, setUser] = useState(undefined)
  const router = useRouter()
  const searchParams = useSearchParams()
  const id = searchParams.get("id")
  
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
    .then(() => router.push('/users'))
    .catch(error => console.log(error))
  }

  if (user === undefined)
    return;

  return (
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
  )
}