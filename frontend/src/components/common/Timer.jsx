import React, { useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { updateTimeRemaining } from '../../store/slices/assessmentSlice'

function Timer() {
  const dispatch = useDispatch()
  const timeRemaining = useSelector(state => state.assessment.timeRemaining)

  useEffect(() => {
    const timer = setInterval(() => {
      dispatch(updateTimeRemaining())
    }, 1000)

    return () => clearInterval(timer)
  }, [dispatch])

  const formatTime = (seconds) => {
    const hours = Math.floor(seconds / 3600)
    const minutes = Math.floor((seconds % 3600) / 60)
    const remainingSeconds = seconds % 60
    return `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`
  }

  return (
    <div className="text-lg font-semibold">
      Time Remaining: {formatTime(timeRemaining)}
    </div>
  )
}

export default Timer