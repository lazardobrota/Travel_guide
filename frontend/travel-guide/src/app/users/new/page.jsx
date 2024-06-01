"use client"
import { useRouter } from "next/navigation"
import { useEffect, useState } from "react"
import HeaderForStaff from "../../../components/Header/HeaderForStaff"
import User from "../../../types/user"
import { loginCheck } from "../../../components/Helpers"

export default function NewUser() {
  const [error, setError] = useState("")
  const [user, setUser] = useState(new User())
  const [userTypes, setUserTypes] = useState(undefined)
  const router = useRouter()

  loginCheck();

  useEffect(() => {

    fetch('http://localhost:8081/api/user/type', {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setUserTypes(data))
  }, [])

  function handleSubmit(e, user) {
    e.preventDefault()

    //admin must be active
    if (user.userTypeId === 1)
      user.active = true

    console.log(user)
    fetch('http://localhost:8081/api/user', {
      method: 'POST',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt'),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: user.email,
        userTypeId: user.userTypeId,
        active: user.active,
        password: user.password,
        name: user.firstname,
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

  if (userTypes === undefined)
    return

  return (
    <>
      <HeaderForStaff />
      <div>
        <label className="invalid-data">{error}</label>
        <form onSubmit={e => handleSubmit(e, user)}>
          <div>
            <label>Name: </label>
            <input required name="name" value={user.firstname} onChange={e => setUser({ ...user, firstname: e.target.value })} />

            <label>Lastname: </label>
            <input required name="lastname" value={user.lastname} onChange={e => setUser({ ...user, lastname: e.target.value })} />
          </div>
          <div>
            <label>Email: </label>
            <input required name="email" value={user.email} onChange={e => setUser({ ...user, email: e.target.value })} />

            <label>Password: </label>
            <input required name="password" value={user.password} onChange={e => setUser({ ...user, password: e.target.value })} />
          </div>
          <div>
            <label>Role: </label>
            <select defaultValue={2} onChange={(e) => setUser({ ...user, userTypeId: parseInt(e.target.value) })}>
              {userTypes.map((elem) => (
                <option key={elem.id} value={elem.id}>{elem.role}</option>
              ))}
            </select>
            <label hidden={user.userTypeId === 1 ? true : false}>Active: </label>
            <input hidden={user.userTypeId === 1 ? true : false} name="type" type="checkbox" defaultChecked={user.active} onChange={e => setUser({ ...user, active: !user.active })} />
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    </>
  )
}