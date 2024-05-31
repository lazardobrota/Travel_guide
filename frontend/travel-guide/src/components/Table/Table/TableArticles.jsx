import { useRouter } from "next/navigation";
import { useState } from "react";
import useTable from "../../../hooks/useTable";
import TableFooter from "../TableFooter/TableFooter";
import "./table.css";

export default function TableArticles({ data, role = null, hasDestination = false, rowsPerPage }) {
  const [page, setPage] = useState(1)
  const { slice, range } = useTable(data, page, rowsPerPage)
  const router = useRouter()


  function changeEvent(elem) {
    router.push(`/articles/edit?id=${elem.id}`)
  }

  async function goTo(elem) {
    console.log(elem)
    await fetch(`http://localhost:8081/api/article/${elem.id}`, {
      method: 'PUT',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })

    router.push(`/comments?articleId=${elem.id}`)
  }

  return (
    <>
      <table>
        <thead>
          <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Text</th>
            <th>Visits</th>
            <th>Created At</th>
            {hasDestination && <th>Destination</th>}
          </tr>
        </thead>
        <tbody>
          {slice.map((elem) => (
            <tr className="tableRowItems" key={elem.id}>
              <td onClick={() => goTo(elem)} >{elem.title}</td>
              <td onClick={() => goTo(elem)} >{elem.author}</td>
              <td onClick={() => goTo(elem)} >{elem.text.slice(0, 30) + "..."}</td>
              <td onClick={() => goTo(elem)} >{elem.visits}</td>
              <td onClick={() => goTo(elem)} >{elem.createdAt[2] + "-" + elem.createdAt[1] + "-" + elem.createdAt[0]}</td>
              {hasDestination && <td onClick={() => goTo(elem)}>{elem.destination.name}</td>}
              <td>{role !== null && <button onClick={() => changeEvent(elem)}>Edit</button>}</td>
            </tr>
          ))}
        </tbody>
      </table >
      <TableFooter range={range} slice={slice} setPage={setPage} page={page}></TableFooter>
    </>
  )
}