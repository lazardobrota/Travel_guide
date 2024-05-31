"use client"

import { useRouter, useSearchParams } from "next/navigation"
import { useEffect, useState } from "react"
import HeaderForStaff from "../../components/Header/HeaderForStaff"
import Comment from "../../types/comment.js"


export default function Comments() {
  const [article, setArticle] = useState(undefined)
  const [comment, setComment] = useState(new Comment())
  const router = useRouter()
  const searchParams = useSearchParams()
  const id = searchParams.get("articleId")

  useEffect(() => {
    setComment({ ...comment, author: window.localStorage.getItem("name") })
    fetch(`http://localhost:8081/api/article/${id}`, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setArticle(data))
  }, [])

  function handleSubmit(e, comment) {
    e.preventDefault()

    fetch(`http://localhost:8081/api/article/comment`, {
      method: 'POST',
      headers: {
        'Authorization': 'Bearer' + window.localStorage.getItem("jwt"),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        author: comment.author,
        text: comment.text,
        articleId: parseInt(id)
      })
    }).then(res => {
      if (res.ok) {
        return res.json()
      }
    })
      .then(data => setArticle({ ...article, comments: [...article.comments, data] }))
      .catch(err => console.log(err))

    setComment({
      author: window.localStorage.getItem("name"),
      text: ""
    })
  }

  if (article === undefined)
    return;

  return (
    <>
      <HeaderForStaff />
      <div className="main-content">
        <h3>Article: {article.title}</h3>
        <h4>Author: {article.author}</h4>
        {article.activities.map(elem => (
          <h5 key={elem.id} style={{ display: "inline-block", margin: "0px 16px" }} >{elem.name}</h5>
        ))}
        <h4>Date: {article.createdAt[2] + "/" + article.createdAt[1] + "/" + article.createdAt[0]}</h4>
        <p>{article.text}</p>

        <h3>Comments</h3>

        <form onSubmit={e => handleSubmit(e, comment)}>
          <div>
            <label>Author: </label>
            <input required name="author" value={comment.author} onChange={e => setComment({ ...comment, author: e.target.value })} />
          </div>
          <div>
            <label>Text: </label>
            <textarea required name="text" value={comment.text} onChange={e => setComment({ ...comment, text: e.target.value })} />
          </div>
          <button type="submit">Add Comment</button>
        </form>
        {article.comments.map(elem => (
          <div key={elem.id}>
            <h4>{elem.title}</h4>
            <h5>{elem.author}</h5>
            <p>{elem.text}</p>
          </div>
        ))}
      </div>
    </>
  )
}