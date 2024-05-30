
import { useEffect, useState } from 'react'
import './header.css'
import GlobalData from '../../types/globalData'
import { useRouter } from 'next/navigation'

export default function HeaderForStaff() {

  const [global, setGlobal] = useState(new GlobalData())
  const router = useRouter()

  useEffect(() => {
    setGlobal({
      ...global, 
      jwt: window.localStorage.getItem('jwt'),
      role: window.localStorage.getItem('role')
    })
  }, [])

  function handelLogout() {
    window.localStorage.removeItem('jwt')
    window.localStorage.removeItem('name')
    window.localStorage.removeItem('role')
    router.push('/login')
  }


  return(
    <div className='header'>
      <div className='header-logo'>
        <h3>Travel Guide</h3>
      </div>
      <div className='header-navigation'>
        {global.jwt !== null && global.role === 'ADMIN' && <button className='header-navigation-item' onClick={() => router.push("/users")}>Users</button>}
        {global.jwt !== null && <button className='header-navigation-item' onClick={() => router.push("/destinations")}>Destinations</button>}
        {global.jwt === null && <button className='header-navigation-item' onClick={() => router.push("/login")}>Login</button>}
        {global.jwt !== null && <button className='header-navigation-item' onClick={() => handelLogout()}>Logout</button>}
      </div>
    </div>
  )
}