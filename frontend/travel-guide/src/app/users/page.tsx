"use client"
import { useEffect, useState } from "react"
import TableUser from "../../components/Table/Table/TableUser";



export default function UserTable() {
  const [users, setUsers] = useState([])

  useEffect(() => {
    fetch("http://localhost:8081/api/user", {
      method: "GET"
    })
      .then(res => res.json())
      .then(data => setUsers(data))
  }, [])


  if (users.length === 0)
    return;

  return (
    <>
      <TableUser data={users} setUsers={setUsers} rowsPerPage={4} />
    </>
  )
}