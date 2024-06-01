"use client"
import { useEffect, useState } from "react"
import TableUser from "../../components/Table/Table/TableUser.jsx";
import HeaderForStaff from "../../components/Header/HeaderForStaff.jsx";
import { useRouter } from "next/navigation";
import GlobalData from '../../types/globalData.js';
import { loginCheck } from "../../components/Helpers.js"

export default function Users() {
  const [users, setUsers] = useState([])
  const [global, setGlobal] = useState(new GlobalData())
  const router = useRouter()

  loginCheck();

  useEffect(() => {
    setGlobal({ ...global, jwt: window.localStorage.getItem('jwt') })
    fetch("http://localhost:8081/api/user", {
      method: "GET",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setUsers(data))
  }, [])


  if (users.length === 0)
    return;

  return (
    <>
      <HeaderForStaff/>
      <TableUser data={users} rowsPerPage={4} />
    </>
  )
}