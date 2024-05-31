import { useRouter } from "next/navigation"
import { useEffect } from "react"

export function loginCheck() {
  const router = useRouter()
  useEffect(() => {
    fetch("http://localhost:8081/api/login/valid", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        jwt: window.localStorage.getItem('jwt')
      })
    }).then(res => res.json())
    .then(data => {
      if (!data.valid)
        router.push("/login")
    })
    .catch(() => router.push("/login"))
  }, [])
}