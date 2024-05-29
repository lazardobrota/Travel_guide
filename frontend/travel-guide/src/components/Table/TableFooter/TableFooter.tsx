import { useEffect } from "react"

const TableFooter = ({range, setPage, page, slice}) => {
  useEffect(() => {
    if (slice.length < 1 && page !== 1)
      setPage(page - 1)

  }, [slice, page, setPage])

  return (
    <div>
      {range.map((elem, index) => {
        <button
          key={index}
          //className={page === elem ? styles.activeButton : styles.inactiveButton}
          onClick={() => setPage(elem)}
        >
          {elem}
        </button>
      })}
    </div>
  )
}

export default TableFooter