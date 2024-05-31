"use client"
import { useRouter, useSearchParams } from "next/navigation"
import { useEffect, useState } from "react"
import HeaderForStaff from "../../../components/Header/HeaderForStaff"


export default function EditDestination() {
  const [article, setArticle] = useState(undefined)
  const router = useRouter()
  const searchParams = useSearchParams()
  const id = searchParams.get("id")


  useEffect(() => {
    fetch(`http://localhost:8081/api/article/${id}`, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setArticle(data))
  }, [])

  function handleSubmit(event, destination) {
    event.preventDefault()
    fetch(`http://localhost:8081/api/article`, {
      method: 'PUT',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt'),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: article.id,
        destinationId: article.destinationId,
        author: article.author,
        title: article.title,
        text: article.text
      })
    })
      .then(() => router.push(`/articles?destinationId=${article.destinationId}`))
      .catch(error => console.log(error))
  }

  function handleDelete(article) {
    const isDeleting = confirm("Are you sure you want to delete this object?")

    if (!isDeleting)
      return;

    fetch(`http://localhost:8081/api/article/${article.id}`, {
      method: "DELETE",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(() => router.push(`/articles?destinationId=${article.destinationId}`))
      .catch(error => console.log(error))
  }

  if (article === undefined)
    return;

  return (
    <>
      <HeaderForStaff />
      <form onSubmit={e => handleSubmit(e, article)}>
        <div>
          <label>Title: </label>
          <input name="title" value={article.title} onChange={e => setArticle({ ...article, title: e.target.value })} />
        </div>
        <div>
          <label>author: </label>
          <input name="author" value={article.author} onChange={e => setArticle({ ...article, author: e.target.value })} />
        </div>
        <div>
          <label>Text: </label>
          <textarea name="text" value={article.text} onChange={e => setArticle({ ...article, text: e.target.value })} />
        </div>
        <button type="submit">Submit</button>
      </form>
      <button onClick={() => handleDelete(article)}>Delete</button>
    </>
  )
}