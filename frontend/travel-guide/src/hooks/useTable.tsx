
import { useState, useEffect} from "react"

const useTable = (data: any[], page: number, rowsPerPage: number) => {
  const [tableRange, setTableRange] = useState([])
  const [slice, setSlice] = useState([])

  useEffect(() => {
    const range = calculateRange(data, rowsPerPage)
    setTableRange([...range]) //[...range] same as range?? (just puts whole array as argument)

    const slice = sliceData(data, page, rowsPerPage)
    setSlice([...slice])
  }, [data, setTableRange, page, setSlice])
  
  return {slice, range: tableRange} //???
}

const calculateRange = (data: any[], rowsPerPage: number) => {
  const range = []; // numbers of pages for pagination ( 1 2 3 .. 100)
  const numOfPages = Math.ceil(data.length / rowsPerPage)
  for (let i = 1; i <= numOfPages; i++)
    range.push(i)

  return range
}

const sliceData = (data: any[], page: number, rowsPerPage: number) => {
  return data.slice((page - 1) * rowsPerPage, page * rowsPerPage)
}

export default useTable