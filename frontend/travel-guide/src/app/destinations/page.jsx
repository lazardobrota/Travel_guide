"use client"

import { useEffect, useState } from "react";
import HeaderForStaff from "../../components/Header/HeaderForStaff";
import TableDestinations from "../../components/Table/Table/TableDestinations";
import { useRouter } from "next/navigation";
import GlobalData from "../../types/globalData";

export default function Destinations() {
  const [destinations, setDestin] = useState([])
  const [global, setGlobal] = useState(new GlobalData())
  const router = useRouter()

  useEffect(() => {
    setGlobal({ 
      ...global, 
      jwt: window.localStorage.getItem('jwt'),
      role: window.localStorage.getItem('role')
    })
    fetch("http://localhost:8081/api/destination", {
      method: "GET",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setDestin(data))
  }, [])

  return (
    <>
      <HeaderForStaff/>
      <TableDestinations data={destinations} role={global.role} rowsPerPage={4}/>
      {global.role !== null && <button onClick={() => router.push('/destinations/new')}>New Destination</button>}
    </>
  )
}