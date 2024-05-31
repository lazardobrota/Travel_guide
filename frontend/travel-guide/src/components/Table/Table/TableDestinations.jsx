import { useRouter } from "next/navigation";
import { useState } from "react";
import useTable from "../../../hooks/useTable";
import TableFooter from "../TableFooter/TableFooter";
import "./table.css";

export default function TableDestinations({ data, role=null, rowsPerPage }) {
  const [page, setPage] = useState(1)
  const { slice, range } = useTable(data, page, rowsPerPage)
  const router = useRouter()


  function changeEvent(elem) {
    router.push(`/destinations/edit?id=${elem.id}`)
  }

  function goTo(elem) {
    router.push(`/articles?destinationId=${elem.id}`)
  }

  return (
    <>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
          </tr>
        </thead>
        <tbody>
          {slice.map((elem) => (
            <tr className="tableRowItems" key={elem.id}>
              <td onClick={() => goTo(elem)} >{elem.name}</td>
              <td onClick={() => goTo(elem)} >{elem.description.slice(0, 30) + "..."}</td>
              <td>{role !== null && <button onClick={() => changeEvent(elem)}>Edit</button>}</td>
            </tr>
          ))}
        </tbody>
      </table >
      <TableFooter range={range} slice={slice} setPage={setPage} page={page}></TableFooter>
    </>
  )
}