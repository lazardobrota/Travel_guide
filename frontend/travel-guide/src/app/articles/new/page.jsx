"use client"
import { useEffect, useState } from "react";
import HeaderForStaff from "../../../components/Header/HeaderForStaff";
import Article from "../../../types/article";
import { useRouter } from "next/navigation";


export default function NewArticle() {

  const [article, setArticle] = useState(new Article())
  const [destin, setDestin] = useState(undefined)
  const [activities, setActivities] = useState(undefined)
  const router = useRouter()

  useEffect(() => {
    // fetch(`http://localhost:8081/api/activity`, {
    //   method: 'GET',
    //   headers: {
    //     'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
    //   }
    // })
    //   .then(res => res.json())
    //   .then(data => setActivities(data))

    fetch(`http://localhost:8081/api/destination`, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setDestin(data))
  }, [])


  function handleSubmit(e, article) {
    e.preventDefault()

    console.log(article)
  }

  if (destin === undefined)
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

          <label>Article: </label>
          <select onChange={(e) => setArticle({ ...article, destinationId: parseInt(e.target.value) })}>
            {destin.map((elem) => (
              <option key={elem.id} value={elem.id}>{elem.name}</option>
            ))}
          </select>
        </div>
        <button type="submit">Submit</button>
      </form>
    </>
  )
}