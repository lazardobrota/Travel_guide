import { useState } from "react";
import useTable from "../../../hooks/useTable";
import TableFooter from "../TableFooter/TableFooter";


export default function Table({ data, rowsPerPage }) {
  const [page, setPage] = useState(1)
  const { slice, range } = useTable(data, page, rowsPerPage)

  return (
    <>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Lastname</th>
            <th>Email</th>
            <th>Role</th>
          </tr>
        </thead>
        <tbody>
          {slice.map((elem) => (
            <tr key={elem.id}>
              <td>{elem.name}</td>
              <td>{elem.lastname}</td>
              <td>{elem.email}</td>
              <td>{elem.role}</td>
            </tr>
          ))}
        </tbody>
      </table >
      <TableFooter range={range} slice={slice} setPage={setPage} page={page}></TableFooter>
    </>
  )
}