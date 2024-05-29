"use client"
import { useRouter } from "next/navigation"
import { useState } from "react"

function UserLogin() {
  this.email = ""
  this.password = ""
}

export default function Login() {

  const [userLogin, setUserLogin] = useState(new UserLogin())
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
      router.push('/users')
    })
    .catch(err => console.log(err))
  }

  return (
    <form onSubmit={(e) => handleSubmit(e, userLogin)}>
      <div>
        <label>Email: </label>
        <input name="email" value={userLogin.email} onChange={(e) => setUserLogin({...userLogin, email: e.target.value})}/>
      </div>
      <div>
        <label>Password: </label>
        <input name="password" value={userLogin.password} onChange={(e) => setUserLogin({...userLogin, password: e.target.value})}/>
      </div>

      <button type="submit">Login</button>
    </form>
  )
}