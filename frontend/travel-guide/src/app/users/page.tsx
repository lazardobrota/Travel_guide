"use client"
import { useEffect, useState } from "react"
import TableUser from "../../components/Table/Table/TableUser";
import HeaderForStaff from "../../components/Header/HeaderForStaff";
import { useRouter } from "next/navigation";
import GlobalData from '../../types/globalData.js';
import { loginCheck } from "../../components/Helpers"

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
      <TableUser data={users} setUsers={setUsers} rowsPerPage={4} />
    </>
  )
}