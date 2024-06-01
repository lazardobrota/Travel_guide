"use client"
import { useEffect, useState } from "react"
import { useRouter, useSearchParams } from "next/navigation"
import TableArticles from "../../components/Table/Table/TableArticles"
import GlobalData from "../../types/globalData"
import HeaderForStaff from "../../components/Header/HeaderForStaff"

export default function Articles() {

  const [destin, setDestin] = useState(undefined)
  const [global, setGlobal] = useState(new GlobalData())

  const router = useRouter()
  const searchParams = useSearchParams()
  const destinationId = searchParams.get("destinationId")

  useEffect(() => {
    setGlobal({
      ...global,
      jwt: window.localStorage.getItem('jwt'),
      role: window.localStorage.getItem('role')
    })
    fetch(`http://localhost:8081/api/destination/${destinationId}`, {
      method: 'GET'
    })
      .then(res => res.json())
      .then(data => setDestin(data))
  }, [])

  if (destin === undefined)
    return;

  return (
    <>
      <HeaderForStaff/>
      <h3>Destination: {destin.name}</h3>
      <p>{destin.description}</p>
      <h3>Articles:</h3>
      <TableArticles data={destin.articles} role={global.role} rowsPerPage={4}/>
      {global.role !== null && <button onClick={() => router.push(`/articles/new?destinationId=${destinationId}`)}>New Article</button>}
    </>
  )
}