"use client"
import { useRouter } from "next/navigation"
import { useState } from "react"
import HeaderForStaff from "../../components/Header/HeaderForStaff"

function UserLogin() {
  this.email = ""
  this.password = ""
}

export default function Login() {

  const [userLogin, setUserLogin] = useState(new UserLogin())
  const [error, setError] = useState("")
  const router = useRouter()

  function handleSubmit(e, userLogin) {
    e.preventDefault()

    fetch('http://localhost:8081/api/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: userLogin.email,
        password: userLogin.password
      })
    })
      .then(res => {
        if (res.ok)
          return res.json()
        throw Error('Invalid data')
      })
      .then(data => {
        localStorage.setItem('jwt', data.jwt)
        localStorage.setItem('role', data.role)
        localStorage.setItem('name', data.name)
        router.push('/destinations')
      })
      .catch(err => setError("Invalid username or password"))
  }

  return (
    <>
      <HeaderForStaff/>
      <form onSubmit={(e) => handleSubmit(e, userLogin)}>

      <label className="invalid-data">{error}</label>
        <div>
          <label>Email: </label>
          <input required name="email" value={userLogin.email} onChange={(e) => setUserLogin({ ...userLogin, email: e.target.value })} />
        </div>
        <div>
          <label>Password: </label>
          <input required type="password" name="password" value={userLogin.password} onChange={(e) => setUserLogin({ ...userLogin, password: e.target.value })} />
        </div>

        <button type="submit">Login</button>
      </form>
    </>
  )
}