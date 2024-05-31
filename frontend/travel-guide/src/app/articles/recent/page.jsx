"use client"
import { useEffect, useState } from "react"
import { useRouter, useSearchParams } from "next/navigation"
import TableArticles from "../../../components/Table/Table/TableArticles"
import GlobalData from "../../../types/globalData"
import HeaderForStaff from "../../../components/Header/HeaderForStaff"

export default function RecentArticles() {

  const [articles, setArticles] = useState(undefined)
  const [global, setGlobal] = useState(new GlobalData())

  const router = useRouter()
  const searchParams = useSearchParams()

  useEffect(() => {
    setGlobal({
      ...global,
      jwt: window.localStorage.getItem('jwt'),
      role: window.localStorage.getItem('role')
    })
    fetch(`http://localhost:8081/api/article?top=${10}`, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setArticles(data))
  }, [])

  if (articles === undefined)
    return;

  return (
    <>
      <HeaderForStaff/>
      <h3>Articles:</h3>
      <TableArticles hasDestination={true} data={articles} rowsPerPage={4}/>
    </>
  )
}