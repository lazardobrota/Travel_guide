import { useState } from "react";
import useTable from "../../../hooks/useTable";
import TableFooter from "../TableFooter/TableFooter";
import "./table.css"
import { error } from "console";
import { useRouter, useSearchParams } from "next/navigation";


export default function TableUser({ data, setUsers, rowsPerPage }) {
  const [page, setPage] = useState(1)
  const { slice, range } = useTable(data, page, rowsPerPage)
  const router = useRouter()
  const searchParams = useSearchParams()

  const jwtCookie = "TODO"

  // function switchActive(elem: any): void {
  //   fetch("http://localhost:8081/api/user", {
  //     method: 'PUT',
  //     headers: {
  //       'Content-Type': 'application/json'
  //     },
  //     body: JSON.stringify({
  //       id: elem.id,
  //       email: elem.email,
  //       userTypeId: elem.userTypeId,
  //       active: !elem.active,
  //       password: "something",
  //       name: elem.name,
  //       lastname: elem.lastname
  //     })
  //   }).then(() => {
  //     setUsers(currentData => {
  //       return currentData.map(user => {
  //         if (user.id === elem.id) {
  //           return { ...user, active: !elem.active }
  //         }

  //         return user
  //       })
  //     })
  //   }).catch(error => console.log(error))
  // }

  function changeEvent(elem) {
    router.push(`/users/edit?id=${elem.id}`)
  }

  return (
    <>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Lastname</th>
            <th>Email</th>
            <th>Role</th>
            <th>Active</th>
          </tr>
        </thead>
        <tbody>
          {slice.map((elem) => (
            <tr className="tableRowItems" onClick={() => changeEvent(elem)} key={elem.id}>
              <td>{elem.name}</td>
              <td>{elem.lastname}</td>
              <td>{elem.email}</td>
              <td>{elem.role}</td>
              <td>{elem.active ? "TRUE" : "FALSE"}</td>
              {/* <td>{elem.userTypeId === 2 && <button onClick={() => switchActive(elem)}>Switch Active</button>}</td> */}
            </tr>
          ))}
        </tbody>
      </table >
      <TableFooter range={range} slice={slice} setPage={setPage} page={page}></TableFooter>
      <button onClick={() => router.push('/users/new')}>New User</button>
    </>
  )
}