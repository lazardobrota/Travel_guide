"use client"

import { useRouter, useSearchParams } from "next/navigation"
import { useEffect, useState } from "react"
import HeaderForStaff from "../../components/Header/HeaderForStaff"


export default function Comments() {
  const [article, setArticle] = useState(undefined)
  const router = useRouter()
  const searchParams = useSearchParams()
  const id = searchParams.get("articleId")
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

  if (article === undefined)
    return;

  console.log(article)

  return (
    <>
      <HeaderForStaff />
      <h3>Article: {article.title}</h3>
      <h4>Author: {article.author}</h4>
      {article.activities.map(elem => (
        <h5 key={elem.id} style={{ display: "inline-block", margin: "0px 16px" }} >{elem.name}</h5>
      ))}
      <h4>Date: {article.createdAt[2] +"/"+ article.createdAt[1] +"/"+ article.createdAt[0]}</h4>
      <p>{article.text}</p>
      
    </>
  )
}