import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { setAnswer } from '../../store/slices/assessmentSlice'

function MCQQuestion({ question }) {
  const dispatch = useDispatch()
  const answer = useSelector(state => state.assessment.answers[question.id])

  const handleOptionSelect = (option) => {
    dispatch(setAnswer({ questionId: question.id, answer: option }))
  }

  return (
    <div className="space-y-4">
      <h3 className="text-lg font-medium">{question.question}</h3>
      <div className="space-y-2">
        {question.options.map((option, index) => (
          <label key={index} className="flex items-center space-x-2 p-2 rounded hover:bg-gray-100">
            <input
              type="radio"
              name={`question-${question.id}`}
              checked={answer === option}
              onChange={() => handleOptionSelect(option)}
              className="h-4 w-4 text-blue-600"
            />
            <span>{option}</span>
          </label>
        ))}
      </div>
    </div>
  )
}

export default MCQQuestion