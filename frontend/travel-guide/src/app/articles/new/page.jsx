"use client"
import { useEffect, useState } from "react";
import HeaderForStaff from "../../../components/Header/HeaderForStaff";
import Article from "../../../types/article";
import { useRouter, useSearchParams } from "next/navigation";


export default function NewArticle() {

  const [article, setArticle] = useState(new Article())
  const [destin, setDestin] = useState(undefined)
  const [activities, setActivities] = useState(undefined)
  const router = useRouter()
  const searchParams = useSearchParams()
  const destinationId = searchParams.get("destinationId")

  useEffect(() => {
    fetch(`http://localhost:8081/api/activity`, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => setActivities(data))

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

    article.visits = 1

    fetch("http://localhost:8081/api/article", {
      method: "POST",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt'),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        destinationId: destinationId, //TODO doesnt make sense to choose destination since we are on the page of destination that we want to add article
        author: article.author,
        title: article.title,
        text: article.text,
        visits: article.visits,
        activityIds: article.activityIds
      })
    })
      .then((res) => {
        if (res.ok)
          router.push(`/destinations/destination?id=${destinationId}`)
    })
      .catch(err => console.log(err))
  }

  function handleChange(e) {
    var options = e.target.options;
    let value = []
    for (var i = 0; i < options.length; i++) {
      if (options[i].selected) {
        value.push(parseInt(options[i].value));
      }
    }

    setArticle({ ...article, activityIds: value })
  }

  if (destin === undefined || activities === undefined)
    return;


  return (
    <>
      <HeaderForStaff />
      <form onSubmit={e => handleSubmit(e, article)}>
        <div>
          <label>Title: </label>
          <input name="title" required value={article.title} onChange={e => setArticle({ ...article, title: e.target.value })} />

          <label>author: </label>
          <input name="author" required value={article.author} onChange={e => setArticle({ ...article, author: e.target.value })} />
        </div>
        <div>
          <label>Text: </label>
          <textarea name="text" required value={article.text} onChange={e => setArticle({ ...article, text: e.target.value })} />

          {/* <label>Destination: </label>
          <select required defaultValue="" onChange={(e) => setArticle({ ...article, destinationId: parseInt(e.target.value) })}>
            <option disabled value=""> -- select an option -- </option>
            {destin.map((elem) => (
              <option key={elem.id} value={elem.id}>{elem.name}</option>
            ))}
          </select> */}
          <label>Activities: </label>
          <select multiple onChange={(e) => handleChange(e)}>
            {activities.map((elem) => (
              <option key={elem.id} value={elem.id}>{elem.name}</option>
            ))}
          </select>
        </div>
        <button type="submit">Submit</button>
      </form>
    </>
  )
}