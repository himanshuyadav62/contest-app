import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { nextQuestion, previousQuestion } from '../store/slices/assessmentSlice'
import MCQQuestion from '../components/questions/MCQQuestion'
import MSQQuestion from '../components/questions/MSQQuestion'
import CodingQuestion from '../components/questions/CodingQuestion'

function AssessmentPage() {
  const dispatch = useDispatch()
  const { questions, currentQuestionIndex } = useSelector(state => state.assessment)
  const currentQuestion = questions[currentQuestionIndex]

  const renderQuestion = () => {
    switch (currentQuestion.type) {
      case 'mcq':
        return <MCQQuestion question={currentQuestion} />
      case 'msq':
        return <MSQQuestion question={currentQuestion} />
      case 'coding':
        return <CodingQuestion question={currentQuestion} />
      default:
        return null
    }
  }

  return (
    <div className="max-w-3xl mx-auto">
      <div className="bg-white shadow rounded-lg p-6">
        <div className="mb-4">
          <span className="text-sm text-gray-500">
            Question {currentQuestionIndex + 1} of {questions.length}
          </span>
        </div>
        
        {renderQuestion()}

        <div className="mt-6 flex justify-between">
          <button
            onClick={() => dispatch(previousQuestion())}
            disabled={currentQuestionIndex === 0}
            className="px-4 py-2 bg-gray-200 text-gray-700 rounded hover:bg-gray-300 disabled:opacity-50"
          >
            Previous
          </button>
          <button
            onClick={() => dispatch(nextQuestion())}
            disabled={currentQuestionIndex === questions.length - 1}
            className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 disabled:opacity-50"
          >
            Next
          </button>
        </div>
      </div>
    </div>
  )
}

export default AssessmentPage