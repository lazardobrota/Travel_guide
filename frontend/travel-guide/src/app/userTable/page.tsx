"use client"
import { useState } from "react"
import Table from "../../components/Table/Table/Table";



export default function UserTable() {
  const [users, setUsers] = useState(undefined)
  fetch("http://localhost:8081/api/user", {
    method: "GET"
  })
  .then(res => res.json())
  .then(data => setUsers(data))
  .catch(error => {
    // if (error) 
    //   console.log(error)
  })

  if (users === undefined)
    return;

  return (
    <>
      <Table data={users} rowsPerPage={4} />
    </>
  )
}