"use client"
import { useRouter, useSearchParams } from "next/navigation"
import { FormEvent, use, useEffect, useState } from "react"


export default function EditUser() {

  const [user, setUser] = useState(undefined)
  const router = useRouter()
  const searchParams = useSearchParams()
  const id = searchParams.get("id")
  const jwtCookie = 'TODO'
  useEffect(() => {
    fetch(`http://localhost:8081/api/user/${id}`, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + jwtCookie
      }
    })
      .then(res => res.json())
      .then(data => setUser(data))
  }, [])

  if (user === undefined)
    return;



  function handleSubmit(e: FormEvent<HTMLFormElement>, user: any): void {
    e.preventDefault();
    fetch(`http://localhost:8081/api/user`, {
      method: 'PUT',
      headers: {
        'Authorization': 'Bearer ' + jwtCookie,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: user.id,
        email: user.email,
        userTypeId: user.userTypeId,
        active: !user.active,
        password: "something",
        name: user.name,
        lastname: user.lastname
      })
    })
    .then(() => router.push('/users'))
    .catch(error => console.log(error))
  }

  return (
    <form onSubmit={e => handleSubmit(e, user)}>
      <div>
        <label>Name: </label>
        <input name="name" value={user.name} onChange={e => setUser({ ...user, name: e.target.value })} />

        <label>Lastname: </label>
        <input name="lastname" value={user.lastname} onChange={e => setUser({ ...user, lastname: e.target.value })} />
      </div>
      <div>
        <label>Email: </label>
        <input name="email" value={user.email} onChange={e => setUser({ ...user, email: e.target.value })} />

        <label>Type: </label>
        <input disabled name="type" value={user.type ? 'TRUE' : 'FALSE'} onChange={e => setUser({ ...user, type: e.target.value })} />
      </div>
      <button type="submit">Submit</button>
    </form>
  )
}